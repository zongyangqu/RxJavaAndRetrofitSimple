package com.example.administrator.rxjavaandretrofitsimple.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.JokeResponse;
import com.example.administrator.rxjavaandretrofitsimple.bean.WeChatResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/21
 *
 * 类描述：笑话数据源
 */

public class JokeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private int HEAD = 0;
    private int ITEM  = 1;
    private View mHeaderView;
    List<JokeResponse.ResultBean.DataBean> dataList = new ArrayList<JokeResponse.ResultBean.DataBean>();

    public void addHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public JokeAdapter(List<JokeResponse.ResultBean.DataBean> dataList) {
        this.dataList = dataList;
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
            View inflate = LayoutInflater.from(BaseApplication.getInstance()).inflate(R.layout.item_joke, parent, false);
            return new ViewHolder(inflate);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == HEAD)
            return;
        final JokeResponse.ResultBean.DataBean response = dataList.get(getRealPosition(holder));
        if(viewType == ITEM){
            ViewHolder viewHolder = ((ViewHolder) holder);
            viewHolder.tvJokeMain.setText(response.content);
            viewHolder.tvUpdateTime.setText(response.updatetime);
        }
    }

    @Override
    public int getItemCount() {
        if (null != dataList && !dataList.isEmpty()) {
            return dataList.size() + 1;
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
        if (null != dataList && !dataList.isEmpty()) {
            return ITEM;
        }
        return ITEM;
    }


    static class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.tvJokeMain)
        TextView tvJokeMain;
        @Bind(R.id.tvUpdateTime)
        TextView tvUpdateTime;
        public ViewHolder(View itemView) {
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
