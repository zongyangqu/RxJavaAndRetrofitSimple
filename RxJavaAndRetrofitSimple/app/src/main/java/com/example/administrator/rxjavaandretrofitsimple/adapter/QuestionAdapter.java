package com.example.administrator.rxjavaandretrofitsimple.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.rxjavaandretrofitsimple.R;
import com.example.administrator.rxjavaandretrofitsimple.bean.DrivingQuestionEntity;

import java.util.List;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 16:43
 * 版本：
 */
public class QuestionAdapter extends BaseAdapter {
    private List<DrivingQuestionEntity.Question> list;
    private Context mContext;

    public QuestionAdapter(Context mContext){
        this.mContext = mContext;
    }

    public void setData(List<DrivingQuestionEntity.Question> list){
        this.list = list;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if(null == list || list.size() == 0){
            return 0;
        }else{
            return list.size();
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_question,null);
            holder.iv_question = (ImageView) convertView.findViewById(R.id.iv_question);
            holder.tv_question = (TextView) convertView.findViewById(R.id.tv_question);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        DrivingQuestionEntity.Question resultBean = list.get(position);
        holder.tv_question.setText(resultBean.getExplains());
        Glide.with(mContext).load(resultBean.getUrl()).placeholder(R.drawable.empty_photo).error(R.drawable.empty_photo).crossFade().into(holder.iv_question);
        return convertView;
    }


    public static class ViewHolder{
        public ImageView iv_question;
        public TextView tv_question;
    }
}
