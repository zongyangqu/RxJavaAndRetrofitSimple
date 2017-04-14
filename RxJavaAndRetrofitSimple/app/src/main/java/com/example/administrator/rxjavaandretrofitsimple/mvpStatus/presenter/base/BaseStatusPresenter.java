package com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.base;

import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base.BaseStatusView;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/20
 *
 * 类描述：P层抽象基类
 */

public abstract class BaseStatusPresenter<V extends BaseStatusView> extends StatusPresenter<V> {

    private CompositeSubscription subscriptions;

    @Override
    public void attachView(V view) {
        super.attachView(view);
        registerObservers();
    }

    protected void registerObservers() {
    }

    public void addSubscription(Subscription subscription) {
        if (subscriptions == null) {
            subscriptions = new CompositeSubscription();
        }
        subscriptions.add(subscription);
    }

    @Override
    public void detachView() {
        if (subscriptions != null && !subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }
        super.detachView();
    }
}
