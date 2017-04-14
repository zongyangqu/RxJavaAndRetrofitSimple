package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.qzy.library.convenientbanner.ConvenientBanner;
import com.android.qzy.library.convenientbanner.holder.CBViewHolderCreator;
import com.android.qzy.library.convenientbanner.holder.Holder;
import com.android.qzy.library.convenientbanner.listener.OnItemClickListener;
import com.android.qzy.library.refreshview.SpringView;
import com.android.qzy.library.refreshview.container.MeituanFooter;
import com.android.qzy.library.refreshview.container.MeituanHeader;
import com.android.qzy.library.viewpagertransform.ZoomOutTransformer;
import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.WeChatModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.WeChatPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.WeChatView;
import com.example.administrator.rxjavaandretrofitsimple.ui.adapter.WeChatAdapter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseModelFragment;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;
import com.example.administrator.rxjavaandretrofitsimple.util.xmlparse.ScreenUtils;
import java.util.ArrayList;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：微信精选
 */

public class WeChatFragment extends BaseModelFragment implements WeChatView {

    private WeChatPresenter _presenter;
    private WeChatAdapter adapter;
    @Bind(R.id.rcv_weChat)
    RecyclerView rcv_weChat;
    @Bind(R.id.springview)
    SpringView springView;
    private int total_page;
    private int currentPage = 1;
    private View headerView;
    private ArrayList<Integer> localImages = new ArrayList<Integer>();
    private ConvenientBanner convenientBanner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void onViewCreatedLazily(Bundle bundle) {
        setListener();
        initView();
        initBanner();
        refreshWeChatInfo(1, false);
    }

    public void initView() {
        rcv_weChat.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WeChatAdapter(getActivity());
        rcv_weChat.setAdapter(adapter);
        headerView = View.inflate(getActivity(), R.layout.item_wechat_head, null);
        convenientBanner = (ConvenientBanner) headerView.findViewById(R.id.convenientBanner);
        adapter.addHeaderView(headerView);
        _presenter = new WeChatPresenter();
        _presenter.attachView(this);
        _presenter.attachModel(new WeChatModel());
    }

    public void initBanner(){
        localImages.add(R.mipmap.banner01);
        localImages.add(R.mipmap.banner02);
        localImages.add(R.mipmap.banner03);
        localImages.add(R.mipmap.banner04);
        localImages.add(R.mipmap.banner05);
        ViewGroup.LayoutParams bannerParams = convenientBanner.getLayoutParams();
        bannerParams.width = ScreenUtils.getWidth(getActivity());
        bannerParams.height = bannerParams.width * 50 / 121;//Designed size.
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
                        AbToastUtil.showToast(getActivity(),String.valueOf(position));
                    }
                });
    }

    public void setListener() {
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                refreshWeChatInfo(1, false);
            }

            @Override
            public void onLoadmore() {
                if (total_page > currentPage) {
                    currentPage = currentPage + 1;
                    refreshWeChatInfo(currentPage, true);
                } else {
                    springView.onFinishFreshAndLoad();
                    AbToastUtil.showToast(getActivity(), "没有更多了，亲！");
                }
            }
        });
        springView.setHeader(new MeituanHeader(getActivity()));
        springView.setFooter(new MeituanFooter(getActivity()));
//        springView.setHeader(new RotationHeader(getActivity()));
//        springView.setFooter(new RotationFooter(getActivity()));
    }

    @Override
    protected void onRetryClick() {
        refreshWeChatInfo(1, false);
    }


    /**
     * 调用接口
     *
     * @param currentPage 当前页数
     * @param isLoadMore  是否是上拉加载更多
     */

    public void refreshWeChatInfo(int currentPage, boolean isLoadMore) {
        _presenter.getWeChat(currentPage, LocalConstant.DEFAULT_PAGE, LocalConstant.WECHAT_REQURST_KAY, isLoadMore);
    }


    @Override
    protected BasePresenter getCurrentPersenter() {
        return _presenter;
    }

    @Override
    public void provideWeChat(WeChatResponse response, boolean isLoadMore) {
        springView.onFinishFreshAndLoad();
        statusLayoutManager.showContent();
        total_page = response.result.totalPage;
        if (isLoadMore) {//加载更多
            adapter.addData(response.result.list);
        } else {//刷新
            adapter.setData(response.result.list);
        }
    }


    @Override
    public void displayEmptyPage() {
        statusLayoutManager.showEmptyData();
    }


    @Override
    public void updateView(WeChatResponse response) {
    }

    @Override
    public void hideLoadingView() {
        springView.onFinishFreshAndLoad();
    }

    @Override
    public void startLoadingView() {
    }

    @Override
    public void showError(String errMsg) {
        statusLayoutManager.showNetWorkError();
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

    @Override
    public void onResumeLazy() {
        super.onResumeLazy();
        //开始自动翻页
        convenientBanner.startTurning(3000);
    }

    @Override
    public void onPauseLazy() {
        super.onPauseLazy();
        //停止翻页
        convenientBanner.stopTurning();
    }


}
