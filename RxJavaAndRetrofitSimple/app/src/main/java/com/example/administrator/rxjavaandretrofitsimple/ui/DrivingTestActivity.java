package com.example.administrator.rxjavaandretrofitsimple.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.DrivingQuestionEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.DrivingQuestionModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.DrivingQuestionPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvp.view.DrivingQuestionView;
import com.example.administrator.rxjavaandretrofitsimple.ui.adapter.QuestionAdapter;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 16:16
 * 版本：
 */
public class DrivingTestActivity extends Activity implements DrivingQuestionView{

    private ListView lv_question;
    private QuestionAdapter adapter;
    private DrivingQuestionPresenter presenter;
    private LinearLayout ll_state_loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driving_test);
        initView();
        init();
    }
    public void initView(){
        lv_question = (ListView) findViewById(R.id.lv_question);
        ll_state_loading = (LinearLayout) findViewById(R.id.ll_state_loading);
    }
    public void init(){
        presenter = new DrivingQuestionPresenter();
        presenter.attachModel(new DrivingQuestionModel());
        presenter.attachView(this);
        adapter = new QuestionAdapter(this);
        lv_question.setAdapter(adapter);
        getQuestion();
    }


    public void getQuestion(){
        presenter.getDrivingQuestion("d9388813115bb6cc1ae6b8d13e2e79c3","1","c1");
    }

    @Override
    public void updateView(DrivingQuestionEntity drivingQuestionEntity) {
        if (drivingQuestionEntity != null) {
            if(drivingQuestionEntity.getError_code() == 0){
                adapter.setData(drivingQuestionEntity.getResult());
            }else{
                AbToastUtil.showToast(getActivity(),"网络错误");
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
