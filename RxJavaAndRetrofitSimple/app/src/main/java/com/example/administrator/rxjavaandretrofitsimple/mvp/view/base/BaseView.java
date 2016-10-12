package com.example.administrator.rxjavaandretrofitsimple.mvp.view.base;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/10/11. 19:55
 * 版本：
 */
public interface BaseView<T> {
    /**
     * 更新数据
     */
    void updateView(T t);

    /**
     * 隐藏加载框
     */
    void hideLoadingView();

    /**
     * 开始加载框
     */
    void startLoadingView();

    /**
     * 显示错误信息
     */
    void showError(String errMsg);
}
