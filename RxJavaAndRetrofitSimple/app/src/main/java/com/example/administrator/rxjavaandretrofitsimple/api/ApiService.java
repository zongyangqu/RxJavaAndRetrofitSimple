package com.example.administrator.rxjavaandretrofitsimple.api;

import com.example.administrator.rxjavaandretrofitsimple.bean.JokeResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.NewsResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeatherResponse;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/19
 *
 * 类描述：请求接口类 功能与RequestClient一致
 */

public interface ApiService {

    /**
     * 根据请求Type获取响应新闻列表
     * @param cacheControl  网络请求缓存策略，只支持GET请求
     * @return
     */
    @GET("toutiao/index?key=761fc4e2bffe6ed2997b3626a642c3e0")
    Observable<NewsResponse> getNewsClassify(@Query("type")String requestType, @Header("Cache-Control") String cacheControl);

    /**
     * 获取头条新闻列表
     * @param cacheControl  网络请求缓存策略，只支持GET请求
     * @return
     */
    @GET("toutiao/index?type=top&key=761fc4e2bffe6ed2997b3626a642c3e0")
    Observable<NewsResponse> getNews(@Header("Cache-Control") String cacheControl);

    /**
     * 获取微信精选列表
     */
    @FormUrlEncoded
    @POST("weixin/query")
    Observable<WeChatResponse> getWeChat(@FieldMap Map<String, String> params);

    /**
     * 获取微信精选列表
     */
    @FormUrlEncoded
    @POST("/joke/content/text.from")
    Observable<JokeResponse> getJokeInfo(@FieldMap Map<String, String> params);

    @GET("data/福利/{size}/{page}")
    Observable<PhotoViewResponse> getPhotoList(
            @Header("Cache-Control") String cacheControl, @Path("size") int size, @Path("page") int page);

    @GET("/onebox/weather/query")
    Observable<WeatherResponse> getWeatherReport(@Query("cityname")String cityname, @Query("key")String key);



}
