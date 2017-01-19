package com.example.administrator.rxjavaandretrofitsimple.listener;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：actvity和fragment通信接口
 */

public interface OnActivitySelectListenner {
    void showLoginDialog(String message);

    void showErrorMessageg(String error);

    void showSuccessMessage(String msg);

    void showInfoMessage(String msg);

    void dismissloading();
}
