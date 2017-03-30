package com.example.administrator.rxjavaandretrofitsimple.mvp.presenter;

import com.example.administrator.rxjavaandretrofitsimple.api.RxNetworkResponseObserver;
import com.example.administrator.rxjavaandretrofitsimple.bean.NewsResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.NewsModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.NewsView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：新闻
 *
 * ApiManager.getCacheControl配置缓存如果不需要可以不加
 */

public class NewsPresenter extends BasePresenter<NewsView, NewsModel> {
    /**
     * 获取新闻信息
     * @param type  新闻类型
     * top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     */
    public void getNews() {
        getView().startLoadingView();
        Observable<NewsResponse> codeEntityObservable = getModel().getNews();
        RxNetworkResponseObserver<NewsResponse> subscriber = new RxNetworkResponseObserver<NewsResponse>() {
            @Override
            public void onBeforeResponseOperation() {
                super.onBeforeResponseOperation();
                getView().hideLoadingView();
            }

            @Override
            public void onResponseFail(String msg) {
                getView().showError(msg);
            }

            @Override
            public void onResponse(NewsResponse result) {
                getView().updateView(result);
            }

            @Override
            public void onResponseStatusFail(String msgCode, String msg) {
                getView().showError(msg);
            }
        };
        codeEntityObservable.doOnNext(new Action1<NewsResponse>() {
            @Override
            public void call(NewsResponse entity) {

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
