package com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter;

import com.example.administrator.rxjavaandretrofitsimple.api.RequestClient;
import com.example.administrator.rxjavaandretrofitsimple.api.SimpleResponseObserver;
import com.example.administrator.rxjavaandretrofitsimple.bean.JokeResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.presenter.base.BaseStatusPresenter;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.JokeView;
import com.example.administrator.rxjavaandretrofitsimple.util.client.NetParams;

import java.util.Map;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/20
 *
 * 类描述：
 */

public class JokePresenter extends BaseStatusPresenter<JokeView> {

    /**
     * 获取笑话信息
     *
     * @param page
     * @param pagesize
     * @param key
     */
    public void getJokeInfo(int page, int pagesize, String key) {
        getView().processingDialog();
        Map<String, String> params = NetParams.getInstance().getJoke(page + "", pagesize + "", key);
        addSubscription(RequestClient.getJokeInfo(params)
                .subscribe(new SimpleResponseObserver<JokeResponse>() {
                    @Override
                    public void onBeforeResponseOperation() {
                        super.onBeforeResponseOperation();
                        getView().dismissProcessingDialog();
                    }

                    @Override
                    public void onResponse(JokeResponse response) {
                        if(response.result.data.isEmpty()){
                            getView().displayEmptyPage();
                        }else{
                            getView().provideJokeInfo(response.result);
                        }

                    }

                    @Override
                    public void onResponseFail(String s) {
                        super.onResponseFail(s);
                        getView().displayErrorPage();
                    }

                    @Override
                    public void onResponseStatusFail(String s, String s1) {
                        getView().dismissProcessingDialog();
                        getView().displayErrorPage();
                    }
                }));
    }
}
