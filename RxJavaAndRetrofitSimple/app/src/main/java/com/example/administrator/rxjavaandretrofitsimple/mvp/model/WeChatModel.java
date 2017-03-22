package com.example.administrator.rxjavaandretrofitsimple.mvp.model;

import com.example.administrator.rxjavaandretrofitsimple.api.ApiManager;
import com.example.administrator.rxjavaandretrofitsimple.bean.JokeResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatEntity;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.base.BaseModel;
import com.example.administrator.rxjavaandretrofitsimple.util.client.NetParams;

import java.util.Map;

import rx.Observable;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/22
 *
 * 类描述：微信精选
 */

public class WeChatModel extends BaseModel{

    /**
     * 获取微信精选
     * @param pno
     * @param ps
     * @param key
     * @return
     */
    public Observable<WeChatEntity> getWeChat(String pno, String ps, String key) {
        Map<String, String> params = NetParams.getInstance().getWeChat(pno,ps,key);
        return getClientService().getWeChat(params);
    }

}
