package com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter;

import com.example.administrator.rxjavaandretrofitsimple.api.RequestClient;
import com.example.administrator.rxjavaandretrofitsimple.api.SimpleResponseObserver;
import com.example.administrator.rxjavaandretrofitsimple.bean.NewsResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.base.BaseStatusPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.NewsDetailsView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/29
 *
 * 类描述：
 */

public class NewsDetailsPresenter extends BaseStatusPresenter<NewsDetailsView> {
    /**
     * 获取新闻信息
     * @param requestType   请求分类
     * @param cacheControl
     */
    public void getNewsInfo( String requestType,String cacheControl) {
        getView().processingDialog();
        addSubscription(RequestClient.getNewsClassify(requestType,cacheControl)
                .subscribe(new SimpleResponseObserver<NewsResponse>() {
                    @Override
                    public void onBeforeResponseOperation() {
                        super.onBeforeResponseOperation();
                        getView().dismissProcessingDialog();
                    }

                    @Override
                    public void onResponse(NewsResponse response) {
                        getView().provideNewsInfo(response.result);
                    }

                    @Override
                    public void onResponseStatusFail(String s, String s1) {
                        super.onResponseStatusFail(s,s1);
                        getView().dismissProcessingDialog();
                    }
                }));
    }
}

