package com.example.administrator.rxjavaandretrofitsimple.api;

import com.example.administrator.rxjavaandretrofitsimple.bean.NewsEntity;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatEntity;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/19
 *
 * 类描述：请求接口类
 */

public interface ApiService {

    /**
     * 获取新闻列表
     * @param cacheControl  网络请求缓存策略
     * @return
     */
    @GET("toutiao/index?type=top&key=761fc4e2bffe6ed2997b3626a642c3e0")
    Observable<NewsEntity> getNews(@Header("Cache-Control") String cacheControl);


    /**
     * 获取微信精选列表
     */
    @FormUrlEncoded
    @POST("weixin/query")
    Observable<WeChatEntity> getWeChat(@FieldMap Map<String, String> params);

}
