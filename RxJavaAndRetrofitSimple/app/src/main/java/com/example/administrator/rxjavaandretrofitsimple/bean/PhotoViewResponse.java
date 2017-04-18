package com.example.administrator.rxjavaandretrofitsimple.bean;

import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/18
 *
 * 类描述：
 */

public class PhotoViewResponse {


    /**
     * error : false
     * results : [{"_id":"58f5649b421aa9544825f89f","createdAt":"2017-04-18T08:58:03.511Z","desc":"4-18","publishedAt":"2017-04-18T11:34:29.203Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-18-17882540_190116561497334_440657494176432128_n.jpg","used":true,"who":"yjyj"},{"_id":"58f3980c421aa9544b773ff1","createdAt":"2017-04-17T00:13:00.136Z","desc":"4-17","publishedAt":"2017-04-17T11:32:14.674Z","source":"chrome","type":"福利","url":"http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-16-17934400_1738549946443321_2924146161843437568_n.jpg","used":true,"who":"daimajia"}]
     */

    public boolean error;
    public List<PhotoViewBean> results;

    public static class PhotoViewBean {
        /**
         * _id : 58f5649b421aa9544825f89f
         * createdAt : 2017-04-18T08:58:03.511Z
         * desc : 4-18
         * publishedAt : 2017-04-18T11:34:29.203Z
         * source : chrome
         * type : 福利
         * url : http://7xi8d6.com1.z0.glb.clouddn.com/2017-04-18-17882540_190116561497334_440657494176432128_n.jpg
         * used : true
         * who : yjyj
         */

        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
    }
}
