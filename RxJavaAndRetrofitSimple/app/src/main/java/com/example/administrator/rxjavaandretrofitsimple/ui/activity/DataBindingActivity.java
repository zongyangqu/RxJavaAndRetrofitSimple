package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeatherResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.contract.WeatherContract;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.model.WeatherModel;
import com.example.administrator.rxjavaandretrofitsimple.mvpStandard.presenter.WeatherPresenter;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.basestandardmvp.BaseStandardMVPActivity;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;
import com.example.administrator.rxjavaandretrofitsimple.util.ProgressDialogUtils;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/28
 *
 * 类描述：
 */

public class DataBindingActivity extends BaseStandardMVPActivity<WeatherPresenter,WeatherModel> implements WeatherContract.View{

    @Bind(R.id.tvCityName)
    TextView tvCityName;

    /**
     * 入口
     *
     * @param activity
     */
    public static void startAction(Activity activity) {
        Intent intent = new Intent(activity, DataBindingActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }
    @Override
    public void initPresenter() {
        mPresenter.setVM(this,mModel);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_weather;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mPresenter.getWeatherRequest("北京", LocalConstant.WEATHER_REQURST_KAY);
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
        AbToastUtil.showToast(getActivity(),msg);
        statusLayoutManager.showNetWorkError();
    }

    @Override
    public void returnWeatherRequestData(WeatherResponse.ResultBean resultBean) {
        statusLayoutManager.showContent();
        tvCityName.setText(resultBean.data.realtime.city_name);
    }

    @Override
    protected void onRetryClick() {
        mPresenter.getWeatherRequest("北京", LocalConstant.WEATHER_REQURST_KAY);
    }

    @Override
    protected void initTitleBar() {
        setTitleCenter("天气预报");
    }

    @Override
    protected Activity getActivity() {
        return this;
    }
}

