package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;


import com.android.qzy.library.flycotablelayout.CommonTabLayout;
import com.android.qzy.library.flycotablelayout.listener.CustomTabEntity;
import com.android.qzy.library.flycotablelayout.listener.OnTabSelectListener;
import com.android.qzy.library.viewpagertransform.DrawerTransformer;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.SplashAdvEntity;
import com.example.administrator.rxjavaandretrofitsimple.bean.TabEntity;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseNoNetworkActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.fragment.NewsFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.fragment.OthersFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.fragment.PhotoFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.fragment.VideoFragment;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.AppManager;
import com.example.administrator.rxjavaandretrofitsimple.util.DownLoaderAsyncTask;


import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：
 */

public class MainActivity extends BaseNoNetworkActivity {
    @Bind(R.id.tab_layout)
    CommonTabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"新闻", "美图","视频","综合"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_home_normal,R.mipmap.ic_girl_normal,R.mipmap.ic_video_normal,R.mipmap.ic_care_normal};
    private int[] mIconSelectIds = {
            R.mipmap.ic_home_selected,R.mipmap.ic_girl_selected, R.mipmap.ic_video_selected,R.mipmap.ic_care_selected};
    private NewsFragment newsFragment;
    private PhotoFragment photoFragment;
    private VideoFragment videoFragment;
    private OthersFragment othersFragment;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<SplashAdvEntity> splashAdvEntities;//启动页广告集合
    private DownLoaderAsyncTask downLoaderAsyncTask;
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
    protected int getLayoutId() {
        return R.layout.activity_main_layout;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        initTitleBar();
        initFragment(savedInstanceState);
        initTab();
        initAdvData();
    }


    public void initAdvData(){
        splashAdvEntities = new ArrayList<SplashAdvEntity>();
        splashAdvEntities.add(new SplashAdvEntity("1","https://www.baidu.com/","http://www.uc.cn/uploads/allimg/140717/25-140GGU9300-L.jpg","111"));
        downLoaderAsyncTask=new DownLoaderAsyncTask();
        downLoaderAsyncTask.execute(splashAdvEntities);
    }

    @Override
    protected void onRetryClick() {
    }


    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            newsFragment = (NewsFragment) getSupportFragmentManager().findFragmentByTag("newsFragment");
            photoFragment = (PhotoFragment) getSupportFragmentManager().findFragmentByTag("photoFragment");
            videoFragment = (VideoFragment) getSupportFragmentManager().findFragmentByTag("videoFragment");
            othersFragment = (OthersFragment) getSupportFragmentManager().findFragmentByTag("othersFragment");
        } else {
            newsFragment = new NewsFragment();
            photoFragment = new PhotoFragment();
            videoFragment = new VideoFragment();
            othersFragment = new OthersFragment();
            mFragments.add(newsFragment);
            mFragments.add(photoFragment);
            mFragments.add(videoFragment);
            mFragments.add(othersFragment);
        }
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mViewPager.setPageTransformer(true,new DrawerTransformer());//加入ViewPager转场动画
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    protected void initTitleBar() {
        showTitle(false);
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    /**
     * 双击返回键退出(利用计算时间差实现0
     */
    private long exitTime = 0;
    public void ExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            AbToastUtil.showToast(getActivity(), getString(R.string.toast_exit));
            exitTime = System.currentTimeMillis();
        } else {
            AppManager.getInstance().AppExit(BaseApplication.getInstance());
            getActivity().finish();
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ExitApp();
        }
        return false;
    }


}
