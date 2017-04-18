package com.example.administrator.rxjavaandretrofitsimple.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.PhotoViewResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/18
 *
 * 类描述：美图数据源
 */

public class PhotoViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected Context mContext;
    OnItemClickListener onItemClickListener;
    protected List<PhotoViewResponse.PhotoViewBean> mDatas=new ArrayList<PhotoViewResponse.PhotoViewBean>();

    public PhotoViewAdapter(Context context)
    {
        mContext = context;
    }

    public void setData(List<PhotoViewResponse.PhotoViewBean> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void addData(List<PhotoViewResponse.PhotoViewBean> mDatas) {
        this.mDatas.addAll(mDatas);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final PhotoViewResponse.PhotoViewBean photoViewBean = mDatas.get(position);
        PhotoViewHolder viewHolder = (PhotoViewHolder)holder;
        Glide.with(BaseApplication.getInstance()).load(photoViewBean.url).into(viewHolder.iv_photo);
        viewHolder.iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != onItemClickListener){
                    onItemClickListener.onImageViewClick(photoViewBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.iv_photo)
        ImageView iv_photo;
        public PhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener{
        void onImageViewClick(PhotoViewResponse.PhotoViewBean photoViewBean);
    }
}
