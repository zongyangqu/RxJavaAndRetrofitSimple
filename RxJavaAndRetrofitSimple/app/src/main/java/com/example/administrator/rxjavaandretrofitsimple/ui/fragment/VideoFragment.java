package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseFragment;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：视频
 */

public class VideoFragment extends BaseFragment{
    @Override
    protected BasePresenter getCurrentPersenter() {
        return null;
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = onCreateView(R.layout.fragment_video, inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void RefreshFragment() {

    }
}
