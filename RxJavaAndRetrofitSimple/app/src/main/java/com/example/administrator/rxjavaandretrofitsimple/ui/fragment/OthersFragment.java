package com.example.administrator.rxjavaandretrofitsimple.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base.BasePresenter;
import com.example.administrator.rxjavaandretrofitsimple.rxbus.RxBus;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.DataBindingActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.HistoryTodayActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.JokeActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.LoginActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.MultipleActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.PDFActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.WeChatActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.WeatherActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.basenormalmvp.BaseModelFragment;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;

import butterknife.Bind;
import butterknife.OnClick;
import greendao.UserDB;
import greendao.db.User;
import rx.Observable;
import rx.Subscriber;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：其他界面
 */

public class OthersFragment extends BaseModelFragment {

    @Bind(R.id.tvJoke)
    TextView tvJoke;
    @Bind(R.id.tvHistoryToday)
    TextView tvHistoryToday;
    @Bind(R.id.tvLogin)
    TextView tvLogin;
    @Bind(R.id.tvMultiple)
    TextView tvMultiple;
    @Bind(R.id.tvWeChat)
    TextView tvWeChat;
    @Bind(R.id.tvWeather)
    TextView tvWeather;
    @Bind(R.id.tvDataBinding)
    TextView tvDataBinding;
    @Bind(R.id.tvPDF)
    TextView tvPDF;
    private Observable<User> observableUserLogin;//监听登录界面发送的用户信息对象
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_others;
    }

    @Override
    protected void onViewCreatedLazily(Bundle bundle) {
        User user = UserDB.queryLoginUser();
        if(null == user){
            setUserLogOut();
        }else{
            setUserLogin(user.getName());
        }
        setListener();
    }

    public void setListener(){
        /**
         * 用户登录事件监听
         */
        observableUserLogin = RxBus.get().register(LocalConstant.LOGIN_SUCCESS, User.class);
        observableUserLogin.subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(User user) {
                setUserLogin(user.getName());
            }
        });
    }
    @Override
    protected void onRetryClick() {

    }

    @OnClick({R.id.tvPDF,R.id.tvJoke,R.id.tvHistoryToday,R.id.tvLogin,R.id.tvMultiple,R.id.tvWeChat,R.id.tvWeather,R.id.tvDataBinding})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tvJoke:
                // 动手制造异常
                String str = null;
                String[] split = str.split(",");
                JokeActivity.startAction(getActivity());
                break;
            case R.id.tvHistoryToday:
                HistoryTodayActivity.startAction(getActivity());
                break;
            case R.id.tvLogin:
                LoginActivity.startAction(getActivity());
                break;
            case R.id.tvMultiple:
                AbToastUtil.showToast(getActivity(),"综合");
                MultipleActivity.startAction(getActivity());
                break;
            case R.id.tvWeChat:
                WeChatActivity.startAction(getActivity());
                break;
            case R.id.tvDataBinding:
                DataBindingActivity.startAction(getActivity());
                break;
            case R.id.tvWeather:
                WeatherActivity.startAction(getActivity());
                break;
            case R.id.tvPDF:
                PDFActivity.startAction(getActivity());
                break;
        }
    }

    @Override
    protected BasePresenter getCurrentPersenter() {
        return null;
    }

    private void setUserLogin(String userName){
        tvLogin.setClickable(false);
        tvLogin.setText(userName);
    }

    private void setUserLogOut(){
        tvLogin.setClickable(true);
        tvLogin.setText(getString(R.string.text_login));
    }

    /**
     * 视图销毁时注销RxBus
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != observableUserLogin) {
            RxBus.get().unregister(LocalConstant.LOGIN_SUCCESS, observableUserLogin);
        }
    }
}
