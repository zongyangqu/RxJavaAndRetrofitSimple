package com.example.administrator.rxjavaandretrofitsimple.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.SplashAdvEntity;
import com.example.administrator.rxjavaandretrofitsimple.ui.base.BaseNoNetworkActivity;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/24
 *
 * 类描述：启动页广告
 */

public class SplashAdvertActivity extends BaseNoNetworkActivity{

    @Bind(R.id.ivSplash)
    ImageView ivSplash;
    @Bind(R.id.tvCountDown)
    TextView tvCountDown;
    @Bind(R.id.rlCountDown)
    RelativeLayout rlCountDown;
    private SplashAdvEntity splashAdvEntity;
    private int countDown = 4;
    Timer timer = new Timer();
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvCountDown.setText(countDown+"");
                    if (countDown < 1) {
                        rlCountDown.setVisibility(View.GONE);
                        toNext();
                    }
            }
        }
    };
    public static void startFrom(Activity context, SplashAdvEntity splashAdvEntity) {
        Intent intent = new Intent(context, SplashAdvertActivity.class);
        intent.putExtra("splashAdvEntity", splashAdvEntity);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.fade_in,
                R.anim.fade_out);
    }

    /**
     * 执行下一步进入应用操作
     */
    private void toNext(){
        if(null != timer){
            timer.cancel();
        }
        MainActivity.startAction(getActivity());
        finish();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash_adv;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        splashAdvEntity = (SplashAdvEntity) getIntent().getSerializableExtra("splashAdvEntity");
        Glide.with(BaseApplication.getInstance())
                .load(new File(splashAdvEntity.filePath))
                .error(R.mipmap.firing_page)
                .into(new GlideDrawableImageViewTarget(ivSplash) {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        timer.schedule(task, 1000, 1000);
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        toNext();
                    }
                });
    }

    @Override
    protected void onRetryClick() {

    }

    @Override
    protected void initTitleBar() {
        showTitle(false);
    }

    @Override
    protected Activity getActivity() {
        return this;
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            countDown--;
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(null != timer){
            timer.cancel();
        }
    }
}
