package com.example.administrator.rxjavaandretrofitsimple.mvp.model;

import com.example.administrator.rxjavaandretrofitsimple.api.ApiManager;
import com.example.administrator.rxjavaandretrofitsimple.api.ApiService;
import com.example.administrator.rxjavaandretrofitsimple.api.HostType;
import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvp.model.base.BaseModel;
import com.example.administrator.rxjavaandretrofitsimple.util.client.NetParams;

import java.util.Map;

import rx.Observable;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/18
 *
 * 类描述：
 */

public class PhotoViewModel extends BaseModel{
    /**
     * 获取微信精选
     * @param cacheControl
     * @param size
     * @param page
     * @return
     */
    public Observable<PhotoViewResponse> getPhotoList(String cacheControl, int size, int page) {
        ApiService clientService = ApiManager.getDefault(HostType.GANK_GIRL_PHOTO);
        return clientService.getPhotoList(cacheControl, size, page);
    }
}
