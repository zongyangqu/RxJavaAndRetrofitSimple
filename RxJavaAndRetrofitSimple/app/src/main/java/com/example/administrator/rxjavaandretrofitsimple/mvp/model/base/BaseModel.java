package com.example.administrator.rxjavaandretrofitsimple.mvp.model.base;

import com.example.administrator.rxjavaandretrofitsimple.api.ApiManager;
import com.example.administrator.rxjavaandretrofitsimple.api.ApiService;
import com.example.administrator.rxjavaandretrofitsimple.api.HostType;

/**
 * 类描述：Model基类
 * 创建人：quzongyang
 * 创建时间：2016/10/11. 19:55
 * 版本：
 */
public class BaseModel {
    private ApiService clientService;
    protected BaseModel(){
        clientService = ApiManager.getDefault(HostType.JUHE_DATE_NET_INTERFACE);
    }
    public ApiService getClientService() {
        return clientService;
    }


}
