package com.example.administrator.rxjavaandretrofitsimple.ui.base;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.LinearLayout;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.base.BaseStatusPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base.BaseStatusView;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base.StatusView;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.framework.MvpLazyFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.OnRetryListener;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.OnShowHideViewListener;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.StatusLayoutManager;
import com.example.administrator.rxjavaandretrofitsimple.util.ProgressDialogUtils;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/22
 *
 * 类描述：懒加载Fragment基类(使用此基类的子Fragment没有写Model层)
 */

public abstract class BaseMvpLazyFragment<V extends BaseStatusView, P extends BaseStatusPresenter<V>> extends MvpLazyFragment<V, P> implements StatusView {

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

    @Override
    public void processingDialog() {
        ProgressDialogUtils.show(getActivity());
    }

    @Override
    public void processingDialog(@StringRes int i) {
        ProgressDialogUtils.show(getActivity(), i);
    }

    @Override
    public void dismissProcessingDialog() {
        ProgressDialogUtils.dismiss();
    }

    /**
     * 加载错误重试
     */
    protected abstract void onRetryClick();

    private CompositeSubscription compositeSubscription;

    /**
     * @param subscription will be unsubscribed until fragment ui destroyed.
     */
    protected void addViewSubscription(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    @Override
    public void onDestroyViewLay() {
        super.onDestroyViewLay();
        ButterKnife.unbind(this);
        if (compositeSubscription != null && compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
    }
}

