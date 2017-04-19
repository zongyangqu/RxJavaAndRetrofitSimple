package com.example.administrator.rxjavaandretrofitsimple.mvpStandard.utils;

import android.app.Activity;
import android.content.Context;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.util.AbToastUtil;
import com.example.administrator.rxjavaandretrofitsimple.util.NetConnectionUtils;
import com.example.administrator.rxjavaandretrofitsimple.util.ProgressDialogUtils;

import rx.Subscriber;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/19
 *
 * 类描述：订阅封装
 */
/*_apiService.login(mobile, verifyCode)
        .//省略
        .subscribe(new RxSubscriber<User user>(mContext,false) {
@Override
public void _onNext(User user) {
        // 处理user
        }

@Override
public void _onError(String msg) {
        ToastUtil.showShort(mActivity, msg);
        });*/

public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog=true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog= true;
    }
    public void hideDialog() {
        this.showDialog= true;
    }

    public RxSubscriber(Context context, String msg,boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog=showDialog;
    }
    public RxSubscriber(Context context) {
        this(context, BaseApplication.getInstance().getString(R.string.loading),true);
    }
    public RxSubscriber(Context context,boolean showDialog) {
        this(context, BaseApplication.getInstance().getString(R.string.loading),showDialog);
    }

    @Override
    public void onCompleted() {
        if (showDialog)
            ProgressDialogUtils.dismiss();
            //LoadingDialog.cancelDialogForLoading();
    }
    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            try {
                ProgressDialogUtils.show((Activity) mContext,msg);
                //LoadingDialog.showDialogForLoading((Activity) mContext,msg,true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onNext(T t) {
        _onNext(t);
    }
    @Override
    public void onError(Throwable e) {
        if (showDialog)
            ProgressDialogUtils.dismiss();
            //LoadingDialog.cancelDialogForLoading();
        e.printStackTrace();
        //网络
        if (!NetConnectionUtils.isNetConnected(BaseApplication.getInstance())) {
            _onError(BaseApplication.getInstance().getString(R.string.no_net));
        }
        //服务器
        else if (e instanceof Exception) {
            _onError(e.getMessage());
        }
        //其它
        else {
            _onError(BaseApplication.getInstance().getString(R.string.net_error));
        }
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}

