package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.qzy.library.flycotablelayout.SlidingTabLayout;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.NewsPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.NewsView;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseMvpLazyFragment;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：新闻管理Fragment
 */
public class NewsFragment extends BaseMvpLazyFragment<NewsView,NewsPresenter> implements NewsView{
    @Bind(R.id.mAbSlidingTabView)
    SlidingTabLayout mAbSlidingTabView;
    @Bind(R.id.viewPager_news_tab)
    ViewPager viewPager_news_tab;
    private List<Fragment> fragmentsList = new ArrayList<Fragment>();
    private String[] tabs;

    private FragmentAdapter fragmentAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void onViewCreatedLazily(Bundle bundle) {
        initFragment();
    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter();
    }

    public void initFragment(){
        tabs = getResources().getStringArray(R.array.tab_news_title);
        for(int position = 0;position<tabs.length;position++){
            switch (position){
                case 0:
                    NewsDetailsFragment topNewsFragment = NewsDetailsFragment.getInstance(LocalConstant.REQUESTTOPNEWS);
                    fragmentsList.add(topNewsFragment);
                    break;
                case 1:
                    NewsDetailsFragment societyNewsFragment = NewsDetailsFragment.getInstance(LocalConstant.REQUESTSOCIETYNEWS);
                    fragmentsList.add(societyNewsFragment);
                    break;
                case 2:
                    NewsDetailsFragment sportsNewsFragment = NewsDetailsFragment.getInstance(LocalConstant.REQUESTSPOTRSNEWS);
                    fragmentsList.add(sportsNewsFragment);
                    break;
                case 3:
                    NewsDetailsFragment chinaNewsFragment = NewsDetailsFragment.getInstance(LocalConstant.REQUESTCHINANEWS);
                    fragmentsList.add(chinaNewsFragment);
                    break;
                case 4:
                    NewsDetailsFragment internationalNewsFragment = NewsDetailsFragment.getInstance(LocalConstant.REQUESTINTERNATIONNEWS);
                    fragmentsList.add(internationalNewsFragment);
                    break;
                case 5:
                    NewsDetailsFragment entertainmentNewsFragment = NewsDetailsFragment.getInstance(LocalConstant.REQUESTENTERTAINMENTNEWS);
                    fragmentsList.add(entertainmentNewsFragment);
                    break;
                case 6:
                    NewsDetailsFragment militaryNewsFragment = NewsDetailsFragment.getInstance(LocalConstant.REQUESTMILITARYNEWS);
                    fragmentsList.add(militaryNewsFragment);
                    break;
            }
        }
        fragmentAdapter = new FragmentAdapter(getActivity(), getFragmentManager());
        viewPager_news_tab.setAdapter(fragmentAdapter);
        viewPager_news_tab.setOffscreenPageLimit(tabs.length);
        mAbSlidingTabView.setViewPager(viewPager_news_tab);
        mAbSlidingTabView.setCurrentTab(0);
    }

    class FragmentAdapter extends FragmentPagerAdapter {

        public FragmentAdapter(Context mContext,FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentsList.get(position);
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }

    @Override
    protected void onRetryClick() {

    }
}

