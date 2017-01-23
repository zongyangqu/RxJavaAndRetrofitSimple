package com.example.administrator.rxjavaandretrofitsimple.mvp.model;

import com.example.administrator.rxjavaandretrofitsimple.api.ApiManager;
import com.example.administrator.rxjavaandretrofitsimple.bean.NewsEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.base.BaseModel;

import rx.Observable;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：
 */

public class NewsModel extends BaseModel{

    /**
     * 获取新闻
     * ApiManager.getCacheControl() 获取缓存（不需要可以不加，目前只支持GET请求）
     * @return
     */
    public Observable<NewsEntity> getNews() {
        return getClientService().getNews(ApiManager.getCacheControl());
    }
}
