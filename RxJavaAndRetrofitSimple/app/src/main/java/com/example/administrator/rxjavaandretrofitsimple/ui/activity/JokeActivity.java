package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.qzy.library.convenientbanner.ConvenientBanner;
import com.android.qzy.library.convenientbanner.holder.CBViewHolderCreator;
import com.android.qzy.library.convenientbanner.holder.Holder;
import com.android.qzy.library.convenientbanner.listener.OnItemClickListener;
import com.android.qzy.library.viewpagertransform.ZoomOutTransformer;
import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.JokeResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.JokePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.JokeView;
import com.example.administrator.rxjavaandretrofitsimple.ui.adapter.JokeAdapter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.basewithoutmodel.BaseStatusMvpActivity;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;
import com.example.administrator.rxjavaandretrofitsimple.util.xmlparse.ScreenUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 作者：quzongyang
 * <p>
 * 创建时间：2017/3/20
 * <p>
 * 类描述：
 */

public class JokeActivity extends BaseStatusMvpActivity<JokeView, JokePresenter> implements JokeView {

    @Bind(R.id.ivBack)
    ImageView ivBack;
    @Bind(R.id.rcvJoke)
    RecyclerView rcvJoke;
    JokeAdapter jokeAdapter;
    private ConvenientBanner convenientBanner;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private View headerView;
    private int total_page;
    private int total;
    private int currentPage = 1;

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, JokeActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Override
    protected void onViewCreated() {
        initView();
        initBanner();
        _presenter.getJokeInfo(1, LocalConstant.DEFAULT_PAGE, LocalConstant.JOKE_REQURST_KAY);
    }

    public void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(visitActivity(), LinearLayoutManager.VERTICAL, false);
        rcvJoke.setLayoutManager(manager);
        headerView = View.inflate(visitActivity(), R.layout.item_wechat_head, null);
        convenientBanner = (ConvenientBanner) headerView.findViewById(R.id.convenientBanner);
    }

    @Override
    public void provideJokeInfo(JokeResponse.ResultBean response) {
        statusLayoutManager.showContent();
        if (jokeAdapter == null) {
            jokeAdapter = new JokeAdapter(response.data);
            jokeAdapter.addHeaderView(headerView);
            rcvJoke.setAdapter(jokeAdapter);
        }
    }

    @Override
    public void refreshCompleted() {
    }

    /**
     * 网络请求无数据界面
     */
    @Override
    public void displayEmptyPage() {
        statusLayoutManager.showEmptyData();
    }

    /**
     * 网络请求错误界面
     */
    @Override
    public void displayErrorPage() {
        statusLayoutManager.showNetWorkError();
    }

    @Override
    public void displayNoMoreTip() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_joke;
    }

    @Override
    protected void initTitleBar() {
        showTitle(false);
    }

    /**
     * 初始化轮播图 数据来自本地
     */
    public void initBanner(){
        localImages.add(R.mipmap.banner01);
        localImages.add(R.mipmap.banner02);
        localImages.add(R.mipmap.banner03);
        localImages.add(R.mipmap.banner04);
        localImages.add(R.mipmap.banner05);
        ViewGroup.LayoutParams bannerParams = convenientBanner.getLayoutParams();
        bannerParams.width = ScreenUtils.getWidth(visitActivity());
        bannerParams.height = bannerParams.width * 1/ 2;//Designed size.
        convenientBanner.setLayoutParams(bannerParams);
        convenientBanner.getViewPager().setPageTransformer(true,new ZoomOutTransformer());
        //自定义你的Holder，实现更多复杂的界面，不一定是图片翻页，其他任何控件翻页亦可。
        convenientBanner.setPages(
                new CBViewHolderCreator<LocalImageHolderView>() {
                    @Override
                    public LocalImageHolderView createHolder() {
                        return new LocalImageHolderView();
                    }
                }, localImages)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        AbToastUtil.showToast(visitActivity(),String.valueOf(position));
                    }
                });
    }

    public class LocalImageHolderView implements Holder<Integer> {
        private ImageView imageView;
        @Override
        public View createView(Context context) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            return imageView;
        }

        @Override
        public void UpdateUI(Context context, final int position, Integer data) {
            Glide.with(BaseApplication.getInstance()).load(data).into(imageView);
        }
    }

    /**
     * 网络请求错误重试
     */
    @Override
    protected void onRetryClick() {
        _presenter.getJokeInfo(1, LocalConstant.DEFAULT_PAGE, LocalConstant.JOKE_REQURST_KAY);
    }

    @OnClick({R.id.ivBack})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
        }
    }

    @Override
    protected JokePresenter createPresenter() {
        return new JokePresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //开始自动翻页
        convenientBanner.startTurning(3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //停止翻页
        convenientBanner.stopTurning();
    }
}
