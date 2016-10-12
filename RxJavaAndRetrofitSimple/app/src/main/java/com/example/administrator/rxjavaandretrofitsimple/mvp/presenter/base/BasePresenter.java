package com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base;

import com.example.administrator.rxjavaandretrofitsimple.mvp.model.base.BaseModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.base.BaseView;
import com.example.administrator.rxjavaandretrofitsimple.util.AbLogUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/10/11. 19:55
 * 版本：
 */
public abstract class BasePresenter<V extends BaseView, M extends BaseModel> implements PresenterHelper<V, M> {
    private List<Subscriber> mSubscribers = new ArrayList<>();//集合用于添加订阅对象
    private V mView;
    private M mModel;

    public BasePresenter() {
    }

    /**
     * 取消任务订阅
     **/
    public void unsubcrib() {
        try {
            if (mSubscribers != null && !mSubscribers.isEmpty()) {
                for (int i = 0; i < mSubscribers.size(); i++) {
                    Subscriber mSubscriber = mSubscribers.get(i);
                    if (mSubscriber != null && !mSubscriber.isUnsubscribed()) {
                        mSubscriber.unsubscribe();
                        AbLogUtil.i("basePresenter", mSubscriber.getClass().getSimpleName() + "取消订阅成功");
                    }
                }
            }
        } catch (Exception e) {
            AbLogUtil.i("basePresenter", "取消订阅失败....");
        }
    }

    /**
     * 添加当前的订阅对象到集合中去
     **/
    public void addSubscrib(Subscriber _subscriber) {
        mSubscribers.add(_subscriber);
    }

    /***
     * 设置视图
     *
     * @param _view
     */
    @Override
    public void attachView(V _view) {
        this.mView = _view;
    }

    @Override
    public void attachModel(M _model) {
        this.mModel = _model;
    }

    /***
     * 销毁视图
     */
    @Override
    public void detachView() {
        this.mView = null;
    }

    /**
     * 获取视图
     *
     * @return
     */
    public V getView() {
        return mView;
    }

    /**
     * 获取数据源
     *
     * @return
     */
    public M getModel() {
        return mModel;
    }
}