package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.WeChatModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.WeChatPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.WeChatView;
import com.example.administrator.rxjavaandretrofitsimple.ui.adapter.WeChatAdapter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseFragment;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：微信精选
 */

public class WeChatFragment extends BaseFragment implements WeChatView{
    private WeChatPresenter _presenter;
    private WeChatAdapter adapter;
    private RecyclerView rcv_weChat;
    @Override
    protected BasePresenter getCurrentPersenter() {
        return _presenter;
    }

    @Override
    protected View initViews(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = onCreateView(R.layout.fragment_wechat, inflater, container, savedInstanceState);
        findViewByID(rootView);
        return rootView;
    }

    public void findViewByID(View rootView){
        rcv_weChat = (RecyclerView) rootView.findViewById(R.id.rcv_weChat);
        rcv_weChat.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WeChatAdapter(getActivity());
        rcv_weChat.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        _presenter = new WeChatPresenter();
        _presenter.attachView(this);
        _presenter.attachModel(new WeChatModel());
        _presenter.getWeChat("1","25","5c6868ae0010858fab351ac83921d9b3");
    }

    @Override
    public void RefreshFragment() {

    }

    @Override
    public void updateView(WeChatEntity response) {
        adapter.setData(response.result.list);
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
