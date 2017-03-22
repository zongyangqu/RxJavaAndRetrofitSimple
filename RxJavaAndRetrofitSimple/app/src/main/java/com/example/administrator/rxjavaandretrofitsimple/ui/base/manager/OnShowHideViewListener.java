package com.example.administrator.rxjavaandretrofitsimple.ui.base.manager;

import android.view.View;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/18
 *
 * 类描述：基类Activity布局监听
 */
public interface OnShowHideViewListener {

    void onShowView(View view, int id);
    void onHideView(View view, int id);
}
