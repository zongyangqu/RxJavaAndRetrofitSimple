package com.example.administrator.rxjavaandretrofitsimple.mvpStandard.model;

import com.example.administrator.rxjavaandretrofitsimple.api.ApiManager;
import com.example.administrator.rxjavaandretrofitsimple.api.HostType;
import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.contract.PhotoListContract;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.utils.RxSchedulers;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/19
 *
 * 类描述：
 */

public class PhotoModel implements PhotoListContract.Model{

    @Override
    public Observable<List<PhotoViewResponse.PhotoViewBean>> getPhotosListData(int size, int page) {
        return ApiManager.getDefault(HostType.GANK_GIRL_PHOTO)
                .getPhotoList(ApiManager.getCacheControl(),size, page)
                .map(new Func1<PhotoViewResponse, List<PhotoViewResponse.PhotoViewBean>>() {
                    @Override
                    public List<PhotoViewResponse.PhotoViewBean> call(PhotoViewResponse girlData) {
                        return girlData.results;
                    }
                })
                .compose(RxSchedulers.<List<PhotoViewResponse.PhotoViewBean>>io_main());
    }
}
