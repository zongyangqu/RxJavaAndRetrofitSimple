package com.example.administrator.rxjavaandretrofitsimple.mvpStandard.view;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/19
 *
 * 类描述：
 */

public interface BaseStandardView {


    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
