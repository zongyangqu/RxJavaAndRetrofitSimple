package com.example.administrator.rxjavaandretrofitsimple.ui.base.basestandardmvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.base.BaseModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.model.base.BaseStandardModel;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.presenter.base.BaseStandardPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.utils.RxManager;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.utils.TUtil;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.framework.LazyFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.OnRetryListener;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.OnShowHideViewListener;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.StatusLayoutManager;

import butterknife.ButterKnife;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/19
 *
 * 类描述：标准化MVP架构Fragment基类
 */

public abstract class BaseStandardMVPFragment <T extends BaseStandardPresenter, E extends BaseStandardModel> extends LazyFragment {

    protected StatusLayoutManager statusLayoutManager;
    private View view;
    public T mPresenter;
    public E mModel;
    public RxManager mRxManager;
    @Override
    protected void onCreateViewLazy(Bundle bundle) {
        view = View.inflate(getContext(), R.layout.fragment_base,null);
        setContentView(view);
        initStatusLayout();
        mRxManager=new RxManager();
        ButterKnife.bind(this, getContentView());
        mPresenter = TUtil.getT(this, 0);
        mModel= TUtil.getT(this,1);
        if(mPresenter!=null){
            mPresenter.mContext=this.getActivity();
        }
        initPresenter();
        onViewCreatedLazily(bundle);
    }

    //简单页面无需mvp就不用管此方法即可,完美兼容各种实际场景的变通
    public abstract void initPresenter();

    protected abstract int getLayoutId();

    protected abstract void onViewCreatedLazily(Bundle bundle);

    protected void initStatusLayout(){
        LinearLayout mainLinearLayout = (LinearLayout) view.findViewById(R.id.main_rl);
        statusLayoutManager = StatusLayoutManager.newBuilder(getContext())
                .contentView(getLayoutId())
                .emptyDataView(R.layout.activity_emptydata)
                .errorView(R.layout.activity_error)
                .loadingView(R.layout.activity_loading)
                .netWorkErrorView(R.layout.activity_networkerror)
                .retryViewId(R.id.button_try)
                .onShowHideViewListener(new OnShowHideViewListener() {
                    @Override
                    public void onShowView(View view, int id) {
                    }

                    @Override
                    public void onHideView(View view, int id) {
                    }
                }).onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry() {
                        onRetryClick();
                    }
                }).build();
        mainLinearLayout.addView(statusLayoutManager.getRootLayout(),0);
        statusLayoutManager.showContent();
    }
    /**
     * 加载错误重试
     */
    protected abstract void onRetryClick();

    protected abstract BasePresenter getCurrentPersenter(); //获取当前的业务处理类



    @Override
    public void onDestroyViewLay() {
        super.onDestroyViewLay();
        ButterKnife.unbind(this);
        if (mPresenter != null)
            mPresenter.onDestroy();
        mRxManager.clear();
    }
}
