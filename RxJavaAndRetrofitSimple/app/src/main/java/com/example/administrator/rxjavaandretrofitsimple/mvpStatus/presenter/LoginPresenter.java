package com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter;

import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.base.BaseStatusPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.LoginView;
import com.example.administrator.rxjavaandretrofitsimple.util.rx.RxBusEvent;
import com.example.administrator.rxjavaandretrofitsimple.util.rx.event.RegisterEvent;

import rx.functions.Action1;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/24
 *
 * 类描述：登录逻辑
 */

public class LoginPresenter extends BaseStatusPresenter<LoginView> {

    @Override
    protected void registerObservers() {
        super.registerObservers();
        /**
         * 接受RxBusEvent发送的注册成功监听
         */
        addSubscription(RxBusEvent.toObservable(RegisterEvent.class)
                .subscribe(new Action1<RegisterEvent>() {
                    @Override
                    public void call(RegisterEvent registerEvent) {
                        if (isViewAttaching()) {
                            getView().registerSuccess(registerEvent.userPhone,registerEvent.password);
                        }
                    }
                }));
    }
}
