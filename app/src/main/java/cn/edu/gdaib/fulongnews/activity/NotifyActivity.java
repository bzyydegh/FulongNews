package cn.edu.gdaib.fulongnews.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import cn.edu.gdaib.fulongnews.R;
import cn.edu.gdaib.fulongnews.utils.NetworkUtil;

/**
 * 推送信息
 * author: ZYongY.
 * date: 2016/12/19.
 */
public class NotifyActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private WebView mWebView;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ProgressBar mProgressBar;
    private static final String URL = "https://www.jiguang.cn/push";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        initView();
    }

    //初始化
    private void initView(){
        mToolbar = (Toolbar) findViewById(R.id.notify_toolbar);
        mWebView = (WebView) findViewById(R.id.notify_webview);
        mProgressBar = (ProgressBar) findViewById(R.id.notify_progressbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout)
                findViewById(R.id.notify_collapse_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        mCollapsingToolbarLayout.setTitle("消息");
        //通过CollapsingToolbarLayout修改字体颜色
        mCollapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色
        initWebView();//初始化WebView
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            this.finish();
        }else if (item.getItemId() == R.id.news_detail_open_browser){
            selectBrowser();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
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

    //弹出一个选择浏览器的框，选择浏览器再进入
    public void selectBrowser() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.parse(URL);
        intent.setData(uri);
        startActivity(Intent.createChooser(intent, "请选择浏览器"));
    }

    //WebView相关设置
    @SuppressLint("SetJavaScriptEnabled")
    @SuppressWarnings("deprecation")
    private void initWebView(){
        //加载网址
        mWebView.loadUrl(URL);
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
        webSettings.setBuiltInZoomControls(true); //内置缩放按钮
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
    }
}
