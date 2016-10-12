package com.example.administrator.rxjavaandretrofitsimple.mvp.model;

import com.example.administrator.rxjavaandretrofitsimple.bean.DrivingQuestionEntity;
import com.example.administrator.rxjavaandretrofitsimple.bean.LoginEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.base.BaseModel;
import com.example.administrator.rxjavaandretrofitsimple.util.client.NetParams;

import java.util.Map;

import rx.Observable;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 17:10
 * 版本：
 */
public class DrivingQuestionModel extends BaseModel{
    /**
     * 模拟登陆
     */
    public Observable<DrivingQuestionEntity> getQuestion(String key, String subject,String model){
        Map<String, String> params = NetParams.getInstance().getQuestion(key,subject,model);
        return getClientService().getDrivingQuestion(params);
    }
}
