package com.example.administrator.rxjavaandretrofitsimple.application;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.LruCache;

import com.example.administrator.rxjavaandretrofitsimple.util.crashutils.AppUncaughtExceptionHandler;
import com.example.administrator.rxjavaandretrofitsimple.util.crashutils.SdcardConfig;

import greendao.DaoMaster;
import greendao.DaoSession;
import greendao.core.DbCore;

public class BaseApplication extends Application {

    private static DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        myAppLication = (BaseApplication) this.getApplicationContext(); //获取全局的上下文对象
        mMainThreadHandler = new Handler(); //获取全局的handler对象
        mLruCache = new LruCache<>(10);
        //配置数据库
        //setupDatabase();
        //初始化数据库
        DbCore.init(this);
        // 初始化文件目录
        SdcardConfig.getInstance().initSdcard();
        // 捕捉异常
        AppUncaughtExceptionHandler crashHandler = AppUncaughtExceptionHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库simple.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "simple.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }

    private static BaseApplication myAppLication;//全局context
    private static Handler mMainThreadHandler; //全局消息调度器
    private static LruCache<String, Bitmap> mLruCache;//bitmap缓冲器

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

    /**
     * 获取自身App安装包信息
     *
     * @return
     */
    public PackageInfo getLocalPackageInfo() {
        return getPackageInfo(getPackageName());
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo(String packageName) {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }

}
