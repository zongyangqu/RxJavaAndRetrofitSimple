package com.example.administrator.rxjavaandretrofitsimple.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/5
 *
 * 类描述：监听滑动事件的WebView
 */

public class ScrollWebView extends WebView{
    private static final String TAG = "ScrollWebView";
    public OnScrollListener listener;

    public ScrollWebView(Context context) {
        this(context,null);
    }


    public ScrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (listener != null){
            if (t - oldt <= 10){
                listener.onScrollDown();
            }
            if(oldt - t >= 10) {
                listener.onScrollUp();
            }
        }
    }

    public void setListener(OnScrollListener listener){
        this.listener = listener;
    }

    public interface OnScrollListener{
        void onScrollUp();//上滑
        void onScrollDown();//下滑
    }
}
