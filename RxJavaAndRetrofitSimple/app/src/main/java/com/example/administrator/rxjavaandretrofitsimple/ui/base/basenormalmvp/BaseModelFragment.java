package com.example.administrator.rxjavaandretrofitsimple.ui.base.basenormalmvp;


import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.framework.LazyFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.OnRetryListener;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.OnShowHideViewListener;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.StatusLayoutManager;
import butterknife.ButterKnife;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：懒加载Fragment基类(使用此基类的子Fragment具有Model层)
 */
public abstract class BaseModelFragment extends LazyFragment {
    protected StatusLayoutManager statusLayoutManager;
    private View view;
    @Override
    protected void onCreateViewLazy(Bundle bundle) {
        view = View.inflate(getContext(), R.layout.fragment_base,null);
        setContentView(view);
        initStatusLayout();
        ButterKnife.bind(this, getContentView());
        onViewCreatedLazily(bundle);
    }

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
        if (getCurrentPersenter() != null) {
            getCurrentPersenter().unsubcrib();

        }
    }
}
