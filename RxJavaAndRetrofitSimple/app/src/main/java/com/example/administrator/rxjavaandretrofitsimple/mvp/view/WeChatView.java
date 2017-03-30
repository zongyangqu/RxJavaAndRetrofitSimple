package com.example.administrator.rxjavaandretrofitsimple.mvp.view;

import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.base.BaseView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/20
 *
 * 类描述：微信精选
 */

public interface WeChatView extends BaseView<WeChatResponse>{

    /**
     * 更新数据
     */
    void provideWeChat(WeChatResponse response, boolean isLoadMore);
}
