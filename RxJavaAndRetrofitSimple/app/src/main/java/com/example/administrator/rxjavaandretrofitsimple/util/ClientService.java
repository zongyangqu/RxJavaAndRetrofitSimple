package com.example.administrator.rxjavaandretrofitsimple.util;

import com.example.administrator.rxjavaandretrofitsimple.bean.DrivingQuestionEntity;
import com.example.administrator.rxjavaandretrofitsimple.bean.LoginEntity;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/10/11. 20:03
 * 版本：
 */
public interface ClientService {

    /**
     * 获取信息
     *
     * @param params
     * @return
     */
    @FormUrlEncoded
    @POST("ip/ip2addr?ip=www.juhe.cn")
    Observable<LoginEntity> searchIP(@FieldMap Map<String, String> params);

    /**
     *
     * 请求驾考题库
     */
    @FormUrlEncoded
    @POST("jztk/query")
    Observable<DrivingQuestionEntity> getDrivingQuestion(@FieldMap Map<String,String> params);
}
