package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.android.qzy.library.refreshview.SpringView;
import com.android.qzy.library.refreshview.container.MeituanFooter;
import com.android.qzy.library.refreshview.container.MeituanHeader;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.api.ApiManager;
import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.PhotoViewModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.PhotoViewPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.PhotoView;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.contract.PhotoListContract;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.model.PhotoModel;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.presenter.PhotoPresenter;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.PhotosDetailActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.adapter.PhotoViewAdapter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.basenormalmvp.BaseModelFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.basestandardmvp.BaseStandardMVPFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.utils.SpacesItemDecoration;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;
import com.example.administrator.rxjavaandretrofitsimple.util.ProgressDialogUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：微信精选
 */

public class PhotoFragment extends BaseStandardMVPFragment<PhotoPresenter,PhotoModel> implements PhotoListContract.View{

    @Bind(R.id.rcvPhoto)
    RecyclerView rcvPhoto;
    @Bind(R.id.springview)
    SpringView springview;
    private int currentPage = 1;
    PhotoViewAdapter adapter;
    private PhotoViewPresenter _presenter;

    @Override
    public void showLoading(String title) {
        ProgressDialogUtils.show(getActivity(),title);
    }

    @Override
    public void stopLoading() {
        springview.onFinishFreshAndLoad();
        ProgressDialogUtils.dismiss();
    }

    @Override
    public void showErrorTip(String msg) {
        statusLayoutManager.showNetWorkError();
    }

    @Override
    public void returnPhotosListData(List<PhotoViewResponse.PhotoViewBean> photoGirls,boolean isLoadMore) {
        statusLayoutManager.showContent();
        springview.onFinishFreshAndLoad();
        if (isLoadMore) {//加载更多
            adapter.addData(photoGirls);
        } else {//刷新
            adapter.setData(photoGirls);
        }
    }

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
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
        refreshPhotoView(1,false);
    }

    /**
     * 调用接口
     * @param currentPage 当前页数
     * @param isLoadMore  是否是上拉加载更多
     */
    public void refreshPhotoView(int currentPage, boolean isLoadMore) {
        mPresenter.getPhotosListDataRequest(LocalConstant.DEFAULT_MAXPAGE, currentPage, isLoadMore);
        //_presenter.getPhoto(ApiManager.getCacheControl(), LocalConstant.DEFAULT_MAXPAGE, currentPage, isLoadMore);
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
    public void onResumeLazy() {
        super.onResumeLazy();
    }

    @Override
    public void onPauseLazy() {
        super.onPauseLazy();
    }


}
