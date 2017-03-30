package com.example.administrator.rxjavaandretrofitsimple.api;

import com.example.administrator.rxjavaandretrofitsimple.api.exception.ResponseExceptionJobber;
import com.example.administrator.rxjavaandretrofitsimple.api.exception.ResponseStatusFailException;
import com.example.administrator.rxjavaandretrofitsimple.bean.base.BaseResponse;

import rx.Observer;
import rx.Subscriber;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/22
 *
 * 类描述：接口回调返回码统一处理类
 */

public abstract class RxNetworkResponseObserver<T> extends Subscriber<T> {

    public static final String TAG = RxNetworkResponseObserver.class.getSimpleName();

    public RxNetworkResponseObserver() {
    }

    private void checkResponseStatus(Object response){
        //// TODO: 2017/1/22 校验接口返回码  想做什么做什么
        if (response instanceof BaseResponse) {
            if ("10001".equals(((BaseResponse) response).error_code)) {//请求KEY错误

            } else if ("0".equals(((BaseResponse) response).error_code)) {//请求正确

            }
        }
    }

    @Override
    public final void onError(Throwable e) {
        try {
            String errorMessage = ResponseExceptionJobber.analyze(e);
            onBeforeResponseOperation();
            onResponseFail(errorMessage);
        } catch (Exception ex) {

        }
    }

    /**
     * Invoked when {@link Observer#onError(Throwable)}
     *
     * @param msg Detail error message to display.
     */
    public abstract void onResponseFail(String msg);

    @Override
    public final void onCompleted() {

    }

    @Override
    public final void onNext(T t) {
        try {
            checkResponseStatus(t);
            ResponseExceptionJobber.check(t);
            onBeforeResponseOperation();
            onResponse(t);
        } catch (ResponseStatusFailException re) {
            onBeforeResponseOperation();
            onResponseStatusFail(((BaseResponse) t).error_code, ((BaseResponse) t).reason);
            onResponseStatusFail(((BaseResponse) t).error_code, t);
        } catch (Exception e) {
            onResponseOptionFail();
        } finally {
            onNextFinally();
        }
    }

    /**
     * Invoke before {@link RxNetworkResponseObserver#onResponse(Object)},
     * {@link RxNetworkResponseObserver#onResponseFail(String)},
     * {@link RxNetworkResponseObserver#onResponseStatusFail(String, String)}
     */
    public void onBeforeResponseOperation() {

    }

    /**
     * Invoked when {@link Observer#onNext(Object)} success.<br/>
     * If some exceptions maybe throws, implement {@link RxNetworkResponseObserver#onResponseOptionFail()}.
     *
     * @param t Response entity.
     */
    public abstract void onResponse(T t);

    /**
     * Invoked when response success but status false.
     *
     * @param msgCode From server.
     */
    public abstract void onResponseStatusFail(String msgCode, String msg);

    /**
     * Form some scene that need response entity.
     *
     * @param msgCode From server.
     * @param t       Response entity.
     */
    public void onResponseStatusFail(String msgCode, T t) {

    }

    /**
     * Invoked when {@link RxNetworkResponseObserver#onResponse(Object)} throws some exception.
     */
    public void onResponseOptionFail() {

    }

    /**
     * Invoked for some tail work.
     */
    public void onNextFinally() {

    }

}

