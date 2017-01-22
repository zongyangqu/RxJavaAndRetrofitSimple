package com.example.administrator.rxjavaandretrofitsimple.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/22
 *
 * 类描述：
 */

public class WeChatHolder extends RecyclerView.ViewHolder{
    public ImageView iv_wechat_img;
    public TextView tv_wechat_title;

    public WeChatHolder(View itemView) {
        super(itemView);
        initHeadView(itemView);
    }

    public void initHeadView(View itemView){
        tv_wechat_title = (TextView) itemView.findViewById(R.id.tv_wechat_title);
        iv_wechat_img = (ImageView) itemView.findViewById(R.id.iv_wechat_img);
    }
}
