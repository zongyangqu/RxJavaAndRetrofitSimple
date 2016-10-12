package com.example.administrator.rxjavaandretrofitsimple.mvp.model;

import com.example.administrator.rxjavaandretrofitsimple.bean.LoginEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.base.BaseModel;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.base.LoginBaseModel;
import com.example.administrator.rxjavaandretrofitsimple.util.client.NetParams;

import java.util.Map;

import rx.Observable;

/**
 * 类描述：模拟登陆
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 11:26
 * 版本：
 */
public class LoginModel extends LoginBaseModel {

    /**
     * 模拟登陆
     */
    public Observable<LoginEntity> login(String key, String ip){
        Map<String, String> params = NetParams.getInstance().login(key, ip);
        return getClientService().searchIP(params);
    }
}
