package com.example.administrator.rxjavaandretrofitsimple.ui.base.framework;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.base.StatusPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base.StatusView;

import java.lang.ref.SoftReference;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/22
 *
 * 类描述：
 */

public abstract class MvpLazyFragment<V extends StatusView, P extends StatusPresenter<V>> extends LazyFragment implements StatusView {

    protected P _presenter;
    private SoftReference<Activity> actHolder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        actHolder = new SoftReference(context);
        _presenter = createPresenter();
        if (_presenter == null) {
            throw new NullPointerException("createPresenter() can't return null object.");
        }
        _presenter.attachView(((V) this));
    }

    protected abstract P createPresenter();

    @Override
    public Activity visitActivity() {
        return getActivity() == null ? actHolder.get() : getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (_presenter != null) {
            _presenter.detachView();
        }
        if (actHolder != null) {
            actHolder.clear();
            actHolder = null;
        }
    }
}

