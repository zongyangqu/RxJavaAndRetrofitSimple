package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.JokeResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.JokePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.JokeView;
import com.example.administrator.rxjavaandretrofitsimple.ui.adapter.JokeAdapter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseStatusMvpStatusActivity;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;

import butterknife.Bind;


/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/20
 *
 * 类描述：
 */

public class JokeActivity extends BaseStatusMvpStatusActivity<JokeView,JokePresenter> implements JokeView{


    @Bind(R.id.rcvJoke)
    RecyclerView rcvJoke;
    JokeAdapter jokeAdapter;
    /**
     * 入口
     * @param activity
     */
    public static void startAction(Activity activity){
        Intent intent = new Intent(activity, JokeActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    @Override
    protected void onViewCreated() {
        initView();
        _presenter.getJokeInfo(1,LocalConstant.DEFAULT_PAGE, LocalConstant.JOKE_REQURST_KAY);
    }

    public void initView(){
        LinearLayoutManager manager = new LinearLayoutManager(visitActivity(), LinearLayoutManager.VERTICAL, false);
        rcvJoke.setLayoutManager(manager);
    }

    @Override
    public void provideJokeInfo(JokeResponse.ResultBean response) {
        statusLayoutManager.showContent();
        if (jokeAdapter == null) {
            jokeAdapter = new JokeAdapter(response.data);
            rcvJoke.setAdapter(jokeAdapter);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_joke;
    }

    @Override
    protected void initTitleBar() {
        setTitleCenter("笑话");
    }

    @Override
    protected void onRetryClick() {
    }



    @Override
    protected JokePresenter createPresenter() {
        return new JokePresenter();
    }
}
