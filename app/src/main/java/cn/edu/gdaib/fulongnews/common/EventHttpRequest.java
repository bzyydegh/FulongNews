package cn.edu.gdaib.fulongnews.common;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdaib.fulongnews.api.ApiService;
import cn.edu.gdaib.fulongnews.application.MyApp;
import cn.edu.gdaib.fulongnews.bean.EventInfoBean;
import cn.edu.gdaib.fulongnews.utils.FileUtil;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 网络请求后台，获取数据
 * author: ZYongY.
 * date: 2016/12/13.
 */

public class EventHttpRequest {
    private static File cacheFile;
    private static Cache cache;
    private static OkHttpClient okHttpClient;
    private static Retrofit retrofit;
    private static ApiService apiService;
    private static List<EventInfoBean> mEventData = new ArrayList<>();

    public static List<EventInfoBean> getData() {
        return mEventData;
    }

    //请求后台数据并缓存
    public static void networkRequest(String apiUrl, String apiKey, String page, String rows) {
        //设置缓存路径，文件名，大小
        cacheFile = new File(FileUtil.getDiskCacheDir(MyApp.getContext()), "EventNewsDataCache");
        cache = new Cache(cacheFile, 1024 * 1024 * 50);
        //日志打印
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        //OkHttp
        okHttpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(new CacheInterceptor())
                .addInterceptor(logging)
                .build();
        //网络请求
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(apiUrl)
                .build();
        apiService = retrofit.create(ApiService.class);

        apiService.getEventNews(apiKey, page, rows)  //获取Observable对象
                .subscribeOn(Schedulers.newThread())    //请求在新的线程中执行
                .observeOn(Schedulers.io())   //请求完成后在io线程中执行
                .doOnNext(new Action1<EventInfoBean>() {
                    @Override
                    public void call(EventInfoBean eventInfoBean) {
                        //保存信息
                        mEventData.add(eventInfoBean);
                        Log.i("doOnNext-event" ,"total=" + mEventData.get(0).getResult().size());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())   //最后在主线程中执行
                .subscribe(new Subscriber<EventInfoBean>() {
                    @Override
                    public void onCompleted() {   //请求结束
                        Log.i("Retrofit-event" ,"onCompleted" );
                        Log.i("error_code_event", "" + mEventData.get(0).getError_code());
                        Log.i("reason-event", "" + mEventData.get(0).getReason());
                    }

                    @Override
                    public void onError(Throwable e) {  //请求失败
                        //请求失败
                        Log.i("Retrofit-event" ,"onError:");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(EventInfoBean eventInfoBean) {   //请求成功
                        //请求成功
                        Log.i("Retrofit-event" ,"onNext");
                    }
                });
    }
}
