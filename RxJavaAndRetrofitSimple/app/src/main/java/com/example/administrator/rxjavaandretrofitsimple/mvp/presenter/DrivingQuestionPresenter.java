package com.example.administrator.rxjavaandretrofitsimple.mvp.presenter;

import com.example.administrator.rxjavaandretrofitsimple.bean.DrivingQuestionEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.DrivingQuestionModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.DrivingQuestionView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 17:04
 * 版本：
 */
public class DrivingQuestionPresenter extends BasePresenter<DrivingQuestionView,DrivingQuestionModel>{
    /**
     *
      * @param key 申请的appKey
     * @param subject 选择考试科目类型，1：科目1；4：科目4
     * @param model 驾照类型，可选择参数为：c1,c2,a1,a2,b1,b2；当subject=4时可省略
     */
    public void getDrivingQuestion(String key,String subject,String model) {
        /*Observable<DrivingQuestionEntity> codeEntityObservable = getModel().getQuestion(key, subject, model);
        Subscriber<DrivingQuestionEntity> subscriber = new Subscriber<DrivingQuestionEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().hideLoadingView();
                getView().showError("网络异常");
            }

            @Override
            public void onNext(DrivingQuestionEntity result) {
                getView().hideLoadingView();
                getView().updateView(result);
            }
        };
        codeEntityObservable.doOnNext(new Action1<DrivingQuestionEntity>() {
            @Override
            public void call(DrivingQuestionEntity entity) {

            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                getView().startLoadingView();
            }
        }).subscribe(subscriber);*/
    }
}
