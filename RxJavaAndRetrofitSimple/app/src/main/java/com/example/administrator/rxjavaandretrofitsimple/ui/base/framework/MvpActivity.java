package com.example.administrator.rxjavaandretrofitsimple.ui.base.framework;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.base.StatusPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base.StatusView;


/**
 * 作者：quzongyang
 * <p>
 * 创建时间：2017/3/20
 * <p>
 * 类描述：
 */

public abstract class MvpActivity<V extends StatusView, P extends StatusPresenter<V>> extends AppCompatActivity implements StatusView {

    protected P _presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _presenter = createPresenter();
        if (_presenter == null) {
            throw new NullPointerException("createPresenter() can't be return null object.");
        }
        _presenter.attachView(((V) this));
    }

    protected abstract P createPresenter();

    @Override
    public Activity visitActivity() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (_presenter != null) {
            _presenter.detachView();
        }
    }
}
