package com.example.administrator.rxjavaandretrofitsimple.ui.base;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.base.BaseStatusPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base.BaseStatusView;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.framework.MvpActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.OnRetryListener;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.OnShowHideViewListener;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.StatusLayoutManager;
import com.example.administrator.rxjavaandretrofitsimple.util.AppManager;
import com.example.administrator.rxjavaandretrofitsimple.util.ProgressDialogUtils;
import com.example.administrator.rxjavaandretrofitsimple.util.statusBar.StatusBarJobber;
import com.example.administrator.rxjavaandretrofitsimple.util.widget.ToolbarWrapper;

import butterknife.ButterKnife;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/20
 *
 * 类描述：Activity基类(使用此基类的子Activity没有写Model层)
 */

public abstract class BaseStatusMvpStatusActivity<V extends BaseStatusView, P extends BaseStatusPresenter<V>> extends MvpActivity<V, P> implements BaseStatusView {

    protected ToolbarWrapper toolbar;
    private CompositeSubscription compositeSubscription;
    protected StatusLayoutManager statusLayoutManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(R.layout.activity_base);
        initStatusLayout();
        if (butterKnifeEnabled()) {
            ButterKnife.bind(this);
        }
        toolbar = ((ToolbarWrapper) findViewById(R.id.toolbar));
        if (toolbar != null) {
            configToolbar();
        }
        initTitleBar();
        if (lightStatusBarMode()) {
            if (StatusBarJobber.switchStatusBarElementTo(this, true)) {
                StatusBarJobber.setTranslucentStatusColor(this, R.color.white);
            }
        }
        onViewCreated();
    }


    protected void initStatusLayout(){
        LinearLayout mainLinearLayout = (LinearLayout) findViewById(R.id.main_rl);
        statusLayoutManager = StatusLayoutManager.newBuilder(this)
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
        mainLinearLayout.addView(statusLayoutManager.getRootLayout(), 1);
        statusLayoutManager.showContent();
    }

    protected boolean butterKnifeEnabled() {
        return true;
    }

    protected void beforeSetContentView() {
        AppManager.getInstance().addActivity(this);
    }

    protected boolean lightStatusBarMode() {
        return true;
    }

    /**
     * Config the toolbar, invoked before {@link #onViewCreated()}
     */
    protected void configToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
    }

    protected void onBack() {
        finish();
    }

    protected abstract void onViewCreated();

    @LayoutRes
    protected abstract int getLayoutId();


    /**
     * 设置标题栏
     */
    protected abstract void initTitleBar();

    /**
     * 加载错误重试
     */
    protected abstract void onRetryClick();

    protected void addViewSubscription(Subscription subscription) {
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    /**
     * 设置中间标题文字颜色
     * @param color
     */
    protected void setTitleColor(String color){
        toolbar.setTitleColor(color);
    }

    /**
     * 设置中间标题文字
     * @param title
     */
    protected void setTitleCenter(String title){
        toolbar.setCenterTitle(title);
    }

    /**
     * 隐藏标题栏左侧
     */
    protected void hiddenTitleLeft(){
        toolbar.setNavigationIcon(null);
    }

    /**
     * 设置标题栏颜色
     */
    protected void setTitleBackgroundColor(int color) {
        toolbar.setBackgroundColor(color);
    }

    /**
     * 隐藏标题栏左侧图片
     */
    protected void setTitleLeftIcon(@Nullable Drawable icon) {
        toolbar.setNavigationIcon(icon);
    }

    /**
     * 设置右侧标题文字
     * @param title
     */
    protected void setTitleRightText(String title){
        toolbar.setRightButtonText(title);
    }

    /**
     * 获取右侧标题
     */
    protected TextView getRightTitleButton(){
        return toolbar.getRightTextButton();
    }


    /**
     * 是否显示标题栏
     * @param isShow
     */
    protected void ShowTitle(boolean isShow){
        if(isShow){
            toolbar.setVisibility(View.VISIBLE);
        }else{
            toolbar.setVisibility(View.GONE);
        }
    }

    /**
     *  设置空界面图片
     */
    public void setEmptyIcon(int resId) {
        statusLayoutManager.setEmptyIcon(resId);
    }

    /**
     *  设置空界面文字描述
     */
    public void setEmptyText(String message) {
        statusLayoutManager.setEmptyText(message);
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }




    @Override
    public void processingDialog() {
        ProgressDialogUtils.show(visitActivity());
    }

    @Override
    public void processingDialog(@StringRes int i) {
        ProgressDialogUtils.show(visitActivity(), i);
    }

    @Override
    public void dismissProcessingDialog() {
        ProgressDialogUtils.dismiss();
    }


    protected boolean enableDispatchTouchEventToDismissSoftKeyBoard() {
        return true;
    }

    protected void onKeyBoardHideEvent() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (butterKnifeEnabled()) {
            ButterKnife.unbind(this);
        }
        if (compositeSubscription != null && !compositeSubscription.isUnsubscribed()) {
            compositeSubscription.unsubscribe();
        }
        AppManager.getInstance().killActivity(this);
    }
}
