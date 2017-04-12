package com.example.administrator.rxjavaandretrofitsimple.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrator.rxjavaandretrofitsimple.bean.base.BaseResponse;

import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：
 */

public class NewsResponse extends BaseResponse {

    public ResultBean result;
    public static class ResultBean {
        public String stat;
        public List<DataBean> data;
        public static class DataBean implements Parcelable {
            public String uniquekey;
            public String title;
            public String date;
            public String category;
            public String author_name;
            public String url;
            public String thumbnail_pic_s;
            public String thumbnail_pic_s02;
            public String thumbnail_pic_s03;

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.uniquekey);
                dest.writeString(this.title);
                dest.writeString(this.date);
                dest.writeString(this.category);
                dest.writeString(this.author_name);
                dest.writeString(this.url);
                dest.writeString(this.thumbnail_pic_s);
                dest.writeString(this.thumbnail_pic_s02);
                dest.writeString(this.thumbnail_pic_s03);
            }

            public DataBean() {
            }

            protected DataBean(Parcel in) {
                this.uniquekey = in.readString();
                this.title = in.readString();
                this.date = in.readString();
                this.category = in.readString();
                this.author_name = in.readString();
                this.url = in.readString();
                this.thumbnail_pic_s = in.readString();
                this.thumbnail_pic_s02 = in.readString();
                this.thumbnail_pic_s03 = in.readString();
            }

            public static final Parcelable.Creator<DataBean> CREATOR = new Parcelable.Creator<DataBean>() {
                @Override
                public DataBean createFromParcel(Parcel source) {
                    return new DataBean(source);
                }

                @Override
                public DataBean[] newArray(int size) {
                    return new DataBean[size];
                }
            };
        }
    }
}
