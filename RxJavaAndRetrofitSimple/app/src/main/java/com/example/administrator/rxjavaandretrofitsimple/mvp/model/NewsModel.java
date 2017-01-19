package com.example.administrator.rxjavaandretrofitsimple.mvp.model;

import com.example.administrator.rxjavaandretrofitsimple.bean.NewsBean;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.base.BaseModel;

import java.util.List;
import java.util.Map;

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
     *
     * @param type
     * @return
     */
    public Observable<NewsBean> getNews(String type) {
        //Map<String, String> params = NetParams.getInstance().WaitGrabSearch(user_id,position_x, position_y,OrderIDList);
        return getClientService().getNews();
    }
}
