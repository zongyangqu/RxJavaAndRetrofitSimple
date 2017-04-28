package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.api.ApiManager;
import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.PhotoViewPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.contract.PhotoListContract;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.model.PhotoModel;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.presenter.PhotoPresenter;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.PhotosDetailActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.adapter.PhotoViewAdapter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.basestandardmvp.BaseStandardMVPFragment;
import com.example.administrator.rxjavaandretrofitsimple.ui.utils.SpacesItemDecoration;
import com.example.administrator.rxjavaandretrofitsimple.util.ProgressDialogUtils;

import java.util.List;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：视频
 */

public class VideoFragment extends BaseStandardMVPFragment<PhotoPresenter,PhotoModel> implements PhotoListContract.View{

    /*@Bind(R.id.rcvPhoto)
    RecyclerView rcvPhoto;
    PhotoViewAdapter adapter;*/

    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }



    @Override
    public void showLoading(String title) {
        ProgressDialogUtils.show(getActivity(),title);
    }

    @Override
    public void stopLoading() {
        ProgressDialogUtils.dismiss();
    }

    @Override
    public void showErrorTip(String msg) {
        ProgressDialogUtils.dismiss();
    }

    @Override
    public void returnPhotosListData(List<PhotoViewResponse.PhotoViewBean> photoGirls,boolean isLoadMore) {
        statusLayoutManager.showContent();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video;
    }

    @Override
    protected void onViewCreatedLazily(Bundle bundle) {

    }

    /**
     * 调用接口
     */
    public void refreshPhotoView(boolean isLoadMore) {
    }


    @Override
    protected void onRetryClick() {

    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return null;
    }
}
