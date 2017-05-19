package com.example.administrator.rxjavaandretrofitsimple.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/5/2
 *
 * 类描述：
 */

public class MyBaseAdapter<T> extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private int layoutId;
    private int variableId;
    private List<T> list;

    public MyBaseAdapter(Context context, int layoutId, List<T> list, int resId) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
        this.variableId = resId;
        inflater = LayoutInflater.from(context);
    }

    @Override

    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewDataBinding dataBinding;
        if (convertView == null) {
            dataBinding = DataBindingUtil.inflate(inflater, layoutId, parent, false);
        } else {
            dataBinding = DataBindingUtil.getBinding(convertView);
        }
        dataBinding.setVariable(variableId, list.get(position));
        return dataBinding.getRoot();
    }
}