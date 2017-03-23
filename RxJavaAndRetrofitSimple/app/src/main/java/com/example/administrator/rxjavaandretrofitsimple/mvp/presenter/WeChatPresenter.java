package com.example.administrator.rxjavaandretrofitsimple.mvp.presenter;

import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.WeChatModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.WeChatView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/21
 *
 * 类描述：微信精选
 */

public class WeChatPresenter extends BasePresenter<WeChatView, WeChatModel> {
    /**
     * 获取微信精选
     * @param pno
     * @param ps
     * @param key
     *@param isLoadMore  是否是上拉加载更多的请求
     */
    public void getWeChat(int pno, int ps, String key, final boolean isLoadMore) {
        Observable<WeChatEntity> weChatObservable = getModel().getWeChat(String.valueOf(pno),String.valueOf(ps),key);
        Subscriber<WeChatEntity> subscriber = new Subscriber<WeChatEntity>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoadingView();
                getView().showError("网络异常");
            }

            @Override
            public void onNext(WeChatEntity result) {
                if(result.result.list.isEmpty()){
                    getView().displayEmptyPage();
                }else{
                    getView().provideWeChat(result,isLoadMore);
                }
            }
        };
        weChatObservable.doOnNext(new Action1<WeChatEntity>() {
            @Override
            public void call(WeChatEntity entity) {

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                getView().startLoadingView();
            }
        }).subscribe(subscriber);
        addSubscrib(subscriber);
    }
}

