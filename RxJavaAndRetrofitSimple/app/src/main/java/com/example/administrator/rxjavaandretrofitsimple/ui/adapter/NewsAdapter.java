package com.example.administrator.rxjavaandretrofitsimple.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.NewsResponse;
import com.example.administrator.rxjavaandretrofitsimple.ui.holder.NewsHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/20
 *
 * 类描述：新闻数据源Adapter
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private List<NewsResponse.ResultBean.DataBean> newsList = new ArrayList<NewsResponse.ResultBean.DataBean>();
    OnNewsItemClickListener onNewsItemClickListener;

    public void setOnNewsItemClickListener(OnNewsItemClickListener onNewsItemClickListener) {
        this.onNewsItemClickListener = onNewsItemClickListener;
    }

    public NewsAdapter(Context context){
        this.context = context;
    }
    public void setData(List<NewsResponse.ResultBean.DataBean> newsList){
        this.newsList = newsList;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final NewsResponse.ResultBean.DataBean news = newsList.get(position);
        NewsHolder newsHolder = ((NewsHolder) holder);
        newsHolder.tv_news_date.setText(news.date);
        newsHolder.tv_news_title.setText(news.title);
        newsHolder.tvAuthorName.setText(news.author_name);
        Glide.with(BaseApplication.getInstance()).load(news.thumbnail_pic_s).error(R.mipmap.ic_launcher).into(newsHolder.iv_news_img);
        newsHolder.rlNewsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewsItemClickListener.onNewsClick(news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public interface OnNewsItemClickListener{
        void onNewsClick(NewsResponse.ResultBean.DataBean response);
    }
}
