package cn.edu.gdaib.fulongnews.api;

import cn.edu.gdaib.fulongnews.bean.EventInfoBean;
import cn.edu.gdaib.fulongnews.bean.PeInfoBean;
import cn.edu.gdaib.fulongnews.bean.ScienceInfoBean;
import cn.edu.gdaib.fulongnews.bean.TourInfoBean;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * api接口
 * Created by ZYongY
 * on 2016/11/08.
 */

public interface ApiService {
    //科技：http://api.avatardata.cn/TechNews/Query?key={ApiKey}&page=1&rows=10
    //体育：http://api.avatardata.cn/SportsNews/Query?key={ApiKey}&page=1&rows=10
    //国际： http://api.avatardata.cn/WorldNews/Query?key={ApiKey}&page=1&rows=10
    //旅游：http://api.avatardata.cn/TravelNews/Query?key={ApiKey}&page=1&rows=10

    //返回Observable对象--科技
    @GET("Query")
    Observable<ScienceInfoBean> getScienceNews(@Query("key") String key,
                                               @Query("page") String page,
                                               @Query("rows") String rows);

    //返回Observable对象--体育
    @GET("Query")
    Observable<PeInfoBean> getPeNews(@Query("key") String key,
                                          @Query("page") String page,
                                          @Query("rows") String rows);

    //返回Observable对象--国际
    @GET("Query")
    Observable<EventInfoBean> getEventNews(@Query("key") String key,
                                           @Query("page") String page,
                                           @Query("rows") String rows);

    //返回Observable对象--旅游
    @GET("Query")
    Observable<TourInfoBean> getTourNews(@Query("key") String key,
                                         @Query("page") String page,
                                         @Query("rows") String rows);
}
