package com.example.administrator.rxjavaandretrofitsimple.mvp.view;

import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.base.BaseView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/18
 *
 * 类描述：美图视图层
 */

public interface PhotoView extends BaseView<PhotoViewResponse> {
    /**
     * 更新数据
     */
    void providePhotoView(PhotoViewResponse response, boolean isLoadMore);
}
