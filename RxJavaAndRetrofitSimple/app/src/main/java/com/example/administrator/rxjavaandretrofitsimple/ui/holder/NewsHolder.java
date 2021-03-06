package com.example.administrator.rxjavaandretrofitsimple.ui.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/20
 *
 * 类描述：新闻Holder
 */

public class NewsHolder extends RecyclerView.ViewHolder{
    public ImageView iv_news_img;
    public TextView tv_news_title;
    public TextView tv_news_date;
    public RelativeLayout rlNewsLayout;
    public TextView tvAuthorName;

    public NewsHolder(View itemView) {
        super(itemView);
        initHeadView(itemView);
    }

    public void initHeadView(View itemView){
        tvAuthorName = (TextView) itemView.findViewById(R.id.tvAuthorName);
        tv_news_title = (TextView) itemView.findViewById(R.id.tv_news_title);
        tv_news_date = (TextView) itemView.findViewById(R.id.tv_news_date);
        iv_news_img = (ImageView) itemView.findViewById(R.id.iv_news_img);
        rlNewsLayout = (RelativeLayout) itemView.findViewById(R.id.rlNewsLayout);
    }
}
