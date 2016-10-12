package com.example.administrator.rxjavaandretrofitsimple.mvp.presenter.base;

/**
 * 类描述：presenter辅助接口
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 11:24
 * 版本：
 */
public interface PresenterHelper<V, M> {
    void attachView(V _view);    //设置视图view

    void detachView();    //销毁视图view

    void attachModel(M _model); //将数据源附加到业务类上

}