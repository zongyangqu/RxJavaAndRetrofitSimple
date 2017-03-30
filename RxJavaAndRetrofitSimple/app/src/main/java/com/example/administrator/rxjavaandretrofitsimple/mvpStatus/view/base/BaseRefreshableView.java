package com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/30
 *
 * 类描述：基类View带有请求状态
 */

public interface BaseRefreshableView extends StatusView{
    void refreshCompleted();

    void displayEmptyPage();

    void displayErrorPage();

    void displayNoMoreTip();
}
