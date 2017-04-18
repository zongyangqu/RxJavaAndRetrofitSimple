package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseStatusModelActivity;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;
import com.example.administrator.rxjavaandretrofitsimple.util.ProgressDialogUtils;
import com.example.administrator.rxjavaandretrofitsimple.util.xmlparse.ScreenUtils;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/14
 *
 * 类描述：
 */

public class WeChatActivity extends BaseStatusModelActivity implements WeChatView {

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
    private GridLayoutManager manager;
    // 当前的条目是recyclerView的头布局
    public static final int HEADER_VIEW_ITEM = 0;
    // 当前的条目是普通recyclerView的条目
    //public static final int NORMAL_RECYCLER_VIEW_ITEM = 1;
    // 一行显示一个
    public static final int RECYCLERVIEW_ITEM_SINGLE = 1;
    // 一行显示两个
    public static final int RECYCLERVIEW_ITEM_DOUBLE = 2;


    /**
     * 入口
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, WeChatActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return new WeChatPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wechat;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        setListener();
        initView();
        initBanner();
        refreshWeChatInfo(1, false);
    }

    public void initView() {
        manager = new GridLayoutManager(this, 2);
        // 设置布局管理一条数据占用几行，如果是头布局则头布局自己占用一行
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int postion) {
                if (postion == 0) {
                    return 2;
                } else {
                    return 1;
                }
            }
        });
        rcv_weChat.setLayoutManager(manager);
       // rcv_weChat.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new WeChatAdapter(getActivity());
        headerView = View.inflate(getActivity(), R.layout.item_wechat_head, null);
        // 设置当前ViewType
        adapter.setSpanSize(WeChatActivity.RECYCLERVIEW_ITEM_DOUBLE);
        adapter.addHeadView(headerView);
        rcv_weChat.setAdapter(adapter);
        convenientBanner = (ConvenientBanner) headerView.findViewById(R.id.convenientBanner);
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
    protected void initTitleBar() {
        getRightTitleIMG().setImageResource(R.mipmap.back);
        getRightTitleIMG().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeRecycleViewList();
            }
        });
        setTitleCenter("微信精选");
    }

    @Override
    protected Activity getActivity() {
        return this;
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

    /**
     * 改变RecycleView的显示列数
     */
    private void changeRecycleViewList() {
        if (adapter != null) {
            int spanSize = adapter.getSpanSize();
            // 当前一行显示一列
            if (spanSize == WeChatActivity.RECYCLERVIEW_ITEM_SINGLE) {
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (adapter.getItemViewType(position) == WeChatActivity.HEADER_VIEW_ITEM) {
                            return 2;
                        } else {
                            return 1;

                        }
                    }
                });
                adapter.setSpanSize(WeChatActivity.RECYCLERVIEW_ITEM_DOUBLE);
            }
            // 当前一行显示两列
            else if (spanSize == WeChatActivity.RECYCLERVIEW_ITEM_DOUBLE) {
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (adapter.getItemViewType(position) == WeChatActivity.HEADER_VIEW_ITEM) {
                            return 2;
                        } else {
                            return 2;
                        }
                    }
                });
                adapter.setSpanSize(WeChatActivity.RECYCLERVIEW_ITEM_SINGLE);
            }
            // 第一个参数是动画开始的位置索引
            adapter.notifyItemRangeChanged(2, adapter.getItemCount());
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
        ProgressDialogUtils.dismiss();
    }

    @Override
    public void startLoadingView() {
        ProgressDialogUtils.show(getActivity());
    }

    @Override
    public void showError(String errMsg) {
        statusLayoutManager.showNetWorkError();
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
