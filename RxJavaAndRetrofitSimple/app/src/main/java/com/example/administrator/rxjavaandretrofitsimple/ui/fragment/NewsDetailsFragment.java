package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.api.ApiManager;
import com.example.administrator.rxjavaandretrofitsimple.bean.NewsEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.NewsDetailsPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.NewsDetailsView;
import com.example.administrator.rxjavaandretrofitsimple.ui.adapter.NewsAdapter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseMvpLazyFragment;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/29
 *
 * 类描述：新闻（此界面使用了网络缓存）
 */

public class NewsDetailsFragment extends BaseMvpLazyFragment<NewsDetailsView,NewsDetailsPresenter> implements NewsDetailsView{

    private String requestType;
    private NewsAdapter newsAdapter;
    @Bind(R.id.rcv_news)
    RecyclerView rcv_news;

    public static NewsDetailsFragment getInstance(String requestType) {
        NewsDetailsFragment fragment = new NewsDetailsFragment();
        fragment.requestType = requestType;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_details;
    }

    @Override
    protected void onViewCreatedLazily(Bundle bundle) {
        rcv_news.setLayoutManager(new LinearLayoutManager(getActivity()));
        newsAdapter = new NewsAdapter(getActivity());
        rcv_news.setAdapter(newsAdapter);
        _presenter.getNewsInfo(requestType,ApiManager.getCacheControl());
    }

    @Override
    public void provideNewsInfo(NewsEntity.ResultBean response) {
        statusLayoutManager.showContent();
        newsAdapter.setData(response.data);
    }

    @Override
    protected NewsDetailsPresenter createPresenter() {
        return new NewsDetailsPresenter();
    }

    @Override
    protected void onRetryClick() {

    }
}
