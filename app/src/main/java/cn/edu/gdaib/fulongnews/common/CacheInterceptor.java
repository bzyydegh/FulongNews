package cn.edu.gdaib.fulongnews.common;

import java.io.IOException;

import cn.edu.gdaib.fulongnews.application.MyApp;
import cn.edu.gdaib.fulongnews.utils.NetworkUtil;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 缓存拦截器
 * author: ZYongY.
 * date: 2016/12/13.
 */

public class CacheInterceptor implements Interceptor{

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        //如果没有网络，则启用 FORCE_CACHE
        if (! NetworkUtil.isNetworkConnected(MyApp.getContext())) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }
        Response originalResponse = chain.proceed(request);
        if (NetworkUtil.isNetworkConnected(MyApp.getContext())) {
            int maxAge = 0; //有网络时，设置缓存超时时间为0个小时
            originalResponse.newBuilder()
                    .header("Cache-Control", "public, maxAge" + maxAge)
                    .removeHeader("Pragma") // 清除头信息，因为如果服务器不支持，会返回一些干扰信息，不清除下面无法生效
                    .build();
        }else {
            int maxStale = 60 * 60 * 24 * 2; // 无网络时，设置超时为2天
            originalResponse.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }
        return originalResponse;
    }

}
