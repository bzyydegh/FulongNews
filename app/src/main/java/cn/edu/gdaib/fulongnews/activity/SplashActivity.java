package cn.edu.gdaib.fulongnews.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import cn.edu.gdaib.fulongnews.R;
import cn.edu.gdaib.fulongnews.application.MyApp;
import cn.edu.gdaib.fulongnews.common.EventHttpRequest;
import cn.edu.gdaib.fulongnews.common.PeHttpRequest;
import cn.edu.gdaib.fulongnews.common.ScienceHttpRequest;
import cn.edu.gdaib.fulongnews.common.TourHttpRequest;
import cn.edu.gdaib.fulongnews.utils.Constant;
import cn.edu.gdaib.fulongnews.utils.NetworkUtil;
import cn.edu.gdaib.fulongnews.utils.PermissionsChecker;
import cn.edu.gdaib.fulongnews.utils.PromptUtil;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class SplashActivity extends Activity {

    private static final int REQUEST_CODE = 0; // 请求码
    private PermissionsChecker mPermissionsChecker; // 权限检测器
    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();//初始化
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 缺少权限时, 进入权限配置页面
        if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
            PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
        }
    }

    //初始化
    private void init(){
        mPermissionsChecker = new PermissionsChecker(this);
        //检查网络
        if (! NetworkUtil.isNetworkConnected(MyApp.getContext())){
            PromptUtil.ToastPrompt(MyApp.getContext(), "无可用网络，请检查网络设置", Toast.LENGTH_LONG);
        }else if (NetworkUtil.isMobileConnected(MyApp.getContext())) {
            PromptUtil.ToastPrompt(MyApp.getContext(), "当前正使用2G/3G/4G网络", Toast.LENGTH_LONG);
        }

        //发出网络请求
        //科技
        ScienceHttpRequest.networkRequest(Constant.SCIENCE_API_URL,
                Constant.SCIENCE_APP_KEY, "1", "50");
        //体育
        PeHttpRequest.networkRequest(Constant.PE_API_URL,
                Constant.PE_APP_KEY, "1", "50");
        //旅游
        TourHttpRequest.networkRequest(Constant.TOUR_API_URL,
                Constant.TOUR_APP_KEY, "1", "50");
        //时事
        EventHttpRequest.networkRequest(Constant.EVENT_API_URL,
                Constant.EVENT_APP_KEY, "1", "50");
        //延时跳转
        Observable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .subscribe(new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
    }

    //屏蔽物理返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
