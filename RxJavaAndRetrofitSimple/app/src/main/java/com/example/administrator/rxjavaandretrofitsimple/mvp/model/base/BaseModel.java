package com.example.administrator.rxjavaandretrofitsimple.mvp.model.base;

import com.example.administrator.rxjavaandretrofitsimple.util.ClientService;
import com.example.administrator.rxjavaandretrofitsimple.util.ConfigUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.Logger;
import com.example.administrator.rxjavaandretrofitsimple.util.conventer.FastJsonConverterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * 类描述：Model基类
 * 创建人：quzongyang
 * 创建时间：2016/10/11. 19:55
 * 版本：
 */
public class BaseModel {
    static final String TAG = "Api.ApiClient";

    private ClientService clientService;
    protected BaseModel(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient _client = new OkHttpClient();
        OkHttpClient.Builder builder = _client.newBuilder();
        builder.interceptors().add(interceptor);
        builder.interceptors().add(logging);
        /*builder.interceptors().add(new HttpLoggingInterceptor(new DebugLogger())
                .setLevel(HttpLoggingInterceptor.Level.BODY));*/
        _client = builder.connectTimeout(30, TimeUnit.SECONDS).readTimeout(30,TimeUnit.SECONDS).writeTimeout(30,TimeUnit.SECONDS).build();
        //OkHttpClient client=new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30,TimeUnit.SECONDS).writeTimeout(30,TimeUnit.SECONDS).build();
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ConfigUtil.BASE_URL_DRIVING).client(_client).addConverterFactory(FastJsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        clientService = retrofit.create(ClientService.class);
    }

    public ClientService getClientService() {
        return clientService;
    }

    private static class DebugLogger implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Logger.BnhLogger.logger.d(TAG, "%s", message);
        }
    }

    private static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = chain.proceed(chain.request());
            return response;
        }
    };
}
