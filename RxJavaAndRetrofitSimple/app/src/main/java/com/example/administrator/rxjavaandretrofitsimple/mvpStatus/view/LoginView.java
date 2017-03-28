package com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view;

import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base.BaseStatusView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/24
 *
 * 类描述：登录View
 */

public interface LoginView extends BaseStatusView {

    void registerSuccess(String userPhone,String password);
}
