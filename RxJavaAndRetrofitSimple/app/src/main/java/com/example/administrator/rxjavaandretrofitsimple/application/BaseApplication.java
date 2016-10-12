package com.example.administrator.rxjavaandretrofitsimple.application;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.LruCache;

public class BaseApplication extends Application {
    private static BaseApplication myAppLication;//全局context
    private static Handler mMainThreadHandler; //全局消息调度器
    private static LruCache<String, Bitmap> mLruCache;//bitmap缓冲器


    @Override
    public void onCreate() {
        super.onCreate();
        myAppLication = (BaseApplication) this.getApplicationContext(); //获取全局的上下文对象
        mMainThreadHandler = new Handler(); //获取全局的handler对象
        mLruCache = new LruCache<>(10);
        //CrashHandler.getInstance().init(getApplicationContext());

    }

    /**
     * 单例模式中获取唯一的MyApplication实例
     *
     * @return
     */
    public static BaseApplication getInstance() {
        return myAppLication;
    }

    /**
     * 单例模式中获取唯一的MyApplication实例
     *
     * @return
     */
    public static Handler getmMainThreadHandler() {
        return mMainThreadHandler;
    }

    /***
     * 获取全局的图片缓存容器对象
     *
     * @return
     */
    public static LruCache<String, Bitmap> getMainLrucache() {
        return mLruCache;
    }

}
