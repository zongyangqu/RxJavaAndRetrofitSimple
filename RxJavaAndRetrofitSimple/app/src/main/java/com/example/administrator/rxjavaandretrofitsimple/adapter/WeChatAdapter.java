package com.example.administrator.rxjavaandretrofitsimple.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatEntity;
import com.example.administrator.rxjavaandretrofitsimple.holder.WeChatHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/22
 *
 * 类描述：微信精选数据源Adapter
 */

public class WeChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<WeChatEntity.ResultBean.ListBean>weChatList = new ArrayList<WeChatEntity.ResultBean.ListBean>();
    public WeChatAdapter(Context context){
        this.context = context;
    }

    public void setData(List<WeChatEntity.ResultBean.ListBean>weChatList){
        this.weChatList = weChatList;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new WeChatHolder(LayoutInflater.from(context).inflate(R.layout.item_wechat, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        WeChatEntity.ResultBean.ListBean weChat = weChatList.get(position);
        WeChatHolder newsHolder = ((WeChatHolder) holder);
        newsHolder.tv_wechat_title.setText(weChat.title);
        Glide.with(BaseApplication.getInstance()).load(weChat.firstImg).into(newsHolder.iv_wechat_img);
    }

    @Override
    public int getItemCount() {
        return weChatList.size();
    }
}
