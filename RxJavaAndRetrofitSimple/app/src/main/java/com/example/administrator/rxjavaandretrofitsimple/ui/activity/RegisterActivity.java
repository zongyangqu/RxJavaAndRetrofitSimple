package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseNoNetworkActivity;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.rx.RxBusEvent;
import com.example.administrator.rxjavaandretrofitsimple.util.rx.event.RegisterEvent;

import butterknife.Bind;
import greendao.UserDB;
import greendao.db.User;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/27
 *
 * 类描述：模拟注册
 */

public class RegisterActivity extends BaseNoNetworkActivity{
    @Bind(R.id.etLoginPhone)
    AppCompatEditText etLoginPhone;
    @Bind(R.id.etLoginPwd)
    AppCompatEditText etLoginPwd;
    @Bind(R.id.etUsername)
    AppCompatEditText etUsername;
    @Bind(R.id.etAddress)
    AppCompatEditText etAddress;
    @Bind(R.id.etAge)
    AppCompatEditText etAge;
    @Bind(R.id.etSex)
    AppCompatEditText etSex;
    @Bind(R.id.tvRegister)
    TextView tvRegister;

    /**
     * 入口
     * @param activity
     */
    public static void startAction(Activity activity){
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userPhone = etLoginPhone.getText().toString().trim();
                String password = etLoginPwd.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String userAddress = etAddress.getText().toString().trim();
                String userAge = etAge.getText().toString().trim();
                String userSex = etSex.getText().toString().trim();
                if(TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(password)){
                    AbToastUtil.showToast(getActivity(),"手机号和密码必填！！！");
                    return;
                }
                User user = new User();
                user.setPhone(userPhone);
                user.setPsw(password);
                user.setAge(userAge);
                user.setName(username);
                user.setSex(userSex);
                user.setAddress(userAddress);
                boolean saveSucess = UserDB.insertUser(user)?true:false;
                if(saveSucess){
                    AbToastUtil.showToast(getActivity(),"注册成功");
                    //注册成功将用户信息传给登录界面
                    RxBusEvent.post(new RegisterEvent(userPhone,password));
                    finish();
                }else{
                    AbToastUtil.showToast(getActivity(),"注册失败");
                }
            }
        });
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    protected void initTitleBar() {
        setTitleCenter("注册");
    }

    @Override
    protected Activity getActivity() {
        return this;
    }
}
