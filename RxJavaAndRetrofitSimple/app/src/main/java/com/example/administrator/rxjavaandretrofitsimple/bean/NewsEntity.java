package com.example.administrator.rxjavaandretrofitsimple.bean;

import com.example.administrator.rxjavaandretrofitsimple.bean.base.BaseEntity;

import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：
 */

public class NewsEntity extends BaseEntity{

    public ResultBean result;
    public static class ResultBean {
        public String stat;
        public List<DataBean> data;
        public static class DataBean {
            public String uniquekey;
            public String title;
            public String date;
            public String category;
            public String author_name;
            public String url;
            public String thumbnail_pic_s;
            public String thumbnail_pic_s02;
            public String thumbnail_pic_s03;
        }
    }
}
