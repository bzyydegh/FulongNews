package cn.edu.gdaib.fulongnews.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import cn.jpush.android.api.JPushInterface;

/**
 * 全局上下文
 * author: ZYongY.
 * date: 2016/12/13.
 */

public class MyApp extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true); //setDebugMode 设置调试模式
        JPushInterface.init(this); //init 初始化SDK
        mContext = this;
    }
}
