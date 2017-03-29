package com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view;

import com.example.administrator.rxjavaandretrofitsimple.bean.NewsEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base.BaseStatusView;

/**
 * 作者：quzongyang

 * 创建时间：2017/3/29

 * 类描述：
 */

public interface NewsDetailsView extends BaseStatusView{
    void provideNewsInfo(NewsEntity.ResultBean response);
}
