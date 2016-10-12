package com.example.administrator.rxjavaandretrofitsimple;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.rxjavaandretrofitsimple.util.ActivityUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_save_user;
    private Button btn_question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }
    public void initView(){
        btn_save_user = (Button) findViewById(R.id.btn_save_user);
        btn_question = (Button) findViewById(R.id.btn_question);
    }
    public void setListener(){
        btn_save_user.setOnClickListener(this);
        btn_question.setOnClickListener(this);
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
