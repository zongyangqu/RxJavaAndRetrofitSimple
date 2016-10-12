package com.example.administrator.rxjavaandretrofitsimple.mvp.model.base;

import com.example.administrator.rxjavaandretrofitsimple.util.ClientService;
import com.example.administrator.rxjavaandretrofitsimple.util.ConfigUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.conventer.FastJsonConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * 类描述：驾考题库Model基类
 * 创建人：quzongyang
 * 创建时间：2016/10/11. 19:55
 * 版本：
 */
public class BaseModel {

    private ClientService clientService;
    protected BaseModel(){
        OkHttpClient client=new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30,TimeUnit.SECONDS).writeTimeout(30,TimeUnit.SECONDS).build();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ConfigUtil.BASE_URL_DRIVING).addConverterFactory(FastJsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        clientService = retrofit.create(ClientService.class);
    }

    public ClientService getClientService() {
        return clientService;
    }
}
