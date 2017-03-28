package com.example.administrator.rxjavaandretrofitsimple.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.SplashAdvEntity;
import com.example.administrator.rxjavaandretrofitsimple.util.xmlparse.PullSplashAdvParser;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/24
 *
 * 类描述：异步下载任务
 */

public class DownLoaderAsyncTask  extends AsyncTask<List<SplashAdvEntity>, Integer, String> {

    private List<SplashAdvEntity> splashAdvEntities;//启动页广告集合
    private PullSplashAdvParser parser;



    //onPreExecute()方法用于在执行异步任务前,主线程做一些准备工作
    @Override
    protected void onPreExecute() {
        super.onPreExecute();


    }

    //doInBackground()方法用于在执行异步任务,不可以更改主线程中UI
    @Override
    protected String doInBackground(List<SplashAdvEntity>... params) {
        Log.i("dodingBack-->","dodingBack");
        splashAdvEntities = params[0];
        if (splashAdvEntities != null && splashAdvEntities.size() > 0) {
            for (int i = 0; i < splashAdvEntities.size(); i++) {
                File file = null;
                SplashAdvEntity splashAdvEntity = splashAdvEntities.get(i);
                try {
                    file = Glide.with(BaseApplication.getInstance())
                            .load(splashAdvEntity.content)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (file != null) {
                        splashAdvEntity.filePath = file.getAbsolutePath();
                    } else {
                        splashAdvEntities.remove(splashAdvEntity);
                        i--;
                    }
                }
            }
            parser = new PullSplashAdvParser();
            if (splashAdvEntities != null && !splashAdvEntities.isEmpty()) {//证明有图片缓存成功了
                try {
                    String xml = parser.serialize(splashAdvEntities);  //序列化
                    FileOutputStream fos = BaseApplication.getInstance().openFileOutput("splashAdv.xml", Context.MODE_PRIVATE);
                    fos.write(xml.getBytes("UTF-8"));
                } catch (Exception e) {
                }
            }
        } else {
            parser = new PullSplashAdvParser();
            try {
                String xml = "";  //序列化
                FileOutputStream fos = BaseApplication.getInstance().openFileOutput("splashAdv.xml", Context.MODE_PRIVATE);
                fos.write(xml.getBytes("UTF-8"));
            } catch (Exception e) {
            }
        }
        return null;
    }



    //onPostExecute()方法用于异步任务执行完成后,在主线程中执行的操作
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

    }

    //onProgressUpdate()方法用于更新异步执行中,在主线程中处理异步任务的执行信息
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }

    //onCancelled()方法用于异步任务被取消时,在主线程中执行相关的操作
    @Override
    protected void onCancelled() {
        super.onCancelled();

    }
}



