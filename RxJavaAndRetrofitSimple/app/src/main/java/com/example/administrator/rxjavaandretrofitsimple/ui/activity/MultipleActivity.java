package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.MultiplePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.MultipleView;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.basewithoutmodel.BaseStatusMvpActivity;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/13
 *
 * 类描述：
 */

public class MultipleActivity extends BaseStatusMvpActivity<MultipleView, MultiplePresenter> implements MultipleView {

    @Override
    protected MultiplePresenter createPresenter() {
        return new MultiplePresenter();
    }

    @Override
    protected void onViewCreated() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_multiple;
    }

    @Override
    protected void initTitleBar() {
        showTitle(false);
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    public void refreshCompleted() {

    }

    @Override
    public void displayEmptyPage() {

    }

    @Override
    public void displayErrorPage() {

    }

    @Override
    public void displayNoMoreTip() {

    }
}
