package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.qzy.library.statusbar.StatusBarUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseNoNetworkActivity;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;

import butterknife.Bind;
import uk.co.senab.photoview.PhotoView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/18
 *
 * 类描述：查看大图详情
 */

public class PhotosDetailActivity extends BaseNoNetworkActivity{
    @Bind(R.id.photo_touch)
    PhotoView photoView;


    /**
     * 入口
     * @param activity
     */
    public static void startAction(Activity activity,String url) {
        Intent intent = new Intent(activity, PhotosDetailActivity.class);
        intent.putExtra(LocalConstant.PHOTO_DETAIL,url);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_details;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.black),0);
        String url = getIntent().getStringExtra(LocalConstant.PHOTO_DETAIL);
        Glide.with(this).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.mipmap.ic_empty_picture)
                .crossFade().into(photoView);
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    protected void initTitleBar() {
        setTitleBackgroundColor("#000000");
    }

    @Override
    protected Activity getActivity() {
        return null;
    }
}
