package com.example.administrator.rxjavaandretrofitsimple.mvpStandard.presenter.base;

import android.content.Context;

import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.utils.RxManager;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/19
 *
 * 类描述：MVP标准化P层基类
 */

public abstract class BaseStandardPresenter <T,E>{
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManage.clear();
    }
}
