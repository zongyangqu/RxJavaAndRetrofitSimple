package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseModelFragment;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：视频
 */

public class VideoFragment extends BaseModelFragment {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void onViewCreatedLazily(Bundle bundle) {

    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return null;
    }
}
