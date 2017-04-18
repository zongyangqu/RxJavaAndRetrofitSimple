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
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatResponse;
import com.example.administrator.rxjavaandretrofitsimple.ui.activity.WeChatActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/22
 *
 * 类描述：微信精选数据源Adapter
 */

public class WeChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private int HEAD = 0;
    private int ITEM  = 1;
    private int spanSize;// 当前每行显示几列
    public View headView;
    private View itemView;
    private List<WeChatResponse.ResultBean.ListBean>weChatList = new ArrayList<WeChatResponse.ResultBean.ListBean>();

    /**
     * 添加自定义头部
     */
    public void addHeadView(View view) {
        this.headView = view;
    }
    public WeChatAdapter(Context context){
        this.context = context;
    }

    public void setData(List<WeChatResponse.ResultBean.ListBean>weChatList){
        this.weChatList = weChatList;
        notifyDataSetChanged();
    }

    public void addData(List<WeChatResponse.ResultBean.ListBean>weChatList){
        this.weChatList.addAll(weChatList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = null;
        // 头部
        if (viewType == WeChatActivity.HEADER_VIEW_ITEM) {
            root = headView;
        } else {// 普通条目
            /** 一行显示一条 */
            if (viewType == WeChatActivity.RECYCLERVIEW_ITEM_SINGLE) {
                root = LayoutInflater.from(context).inflate(R.layout.item_wechat_single, parent, false);
            }
            /** 一行显示两条 */
            else {
                root = LayoutInflater.from(context).inflate(R.layout.item_wechat, parent, false);
            }
        }
        return new WeChatHolder(root, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        // 头部
        if (itemViewType == WeChatActivity.HEADER_VIEW_ITEM) {
            return;
        } else {// 普通条目
            final WeChatResponse.ResultBean.ListBean weChat = weChatList.get(getRealPosition(holder));
            WeChatHolder holder1 = ((WeChatHolder) holder);
            if (itemViewType == WeChatActivity.RECYCLERVIEW_ITEM_DOUBLE) {// 一行两列视图
                holder1.tv_wechat_title.setText(weChat.title);
                Glide.with(BaseApplication.getInstance()).load(weChat.firstImg).into(holder1.iv_wechat_img);
            } else if (itemViewType == WeChatActivity.RECYCLERVIEW_ITEM_SINGLE) {// 一行一列视图
                holder1.ivWechatTitleSingle.setText(weChat.title);
                Glide.with(BaseApplication.getInstance()).load(weChat.firstImg).into(holder1.ivWechatImgSingle);
            }
        }
    }


    /**
     * 设置数据源总的条目
     */
    @Override
    public int getItemCount() {
        if (null != weChatList && !weChatList.isEmpty()) {
            return weChatList.size() + 1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return WeChatActivity.HEADER_VIEW_ITEM;
        } else {
            /** 一行显示一条 */
            if (spanSize == WeChatActivity.RECYCLERVIEW_ITEM_SINGLE) {
                return WeChatActivity.RECYCLERVIEW_ITEM_SINGLE;
                /** 一行显示两条 */
            } else {
                return WeChatActivity.RECYCLERVIEW_ITEM_DOUBLE;
            }
        }
    }





    /**
     * 获取数据真实索引
     *
     * @param holder
     * @return
     */
    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return headView == null ? position : position - 1;
    }


    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    static class WeChatHolder extends RecyclerView.ViewHolder{
        //一行两列视图
        public ImageView iv_wechat_img;
        public TextView tv_wechat_title;

        //一行一列视图
        public ImageView ivWechatImgSingle;
        public TextView ivWechatTitleSingle;

        public WeChatHolder(View itemView,int viewType) {
            super(itemView);
            initView(itemView,viewType);
        }
        private void initView(View itemView, int viewType) {
            if(viewType == WeChatActivity.RECYCLERVIEW_ITEM_DOUBLE){
                iv_wechat_img = (ImageView) itemView.findViewById(R.id.iv_wechat_img);
                tv_wechat_title = (TextView) itemView.findViewById(R.id.tv_wechat_title);
            }else if(viewType == WeChatActivity.RECYCLERVIEW_ITEM_SINGLE){
                ivWechatImgSingle = (ImageView) itemView.findViewById(R.id.ivWechatImgSingle);
                ivWechatTitleSingle = (TextView) itemView.findViewById(R.id.ivWechatTitleSingle);
            }
        }
    }

}
