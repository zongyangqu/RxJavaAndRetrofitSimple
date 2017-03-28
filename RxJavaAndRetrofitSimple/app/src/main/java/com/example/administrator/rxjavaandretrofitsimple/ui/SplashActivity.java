package com.example.administrator.rxjavaandretrofitsimple.ui;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.SplashAdvEntity;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.MainActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.SplashAdvertActivity;
import com.example.administrator.rxjavaandretrofitsimple.util.xmlparse.PullSplashAdvParser;
import com.example.administrator.rxjavaandretrofitsimple.util.xmlparse.SplashAdvParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：
 */

public class SplashActivity extends AppCompatActivity {

    ImageView ivLogo;
    TextView tvName;
    private SplashAdvParser parser;
    private List<SplashAdvEntity> splashAdvList = new ArrayList<SplashAdvEntity>();
    private SplashAdvEntity splashAdvShowEntity;//需要显示的启动页广告

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initView();
    }


    public void initView() {
        initAdvertData();
        ivLogo = (ImageView) findViewById(R.id.iv_logo);
        tvName = (TextView) findViewById(R.id.tv_name);
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofPropertyValuesHolder(tvName, alpha, scaleX, scaleY);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(ivLogo, alpha, scaleX, scaleY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1, objectAnimator2);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(null == splashAdvShowEntity){
                    MainActivity.startAction(SplashActivity.this);
                    finish();
                }else{
                    SplashAdvertActivity.startFrom(SplashActivity.this,splashAdvShowEntity);
                    finish();
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }

    /**
     * 检测广告信息
     */
    public void initAdvertData(){
        try {
            InputStream is = openFileInput("splashAdv.xml");
            parser = new PullSplashAdvParser();
            splashAdvList = parser.parse(is);
            if (!splashAdvList.isEmpty()) {
                for (int i = 0; i < splashAdvList.size(); i++) {
                    SplashAdvEntity splashAdvEntity = splashAdvList.get(i);
                    if(!"1".equals(splashAdvEntity.isAlive)|| TextUtils.isEmpty(splashAdvEntity.filePath)){
                        splashAdvList.remove(splashAdvEntity);
                        i--;
                    }
                }
            }
            if (!splashAdvList.isEmpty()) {
                //int index = new Random().nextInt(splashAdvList.size());
                splashAdvShowEntity = splashAdvList.get(0);
            }
        } catch (Exception e) {
        }
    }

}
