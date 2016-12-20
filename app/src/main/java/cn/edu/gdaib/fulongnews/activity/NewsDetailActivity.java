package cn.edu.gdaib.fulongnews.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.edu.gdaib.fulongnews.R;
import cn.edu.gdaib.fulongnews.utils.NetworkUtil;

public class NewsDetailActivity extends AppCompatActivity{

    private Toolbar mToolbar;
    private WebView mWebView;
    private String mUrl;
    private String mToolbarTitle;
    private ProgressBar mProgressBar;
    private ImageView mImageView;
    private TextView mTextView;
    private Button mButtonReload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science_news_detail);
        init();//初始化
    }

    //初始化
    @SuppressLint("SetJavaScriptEnabled")
    @SuppressWarnings({"deprecation", "ConstantConditions"})
    private void init() {
        Intent intent = getIntent(); //获取Intent对象
        mUrl = intent.getStringExtra("HtmlUrl");
        mToolbarTitle = intent.getStringExtra("ToolbarTitle");

        mToolbar = (Toolbar) findViewById(R.id.news_detail_toolbar);
        mWebView = (WebView) findViewById(R.id.news_detail_webview);
        mImageView = (ImageView) findViewById(R.id.news_detail_iv_failure);
        mTextView = (TextView) findViewById(R.id.news_detail_tv_failure);
        mProgressBar = (ProgressBar) findViewById(R.id.news_detail_progressbar);
        mButtonReload = (Button) findViewById(R.id.news_detail_btn_reload) ;
        mToolbar.setTitle(mToolbarTitle);
        setSupportActionBar(mToolbar);

        //加载网址
        mWebView.loadUrl(mUrl);
        //WebView离线缓存
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setAppCacheEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setDomStorageEnabled(true); //开启DOM缓存，关闭的话H5自身的一些操作是无效的
        //Application Cache 存储机制
        webSettings.setAppCacheEnabled(true);
        final String cachePath = getApplicationContext().getDir("WebViewCache",
                Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(cachePath);
        webSettings.setAppCacheMaxSize(5 * 1024 * 1024);
        //缓存策略
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        if (NetworkUtil.isNetworkConnected(this)) {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }else{
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ONLY); //不使用网络，只加载缓存
        }
        webSettings.setJavaScriptEnabled(true); //支持Javascript
        mWebView.requestFocusFromTouch(); //支持获取手势焦点
        webSettings.setSupportZoom(true);  //支持缩放
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true);  //将图片调整到适合WebView的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //在本页面打开
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                mProgressBar.setVisibility(View.VISIBLE);
                view.loadUrl(url);
                return true;
            }
            //页面开始加载时
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
            }
            //加载出错
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                //可见
                mImageView.setVisibility(View.VISIBLE);
                mTextView.setVisibility(View.VISIBLE);
                mButtonReload.setVisibility(View.VISIBLE);
                super.onReceivedError(view, request, error);
            }
        });
        //使用WebChromeClient内核，显示进度
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100){
                    mProgressBar.setVisibility(View.GONE);
                }
                mProgressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }

        });
        //设置监听
        mButtonReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.reload(); //重新加载
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
        }else if (item.getItemId() == R.id.news_detail_open_browser){
            selectBrowser();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //当WebView的url进行跳转的时候，自动回记录进入历史界面
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //canGoBack方法判断历史记录中是否还有页面，如果还有上一级页面，将会返回true
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }else {
            this.finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    //销毁WebView
    private void clearWebViewResource() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            // android 5.1(sdk:21)
            ((ViewGroup)mWebView.getParent()).removeView(mWebView);
            mWebView.setTag(null);
            mWebView.clearHistory();
            mWebView.destroy();
            mWebView = null;
        }
    }

    @Override
    protected void onDestroy() {
        clearWebViewResource();
        super.onDestroy();
    }

    @Override
    public void finish() {
        //移除View，防止内存泄漏
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }

    //弹出一个选择浏览器的框，选择浏览器再进入
    public void selectBrowser() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(mUrl);
        intent.setData(uri);
        startActivity(Intent.createChooser(intent, "请选择浏览器"));
    }
}
