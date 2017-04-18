package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.android.qzy.library.refreshview.SpringView;
import com.android.qzy.library.refreshview.container.MeituanFooter;
import com.android.qzy.library.refreshview.container.MeituanHeader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.api.ApiManager;
import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.PhotoViewModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.WeChatModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.PhotoViewPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.WeChatPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.PhotoView;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.WeChatView;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.PhotosDetailActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.adapter.PhotoViewAdapter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseModelFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.utils.SpacesItemDecoration;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;
import com.example.administrator.rxjavaandretrofitsimple.util.ProgressDialogUtils;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：微信精选
 */

public class PhotoFragment extends BaseModelFragment implements PhotoView {

    @Bind(R.id.rcvPhoto)
    RecyclerView rcvPhoto;
    @Bind(R.id.springview)
    SpringView springview;
    private int total_page;
    private int currentPage = 1;
    PhotoViewAdapter adapter;
    private PhotoViewPresenter _presenter;

    @Override
    public void providePhotoView(PhotoViewResponse response, boolean isLoadMore) {
        springview.onFinishFreshAndLoad();
        statusLayoutManager.showContent();
        if (isLoadMore) {//加载更多
            adapter.addData(response.results);
        } else {//刷新
            adapter.setData(response.results);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wechat;
    }

    @Override
    protected void onViewCreatedLazily(Bundle bundle) {
        setListener();
        initView();
    }

    public void initView() {
        adapter = new PhotoViewAdapter(getActivity());
        rcvPhoto.setAdapter(adapter);
        adapter.setOnItemClickListener(new PhotoViewAdapter.OnItemClickListener() {
            @Override
            public void onImageViewClick(PhotoViewResponse.PhotoViewBean photoViewBean) {
                PhotosDetailActivity.startAction(getActivity(),photoViewBean.url);
            }
        });
        rcvPhoto.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        SpacesItemDecoration decoration=new SpacesItemDecoration(18);
        rcvPhoto.addItemDecoration(decoration);
        _presenter = new PhotoViewPresenter();
        _presenter.attachView(this);
        _presenter.attachModel(new PhotoViewModel());
        refreshPhotoView(1,false);
    }

    /**
     * 调用接口
     * @param currentPage 当前页数
     * @param isLoadMore  是否是上拉加载更多
     */

    public void refreshPhotoView(int currentPage, boolean isLoadMore) {
        _presenter.getPhoto(ApiManager.getCacheControl(), LocalConstant.DEFAULT_MAXPAGE, currentPage, isLoadMore);
    }

    public void setListener() {
        springview.setType(SpringView.Type.FOLLOW);
        springview.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                refreshPhotoView(1,false);
            }

            @Override
            public void onLoadmore() {
                currentPage = currentPage + 1;
                refreshPhotoView(currentPage, true);
            }
        });
        springview.setHeader(new MeituanHeader(getActivity()));
        springview.setFooter(new MeituanFooter(getActivity()));
    }

    @Override
    protected void onRetryClick() {
        refreshPhotoView(1,false);
    }


    @Override
    protected BasePresenter getCurrentPersenter() {
        return new PhotoViewPresenter();
    }


    @Override
    public void displayEmptyPage() {
        statusLayoutManager.showEmptyData();
    }


    @Override
    public void updateView(PhotoViewResponse response) {
    }

    @Override
    public void hideLoadingView() {
        springview.onFinishFreshAndLoad();
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
    public void onResumeLazy() {
        super.onResumeLazy();
    }

    @Override
    public void onPauseLazy() {
        super.onPauseLazy();
    }


}
