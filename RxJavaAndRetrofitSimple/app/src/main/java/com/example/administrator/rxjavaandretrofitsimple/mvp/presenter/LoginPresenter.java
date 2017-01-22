package com.example.administrator.rxjavaandretrofitsimple.mvp.presenter;

import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.LoginEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.LoginModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.base.BaseView;
import com.example.administrator.rxjavaandretrofitsimple.util.db.UserDao;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 类描述：模拟登陆
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 11:21
 * 版本：
 */
public class LoginPresenter extends BasePresenter<BaseView<LoginEntity>, LoginModel> {

    public void login(String key,String ip){
        /*Observable<LoginEntity> codeEntityObservable = getModel().login(key,ip);
        Subscriber<LoginEntity> subscriber = new Subscriber<LoginEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().showError("网络异常");
                getView().hideLoadingView();
            }

            @Override
            public void onNext(LoginEntity result) {
                getView().hideLoadingView();
                getView().updateView(result);
            }
        };
        *//**
         * 切换到IO线程保存用户信息到数据库
         *//*
        codeEntityObservable.observeOn(Schedulers.io()).doOnNext(new Action1<LoginEntity>() {
            @Override
            public void call(LoginEntity resutl) {
                LoginEntity.UserBean entity = resutl.getResult();
                if (entity != null) {
                    UserDao tempUserDao = UserDao.getInstance(BaseApplication.getInstance());
                    tempUserDao.saveUserEntity(entity);
                }
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Action0() {
            @Override
            public void call() {
                getView().startLoadingView();
            }
        }).subscribe(subscriber);
        addSubscrib(subscriber);*/
    }

}
