package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.TabEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.fragment.NewsFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.fragment.OthersFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.fragment.VideoFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.fragment.WeChatFragment;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：
 */

public class MainActivity extends BaseActivity{
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @Bind(R.id.fl_body)
    FrameLayout fl_body;
    private String[] mTitles = {"新闻", "微信","视频","其他"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_home_normal,R.mipmap.ic_girl_normal,R.mipmap.ic_video_normal,R.mipmap.ic_care_normal};
    private int[] mIconSelectIds = {
            R.mipmap.ic_home_selected,R.mipmap.ic_girl_selected, R.mipmap.ic_video_selected,R.mipmap.ic_care_selected};
    private NewsFragment newsFragment;
    private WeChatFragment weChatFragment;
    private VideoFragment videoFragment;
    private OthersFragment othersFragment;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private static int tabLayoutHeight;
    /**
     * 入口
     * @param activity
     */
    public static void startAction(Activity activity){
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_layout);
        initTab();
        initFragment(savedInstanceState);
        initTitleBar();
        initfindViewById();
        init();
        setListener();
    }

    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int currentTabPosition = 0;
        if (savedInstanceState != null) {
            newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentByTag("newsFragment");
            weChatFragment = (WeChatFragment) getSupportFragmentManager().findFragmentByTag("weChatFragment");
            videoFragment = (VideoFragment) getSupportFragmentManager().findFragmentByTag("videoFragment");
            othersFragment = (OthersFragment) getSupportFragmentManager().findFragmentByTag("othersFragment");
            currentTabPosition = savedInstanceState.getInt(LocalConstant.MAIN_CURRENT_TAB_POSITION);
        } else {
            newsFragment = new NewsFragment();
            weChatFragment = new WeChatFragment();
            videoFragment = new VideoFragment();
            othersFragment = new OthersFragment();

            transaction.add(R.id.fl_body, newsFragment, "newsFragment");
            transaction.add(R.id.fl_body, weChatFragment, "weChatFragment");
            transaction.add(R.id.fl_body, videoFragment, "videoFragment");
            transaction.add(R.id.fl_body, othersFragment, "othersFragment");
        }
        transaction.commit();
        SwitchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                SwitchTo(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    /**
     * 切换
     */
    private void SwitchTo(int position) {
        //LogUtils.logd("主页菜单position" + position);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            //新闻
            case 0:
                transaction.hide(weChatFragment);
                transaction.hide(videoFragment);
                transaction.hide(othersFragment);
                transaction.show(newsFragment);
                transaction.commitAllowingStateLoss();
                break;
            //微信精选
            case 1:
                transaction.hide(videoFragment);
                transaction.hide(othersFragment);
                transaction.hide(newsFragment);
                transaction.show(weChatFragment);
                transaction.commitAllowingStateLoss();
                break;
            //视频
            case 2:
                transaction.hide(newsFragment);
                transaction.hide(weChatFragment);
                transaction.hide(othersFragment);
                transaction.show(videoFragment);
                transaction.commitAllowingStateLoss();
                break;
            //其他
            case 3:
                transaction.hide(newsFragment);
                transaction.hide(weChatFragment);
                transaction.hide(videoFragment);
                transaction.show(othersFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }
    @Override
    protected void initTitleBar() {
        hideTitle();
    }

    @Override
    protected void initfindViewById() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected Activity getActivity() {
        return null;
    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return null;
    }
}
