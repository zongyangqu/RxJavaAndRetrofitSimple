package com.example.administrator.rxjavaandretrofitsimple.ui.base.framework;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/22
 *
 * 类描述：
 */

public abstract class BaseFragment extends Fragment implements ViewFinder {

    protected LayoutInflater inflater;
    protected View contentViewShadow;
    protected Context context;

    private ViewGroup container;

    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity().getApplicationContext();
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        onCreateView(savedInstanceState);
        return contentViewShadow != null ? contentViewShadow : super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void onCreateView(Bundle savedInstanceState);

    protected void setContentView(@LayoutRes int layoutResId) {
        setContentView(inflater.inflate(layoutResId, container, false));
    }

    protected void setContentView(View view) {
        contentViewShadow = view;
    }

    public View getContentView() {
        return contentViewShadow;
    }


    @Override
    public final View viewBy(@IdRes int id) {
        if (contentViewShadow != null) {
            return contentViewShadow.findViewById(id);
        }
        return null;
    }

    @Override
    public final View viewWith(Object tag) {
        if (contentViewShadow != null) {
            return contentViewShadow.findViewWithTag(tag);
        }
        return null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        container = null;
        contentViewShadow = null;
        inflater = null;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            final Field mChildFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            mChildFragmentManager.setAccessible(true);
            mChildFragmentManager.set(this, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

