package com.example.administrator.rxjavaandretrofitsimple.ui.activity;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;


import com.example.administrator.rxjavaandretrofitsimple.R;


import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.LoginPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.LoginView;
import com.example.administrator.rxjavaandretrofitsimple.rxbus.RxBus;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseStatusMvpActivity;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.LocalConstant;

import butterknife.Bind;
import butterknife.OnClick;
import greendao.UserDB;
import greendao.db.User;

/**
 * 类描述：模拟登陆功能(仅使用数据存储数据)
 * 创建人：quzongyang
 * 创建时间：2016/10/11. 19:39
 * 版本：
 */
public class LoginActivity extends BaseStatusMvpActivity<LoginView, LoginPresenter> implements LoginView{

    @Bind(R.id.tvLoginRegister)
    TextView tvLoginRegister;
    @Bind(R.id.et_login_user)
    AppCompatEditText et_login_user;
    @Bind(R.id.et_login_pwd)
    AppCompatEditText et_login_pwd;


    /**
     * 入口
     * @param activity
     */
    public static void startAction(Activity activity){
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Override
    protected void onViewCreated() {
    }

    /**
     * 注册成功回调
     * @param userPhone
     * @param password
     */
    @Override
    public void registerSuccess(String userPhone, String password) {
        et_login_user.setText(userPhone);
        et_login_pwd.setText(password);
    }

    @OnClick({R.id.tvLoginRegister,R.id.btn_login})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.btn_login:
                String phone = et_login_user.getText().toString().trim();
                String psw = et_login_pwd.getText().toString().trim();
                if(TextUtils.isEmpty(phone) || TextUtils.isEmpty(psw)){
                    AbToastUtil.showToast(visitActivity(),"请输入手机号和密码");
                }else{
                    User user = UserDB.queryUser(phone);
                    if(null == user){
                        AbToastUtil.showToast(visitActivity(),"登录失败");
                    }else {
                        RxBus.get().post(LocalConstant.LOGIN_SUCCESS,user);
                        AbToastUtil.showToast(visitActivity(),"登录成功");
                        finish();
                    }
                }
                break;
            case R.id.tvLoginRegister:
                RegisterActivity.startAction(visitActivity());
                break;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initTitleBar() {
        setTitleCenter("登录");
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

}
