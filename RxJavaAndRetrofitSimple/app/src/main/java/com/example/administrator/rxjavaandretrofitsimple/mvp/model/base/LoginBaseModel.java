package com.example.administrator.rxjavaandretrofitsimple.mvp.model.base;

/**
 * 类名称：
 * 类描述：模拟登陆Model基类
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 17:32
 * 版本：
 */
public class LoginBaseModel extends BaseModel{
    /*private ApiService clientService;
    protected LoginBaseModel(){
        OkHttpClient client=new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30,TimeUnit.SECONDS).writeTimeout(30,TimeUnit.SECONDS).build();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ConfigUtil.BASE_URL_LOGIN).addConverterFactory(FastJsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        clientService = retrofit.create(ClientService.class);
    }

    public ApiService getClientService() {
        return clientService;
    }*/
}
