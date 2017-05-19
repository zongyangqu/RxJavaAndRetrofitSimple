package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.Person;
import com.example.administrator.rxjavaandretrofitsimple.bean.PersonComparator;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.MultiplePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.MultipleView;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.basewithoutmodel.BaseStatusMvpActivity;
import com.example.administrator.rxjavaandretrofitsimple.widget.AsImageTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/13
 *
 * 类描述：
 */

public class MultipleActivity extends BaseStatusMvpActivity<MultipleView, MultiplePresenter> implements MultipleView {

    @Bind(R.id.asImageView)
    AsImageTextView asImageView;

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, MultipleActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Override
    protected MultiplePresenter createPresenter() {
        return new MultiplePresenter();
    }

    @Override
    protected void onViewCreated() {
        asImageView.setTvImagetext("这是测试");
        asImageView.setIvImagetext(R.mipmap.ic_launcher);
        asImageView.setImagetextclick(new AsImageTextView.Imagetextclick() {
            @Override
            public void setImagetextclick() {
                Toast.makeText(MultipleActivity.this,"点击了图片",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_multiple;
    }

    @Override
    protected void initTitleBar() {
        showTitle(false);
        setTitleCenter("综合");
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    public void refreshCompleted() {

    }

    @Override
    public void displayEmptyPage() {

    }

    @Override
    public void displayErrorPage() {

    }

    @Override
    public void displayNoMoreTip() {

    }
}
