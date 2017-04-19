package com.example.administrator.rxjavaandretrofitsimple.mvpStandard.presenter;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeatherResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.contract.PhotoListContract;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.contract.WeatherContract;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.utils.RxSubscriber;

import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/19
 *
 * 类描述：
 */

public class WeatherPresenter extends WeatherContract.Presenter{

    @Override
    public void getWeatherRequest(String city, String key) {
        mRxManage.add(mModel.getWeatherData(city,key).subscribe(new RxSubscriber<WeatherResponse.ResultBean>(mContext,false) {
            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading(mContext.getString(R.string.loading));
            }
            @Override
            protected void _onNext(WeatherResponse.ResultBean resultBean) {
                mView.returnWeatherRequestData(resultBean);
                mView.stopLoading();
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }

        }));
    }
}

