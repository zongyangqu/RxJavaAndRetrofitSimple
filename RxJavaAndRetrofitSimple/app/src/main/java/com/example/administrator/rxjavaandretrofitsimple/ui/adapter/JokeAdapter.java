package com.example.administrator.rxjavaandretrofitsimple.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.JokeResponse;

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
    List<JokeResponse.ResultBean.DataBean> dataList = new ArrayList<JokeResponse.ResultBean.DataBean>();

    public JokeAdapter(List<JokeResponse.ResultBean.DataBean> dataList) {
        this.dataList = dataList;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(BaseApplication.getInstance()).inflate(R.layout.item_joke, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = ((ViewHolder) holder);
        JokeResponse.ResultBean.DataBean response = dataList.get(position);
        viewHolder.tvJokeMain.setText(response.content);
        viewHolder.tvUpdateTime.setText(response.updatetime);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        @Bind(R.id.tvJokeMain)
        TextView tvJokeMain;
        @Bind(R.id.tvUpdateTime)
        TextView tvUpdateTime;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
