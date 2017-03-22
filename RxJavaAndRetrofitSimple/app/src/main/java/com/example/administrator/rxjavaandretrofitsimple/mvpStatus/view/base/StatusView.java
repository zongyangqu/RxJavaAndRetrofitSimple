package com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base;

import android.app.Activity;
import android.support.annotation.StringRes;

/**
 * 作者：quzongyang
 * <p>
 * 创建时间：2017/3/20
 * <p>
 * 类描述：
 */

public interface StatusView {

    void processingDialog();

    void processingDialog(@StringRes int msgResId);

    void dismissProcessingDialog();

    Activity visitActivity();

}
