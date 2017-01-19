package com.example.administrator.rxjavaandretrofitsimple.api;

import com.example.administrator.rxjavaandretrofitsimple.bean.NewsBean;

import retrofit2.http.GET;
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
     */
    @GET("toutiao/index?type=top&key=761fc4e2bffe6ed2997b3626a642c3e0")
    Observable<NewsBean> getNews();

}
