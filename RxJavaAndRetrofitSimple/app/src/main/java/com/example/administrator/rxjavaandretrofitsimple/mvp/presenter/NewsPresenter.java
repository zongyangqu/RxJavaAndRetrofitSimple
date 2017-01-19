package com.example.administrator.rxjavaandretrofitsimple.mvp.presenter;

import com.example.administrator.rxjavaandretrofitsimple.bean.NewsBean;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.NewsModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.NewsView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
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
 */

public class NewsPresenter extends BasePresenter<NewsView, NewsModel> {
    /**
     * 获取新闻信息
     * @param type  新闻类型
     * top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     */
    public void getNews(String type) {
        Observable<NewsBean> codeEntityObservable = getModel().getNews(type);
        Subscriber<NewsBean> subscriber = new Subscriber<NewsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoadingView();
                getView().showError("网络异常");
            }

            @Override
            public void onNext(NewsBean result) {
                getView().hideLoadingView();
                getView().updateView(result);
            }
        };
        codeEntityObservable.doOnNext(new Action1<NewsBean>() {
            @Override
            public void call(NewsBean entity) {

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
