package com.example.administrator.rxjavaandretrofitsimple.ui.base;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.qzy.library.statusbar.StatusBarUtil;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.OnRetryListener;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.OnShowHideViewListener;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.manager.StatusLayoutManager;
import com.example.administrator.rxjavaandretrofitsimple.util.AppManager;
import com.example.administrator.rxjavaandretrofitsimple.util.widget.ToolbarWrapper;
import butterknife.ButterKnife;


/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：无网络请求的Activity基类
 */

public abstract class BaseNoNetworkActivity extends AppCompatActivity {

    protected ToolbarWrapper toolbar;
    protected StatusLayoutManager statusLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_base);
        setStatusBar();
        initStatusLayout();
        if (butterKnifeEnabled()) {
            ButterKnife.bind(this);
        }
        toolbar = ((ToolbarWrapper) findViewById(R.id.toolbar));
        if (toolbar != null) {
            configToolbar();
        }
        initTitleBar();
        onViewCreated(savedInstanceState);
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.main_blue),0);
    }

    /**
     * 初始化状态切换布局
     */
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




    protected void configToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void onViewCreated(Bundle savedInstanceState);

    protected void onBack() {
        finish();
    }

    /**
     * 加载错误重试
     */
    protected abstract void onRetryClick();

    protected boolean butterKnifeEnabled() {
        return true;
    }

    protected boolean lightStatusBarMode() {
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 初始化标题
     **/
    protected abstract void initTitleBar();

    protected abstract Activity getActivity();

    @Override
    protected void onDestroy() {
        AppManager.getInstance().killActivity(this);
        ButterKnife.unbind(this);
        super.onDestroy();
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
    protected void setTitleBackgroundColor(String color) {
        toolbar.setTitleBarBackGroundColor(color);

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
    protected void showTitle(boolean isShow){
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
}

