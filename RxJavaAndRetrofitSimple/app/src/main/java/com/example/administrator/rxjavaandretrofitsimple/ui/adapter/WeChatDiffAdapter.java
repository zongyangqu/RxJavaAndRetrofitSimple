package com.example.administrator.rxjavaandretrofitsimple.ui.adapter;

import android.content.Context;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatResponse;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/13
 *
 * 类描述：
 */

public class WeChatDiffAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    /**
     * 数据源替换为SortedList，
     * 以前可能会用ArrayList。
     */
    private List<WeChatResponse.ResultBean.ListBean> weChatList;
    private Context mContext;
    private LayoutInflater mInflater;
    private int HEAD = 0;
    private int ITEM  = 1;
    private View mHeaderView;
    private View itemView;

    public void addHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public WeChatDiffAdapter(Context mContext, List<WeChatResponse.ResultBean.ListBean> mDatas){
        this.weChatList = mDatas;
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setDatas(List<WeChatResponse.ResultBean.ListBean> mDatas) {
        this.weChatList = mDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEAD) {
            //这里重新设置布局的宽高以防止宽度不能充满屏幕
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            mHeaderView.setLayoutParams(lp);
            return new HeadViewHolder(mHeaderView);
        } else {
            View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_wechat, parent, false);
            return new WeChatHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == HEAD)
            return;
        final WeChatResponse.ResultBean.ListBean weChat = weChatList.get(getRealPosition(holder));
        if(viewType == ITEM){
            WeChatHolder newsHolder = ((WeChatHolder) holder);
            newsHolder.tv_wechat_title.setText(weChat.title);
            Glide.with(BaseApplication.getInstance()).load(weChat.firstImg).into(newsHolder.iv_wechat_img);
        }
    }

    @Override
    public int getItemCount() {
        if (null != weChatList && weChatList.size() != 0) {
            return weChatList.size() + 1;
        }
        return 0;
    }




    /**
     * 获取数据真实索引
     *
     * @param holder
     * @return
     */
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEAD;
        if (null != weChatList && weChatList.size() != 0) {
            return ITEM;
        }
        return ITEM;
    }

    class WeChatHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_wechat_img)
        ImageView iv_wechat_img;
        @Bind(R.id.tv_wechat_title)
        TextView tv_wechat_title;
        public WeChatHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HeadViewHolder extends RecyclerView.ViewHolder{
        public ImageView ivWechatHead;
        public HeadViewHolder(View headView) {
            super(headView);
            initView(headView);
        }
        public void initView(View itemView){
           // ivWechatHead = (ImageView) itemView.findViewById(R.id.ivWechatHead);
        }
    }
}
