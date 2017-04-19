package com.example.administrator.rxjavaandretrofitsimple.mvpStandard.presenter;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.contract.PhotoListContract;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.utils.RxSubscriber;

import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/19
 *
 * 类描述：
 */

public class PhotoPresenter extends PhotoListContract.Presenter {
    @Override
    public void getPhotosListDataRequest(int size, int page) {
        mRxManage.add(mModel.getPhotosListData(size,page).subscribe(new RxSubscriber<List<PhotoViewResponse.PhotoViewBean>>(mContext,false) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }
            @Override
            protected void _onNext(List<PhotoViewResponse.PhotoViewBean> photoGirls) {
                mView.returnPhotosListData(photoGirls);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }

        }));
    }
}
