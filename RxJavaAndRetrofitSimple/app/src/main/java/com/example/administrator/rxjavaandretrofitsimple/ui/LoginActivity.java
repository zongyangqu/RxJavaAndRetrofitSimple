package com.example.administrator.rxjavaandretrofitsimple.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.LoginEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.LoginModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.LoginPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.LoginView;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.base.BaseView;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.db.UserDao;

/**
 * 类描述：模拟登陆功能
 * 创建人：quzongyang
 * 创建时间：2016/10/11. 19:39
 * 版本：
 */
public class LoginActivity extends Activity implements LoginView,View.OnClickListener{

    private Button btn_login;
    private Button btn_delete_user;
    private LinearLayout ll_state_loading;
    private LoginPresenter loginPresenter;
    private UserDao userDao;
    private LoginEntity.UserBean userBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        init();
        setListener();
    }

    public void initView(){
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_delete_user = (Button) findViewById(R.id.btn_delete_user);
        ll_state_loading = (LinearLayout) findViewById(R.id.ll_state_loading);
    }

    /**
     * 初始化MVP
     */
    public void init(){
        userDao = UserDao.getInstance(getActivity());
        loginPresenter = new LoginPresenter();
        loginPresenter.attachModel(new LoginModel());
        loginPresenter.attachView(this);
    }
    public void setListener(){
        btn_login.setOnClickListener(this);
        btn_delete_user.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                userBean = userDao.loadUserEntity();
                if(userBean == null){
                    loginPresenter.login("1ebe5a3e07418853d086901a8cc44763","220.194.43.29");
                }else{
                    AbToastUtil.showToast(getActivity(),"已经登陆过了");
                }
                break;
            case R.id.btn_delete_user:
                userDao.removeUserEntity();
                break;
        }
    }

    @Override
    public void updateView(LoginEntity loginEntity) {
        if (loginEntity != null) {
            if(loginEntity.getResultcode() == 200){
                AbToastUtil.showToast(getActivity(),"登陆成功");
            }else{
                AbToastUtil.showToast(getActivity(),loginEntity.getReason());
            }
        }
    }

    @Override
    public void hideLoadingView() {
        ll_state_loading.setVisibility(View.GONE);
    }

    @Override
    public void startLoadingView() {
        ll_state_loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(String errMsg) {
        AbToastUtil.showToast(getActivity(),errMsg);
    }

    public Activity getActivity(){
        return this;
    }
}
