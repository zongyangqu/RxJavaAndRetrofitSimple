package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.HistoryTodayActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.JokeActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseModelFragment;

import butterknife.Bind;
import butterknife.OnClick;

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
    @Bind(R.id.tvHistoryToday)
    TextView tvHistoryToday;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_others;
    }

    @Override
    protected void onViewCreatedLazily(Bundle bundle) {

    }
    @Override
    protected void onRetryClick() {

    }

    @OnClick({R.id.tvJoke,R.id.tvHistoryToday})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tvJoke:
                JokeActivity.startAction(getActivity());
                break;
            case R.id.tvHistoryToday:
                HistoryTodayActivity.startAction(getActivity());
                break;
        }
    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return null;
    }
}
