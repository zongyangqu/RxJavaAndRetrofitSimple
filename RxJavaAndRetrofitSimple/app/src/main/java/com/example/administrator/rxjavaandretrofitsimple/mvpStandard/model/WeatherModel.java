package com.example.administrator.rxjavaandretrofitsimple.mvpStandard.model;

import com.example.administrator.rxjavaandretrofitsimple.api.ApiManager;
import com.example.administrator.rxjavaandretrofitsimple.api.HostType;
import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeatherResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.contract.PhotoListContract;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.contract.WeatherContract;
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

public class WeatherModel implements WeatherContract.Model{

    @Override
    public Observable<WeatherResponse.ResultBean> getWeatherData(String city, String key) {
        return ApiManager.getDefault(HostType.JUHE_DATE_NET_INTERFACEWEATHER)
                .getWeatherReport(city, key)
                .map(new Func1<WeatherResponse, WeatherResponse.ResultBean>() {
                    @Override
                    public WeatherResponse.ResultBean call(WeatherResponse response) {
                        return response.result;
                    }
                })
                .compose(RxSchedulers.<WeatherResponse.ResultBean>io_main());
    }
}
