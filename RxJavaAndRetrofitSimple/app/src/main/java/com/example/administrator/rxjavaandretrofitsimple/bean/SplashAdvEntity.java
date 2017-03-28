package com.example.administrator.rxjavaandretrofitsimple.bean;

import java.io.Serializable;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/24
 *
 * 类描述：
 */

public class SplashAdvEntity implements Serializable {

    public String link;

    public String content;//网络图片路径

    public String version;

    public String isAlive;//1 活跃  2不活跃
    public String filePath;//本地缓存路径

    public SplashAdvEntity() {
    }

    public SplashAdvEntity(
            String isAlive,
            String link,
            String content,
            String version) {
        this.link = link;
        this.isAlive = isAlive;
        this.content = content;
        this.version = version;
    }
}
