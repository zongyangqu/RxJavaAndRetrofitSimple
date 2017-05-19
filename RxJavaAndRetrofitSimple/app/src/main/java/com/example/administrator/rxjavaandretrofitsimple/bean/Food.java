package com.example.administrator.rxjavaandretrofitsimple.bean;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;

/**
 * 作者：quzongyang
 * <p>
 * 创建时间：2017/5/2
 * <p>
 * 类描述：
 */

public class Food {
    private String description;
    private String img;
    private String keywords;
    private String summary;

    public Food() {
    }

    public Food(String description, String img, String keywords, String summary) {
        this.description = description;
        this.img = img;
        this.keywords = keywords;
        this.summary = summary;
    }

    @BindingAdapter("bind:img")
    public static void loadInternetImage(ImageView iv, String img) {
        Glide.with(BaseApplication.getInstance()).load(img).into(iv);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
