package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.android.qzy.library.refreshview.SpringView;
import com.android.qzy.library.refreshview.container.RotationFooter;
import com.android.qzy.library.refreshview.container.RotationHeader;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseNoNetworkActivity;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/23
 *
 * 类描述：
 */

public class HistoryTodayActivity extends BaseNoNetworkActivity {

    @Bind(R.id.springview)
    SpringView springView;
    /**
     * 入口
     * @param activity
     */
    public static void startAction(Activity activity){
        Intent intent = new Intent(activity, HistoryTodayActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_history_today;
    }


    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AbToastUtil.showToast(getActivity(),"刷新完成");
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AbToastUtil.showToast(getActivity(),"加载完成");
                        springView.onFinishFreshAndLoad();
                    }
                }, 1000);
            }
        });
        springView.setHeader(new RotationHeader(this));
        springView.setFooter(new RotationFooter(this));
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    protected void initTitleBar() {
        setTitleCenter("历史上的今天");
    }

    @Override
    protected Activity getActivity() {
        return this;
    }
}
