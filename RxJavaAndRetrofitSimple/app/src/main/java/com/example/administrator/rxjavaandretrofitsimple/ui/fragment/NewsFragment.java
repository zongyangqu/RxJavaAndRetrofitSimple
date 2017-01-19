package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.NewsBean;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.NewsModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.NewsPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.NewsView;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseFragment;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：新闻
 */

public class NewsFragment extends BaseFragment implements NewsView{

    private NewsPresenter _presenter;
    @Override
    protected BasePresenter getCurrentPersenter() {
        return _presenter;
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = onCreateView(R.layout.fragment_news, inflater, container, savedInstanceState);
        return rootView;
    }

    @Override
    protected void initData() {
        _presenter = new NewsPresenter();
        _presenter.attachView(this);
        _presenter.attachModel(new NewsModel());
        _presenter.getNews("top");
    }

    @Override
    public void RefreshFragment() {

    }

    @Override
    public void updateView(NewsBean response) {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void startLoadingView() {

    }

    @Override
    public void showError(String errMsg) {

    }
}
