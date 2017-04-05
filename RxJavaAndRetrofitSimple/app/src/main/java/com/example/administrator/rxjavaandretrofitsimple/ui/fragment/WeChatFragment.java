package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.qzy.library.refreshview.SpringView;
import com.android.qzy.library.refreshview.container.MeituanFooter;
import com.android.qzy.library.refreshview.container.MeituanHeader;
import com.android.qzy.library.refreshview.container.RotationFooter;
import com.android.qzy.library.refreshview.container.RotationHeader;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.WeChatModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.WeChatPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.WeChatView;
import com.example.administrator.rxjavaandretrofitsimple.ui.adapter.WeChatAdapter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseModelFragment;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;

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
    @Bind(R.id.springview)
    SpringView springView;
    private int total_page;
    private int currentPage = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void onViewCreatedLazily(Bundle bundle) {
        setListener();
        initView();
    }

    public void initView(){
        rcv_weChat.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WeChatAdapter(getActivity());
        rcv_weChat.setAdapter(adapter);
        View headerView = View.inflate(getActivity(),R.layout.item_wechat_head,null);
        adapter.addHeaderView(headerView);
        _presenter = new WeChatPresenter();
        _presenter.attachView(this);
        _presenter.attachModel(new WeChatModel());
        refreshWeChatInfo(1,false);
    }

    public void setListener(){
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                refreshWeChatInfo(1,false);
            }
            @Override
            public void onLoadmore() {
                if(total_page>currentPage){
                    currentPage = currentPage+1;
                    refreshWeChatInfo(currentPage,true);
                }else{
                    springView.onFinishFreshAndLoad();
                    AbToastUtil.showToast(getActivity(),"没有更多了，亲！");
                }
            }
        });
        springView.setHeader(new MeituanHeader(getActivity()));
        springView.setFooter(new MeituanFooter(getActivity()));
//        springView.setHeader(new RotationHeader(getActivity()));
//        springView.setFooter(new RotationFooter(getActivity()));
    }

    @Override
    protected void onRetryClick() {
        refreshWeChatInfo(1,false);
    }


    /**
     * 调用接口
     * @param currentPage   当前页数
     * @param isLoadMore  是否是上拉加载更多
     */
    public void refreshWeChatInfo(int currentPage,boolean isLoadMore){
        _presenter.getWeChat(currentPage, LocalConstant.DEFAULT_PAGE,LocalConstant.WECHAT_REQURST_KAY,isLoadMore);
    }


    @Override
    protected BasePresenter getCurrentPersenter() {
        return _presenter;
    }

    @Override
    public void provideWeChat(WeChatResponse response, boolean isLoadMore) {
        springView.onFinishFreshAndLoad();
        statusLayoutManager.showContent();
        total_page = response.result.totalPage;
        if(isLoadMore){//加载更多
            adapter.addData(response.result.list);
        }else{//刷新
            adapter.setData(response.result.list);
        }
    }



    @Override
    public void displayEmptyPage() {
        statusLayoutManager.showEmptyData();
    }


    @Override
    public void updateView(WeChatResponse response) {
    }

    @Override
    public void hideLoadingView() {
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void startLoadingView() {
    }

    @Override
    public void showError(String errMsg) {
        statusLayoutManager.showNetWorkError();
    }
}
