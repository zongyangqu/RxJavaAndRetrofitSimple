package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.WeChatModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.WeChatPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.WeChatView;
import com.example.administrator.rxjavaandretrofitsimple.ui.adapter.WeChatAdapter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseModelFragment;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.ProgressDialogUtils;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：微信精选
 */

public class WeChatFragment extends BaseModelFragment implements WeChatView{
    private WeChatPresenter _presenter;
    private WeChatAdapter adapter;
    @Bind(R.id.rcv_weChat)
    RecyclerView rcv_weChat;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void onViewCreatedLazily(Bundle bundle) {
        rcv_weChat.setLayoutManager(new LinearLayoutManager(getActivity()));
        rcv_weChat.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WeChatAdapter(getActivity());
        rcv_weChat.setAdapter(adapter);
        _presenter = new WeChatPresenter();
        _presenter.attachView(this);
        _presenter.attachModel(new WeChatModel());
        _presenter.getWeChat("1","25","5c6868ae0010858fab351ac83921d9b3");
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        AbToastUtil.showToast(getActivity(),isVisibleToUser+"");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        AbToastUtil.showToast(getActivity(),"Wechat-->"+hidden);
    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return _presenter;
    }

    @Override
    public void updateView(WeChatEntity response) {
        adapter.setData(response.result.list);
    }

    @Override
    public void hideLoadingView() {
        ProgressDialogUtils.dismiss();
    }

    @Override
    public void startLoadingView() {
        AbToastUtil.showToast(getActivity(),"start");
        ProgressDialogUtils.show(getActivity());
    }

    @Override
    public void showError(String errMsg) {

    }
}
