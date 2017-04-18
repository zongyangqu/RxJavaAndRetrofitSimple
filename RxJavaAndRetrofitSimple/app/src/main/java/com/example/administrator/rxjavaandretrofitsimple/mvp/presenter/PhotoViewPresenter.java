package com.example.administrator.rxjavaandretrofitsimple.mvp.presenter;
import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.PhotoViewModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.PhotoView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/18
 *
 * 类描述：美图
 */

public class PhotoViewPresenter extends BasePresenter<PhotoView, PhotoViewModel> {

    /**
     * 获取美图
     * @param cacheControl
     * @param size
     * @param page
     *@param isLoadMore  是否是上拉加载更多的请求
     */
    public void getPhoto(String cacheControl, int size, int page, final boolean isLoadMore) {
        Observable<PhotoViewResponse> weChatObservable = getModel().getPhotoList(cacheControl,size,page);
        Subscriber<PhotoViewResponse> subscriber = new Subscriber<PhotoViewResponse>() {
            @Override
            public void onCompleted() {
                getView().hideLoadingView();
            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoadingView();
                getView().showError("网络异常");
            }

            @Override
            public void onNext(PhotoViewResponse result) {
                if(result.results.isEmpty()){
                    getView().displayEmptyPage();
                }else{
                    getView().providePhotoView(result,isLoadMore);
                }
            }
        };
        weChatObservable.doOnNext(new Action1<PhotoViewResponse>() {
            @Override
            public void call(PhotoViewResponse entity) {

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                getView().startLoadingView();
            }
        }).subscribe(subscriber);
        addSubscrib(subscriber);//用于取消订阅时使用
    }
}
