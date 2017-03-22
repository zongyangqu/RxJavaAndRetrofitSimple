package com.example.administrator.rxjavaandretrofitsimple.api;

import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.ProgressDialogUtils;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/20
 *
 * 类描述：
 */

public abstract class SimpleResponseObserver<T> extends RxNetworkResponseObserver<T> {

    @Override
    public void onBeforeResponseOperation() {
        ProgressDialogUtils.dismiss();
    }

    @Override
    public void onResponseFail(String s) {
        AbToastUtil.showToast(BaseApplication.getInstance(),s);
        //TipToast.shortTip(s);
    }

    @Override
    public void onResponseStatusFail(String s, String s1) {
        AbToastUtil.showToast(BaseApplication.getInstance(),s1);
    }
}