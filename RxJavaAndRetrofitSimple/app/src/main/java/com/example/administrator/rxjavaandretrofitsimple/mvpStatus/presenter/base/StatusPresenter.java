package com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.base;

import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base.StatusView;

import java.lang.ref.SoftReference;

/**
 * 作者：quzongyang
 * <p>
 * 创建时间：2017/3/20
 * <p>
 * 类描述：
 */

public class StatusPresenter <V extends StatusView>{
    private SoftReference<V> mViewHolder;

    public void attachView(V view) {
        if (view == null) {
            throw new NullPointerException("Presenter can't attach to null.");
        }
        mViewHolder = new SoftReference(view);
    }

    public final boolean isViewAttaching() {
        return mViewHolder != null && mViewHolder.get() != null;
    }

    protected final V getView() {
        return mViewHolder.get();
    }

    public void detachView() {
        if (isViewAttaching()) {
            mViewHolder.clear();
            mViewHolder = null;
        }
    }
}
