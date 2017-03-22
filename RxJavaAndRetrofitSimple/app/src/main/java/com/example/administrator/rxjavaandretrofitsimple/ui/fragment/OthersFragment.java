package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.JokeActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseModelFragment;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：
 */

public class OthersFragment extends BaseModelFragment {

    @Bind(R.id.tvJoke)
    TextView tvJoke;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_others;
    }

    @Override
    protected void onViewCreatedLazily(Bundle bundle) {
        tvJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JokeActivity.startAction(getActivity());
            }
        });
    }
    @Override
    protected void onRetryClick() {

    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return null;
    }
}
