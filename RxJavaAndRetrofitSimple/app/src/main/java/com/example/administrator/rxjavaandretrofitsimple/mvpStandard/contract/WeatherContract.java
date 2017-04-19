package com.example.administrator.rxjavaandretrofitsimple.mvpStandard.contract;

import com.example.administrator.rxjavaandretrofitsimple.bean.WeatherResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.model.base.BaseStandardModel;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.presenter.base.BaseStandardPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.view.BaseStandardView;

import rx.Observable;


/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/19
 *
 * 类描述：微信精选列表契约管理类
 */

public interface WeatherContract {

    interface Model extends BaseStandardModel {
        //请求获取图片
        Observable<WeatherResponse.ResultBean> getWeatherData(String city, String key);
    }

    interface View extends BaseStandardView {
        //返回获取的图片
        void returnWeatherRequestData(WeatherResponse.ResultBean resultBean);
    }
    abstract static class Presenter extends BaseStandardPresenter<WeatherContract.View, WeatherContract.Model> {
        //发起获取图片请求
        public abstract void getWeatherRequest(String city, String key);
    }
}
