package com.example.administrator.rxjavaandretrofitsimple;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.bean.LoginEntity;
import com.example.administrator.rxjavaandretrofitsimple.rxbus.RxBus;
import com.example.administrator.rxjavaandretrofitsimple.util.ActivityUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.ConfigUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.db.UserDao;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_save_user;
    private Button btn_question;
    private TextView tv_login_info;
    private Observable<String> mUserLoginObserver;
    private UserDao userDao;
    private LoginEntity.UserBean userBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        init();
        setListener();
    }

    public void init(){
        userDao = UserDao.getInstance(getActivity());
        userBean = userDao.loadUserEntity();
        if(userBean == null){
            tv_login_info.setText("亲！您还未登陆");
        }else{
            tv_login_info.setText("成功登陆，欢迎您");
        }
    }
    public void initView(){
        btn_save_user = (Button) findViewById(R.id.btn_save_user);
        btn_question = (Button) findViewById(R.id.btn_question);
        tv_login_info = (TextView) findViewById(R.id.tv_login_info);
    }
    public void setListener(){
        btn_save_user.setOnClickListener(this);
        btn_question.setOnClickListener(this);
        /**
         * 登陆信息事件回调
         */
        mUserLoginObserver = RxBus.get().register(ConfigUtil.USERINFO, String.class);
        mUserLoginObserver.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String _s) {
                if(_s.equals(ConfigUtil.USERLOGIN)){//登陆回调
                    tv_login_info.setText("成功登陆，欢迎您");
                }else if(_s.equals(ConfigUtil.USEREXIT)){//清除用户信息的回调
                    tv_login_info.setText("亲！您还未登陆");
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save_user:
                ActivityUtil.startLoginActivity(getActivity());
                break;
            case R.id.btn_question:
                ActivityUtil.startDrivingTestActivity(getActivity());
                break;
        }
    }

    public Activity getActivity(){
        return this;
    }
}
