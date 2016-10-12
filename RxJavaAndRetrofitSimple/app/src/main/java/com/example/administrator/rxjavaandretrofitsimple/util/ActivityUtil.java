package com.example.administrator.rxjavaandretrofitsimple.util;

import android.app.Activity;
import android.content.Intent;

import com.example.administrator.rxjavaandretrofitsimple.ui.DrivingTestActivity;
import com.example.administrator.rxjavaandretrofitsimple.ui.LoginActivity;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/10/11. 19:38
 * 版本：
 */
public class ActivityUtil {

    public static void startLoginActivity(Activity _activity) {
        Intent in = new Intent(_activity, LoginActivity.class);
        _activity.startActivity(in);
    }
    public static void startDrivingTestActivity(Activity _activity) {
        Intent in = new Intent(_activity, DrivingTestActivity.class);
        _activity.startActivity(in);
    }

}
