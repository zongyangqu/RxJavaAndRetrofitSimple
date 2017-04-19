package com.example.administrator.rxjavaandretrofitsimple.mvpStandard.contract;

import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.model.base.BaseStandardModel;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.presenter.base.BaseStandardPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.view.BaseStandardView;

import java.util.List;

import rx.Observable;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/19
 *
 * 类描述：美图列表契约管理类
 */

public interface PhotoListContract {

    interface Model extends BaseStandardModel {
        //请求获取图片
        Observable<List<PhotoViewResponse.PhotoViewBean>> getPhotosListData(int size, int page);
    }

    interface View extends BaseStandardView {
        //返回获取的图片
        void returnPhotosListData(List<PhotoViewResponse.PhotoViewBean> photoGirls);
    }
    abstract static class Presenter extends BaseStandardPresenter<View, Model> {
        //发起获取图片请求
        public abstract void getPhotosListDataRequest(int size, int page);
    }
}
