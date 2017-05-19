package com.example.administrator.rxjavaandretrofitsimple.bean;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;

/**
 * 作者：quzongyang
 * <p>
 * 创建时间：2017/4/28
 * <p>
 * 类描述：
 */

public class UserEntity {
    private String username;
    private String nickname;
    private int age;
    //http://img05.tooopen.com/images/20150531/tooopen_sy_127457023651.jpg
    private String imgUrl;

    @BindingAdapter("bind:imgUrl")
    public static void getInternetImage(ImageView iv, String imgUrl) {
        Glide.with(BaseApplication.getInstance()).load(imgUrl).into(iv);
    }

    public UserEntity() {
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserEntity(int age, String nickname, String username) {
        this.age = age;
        this.nickname = nickname;
        this.username = username;
    }
}
