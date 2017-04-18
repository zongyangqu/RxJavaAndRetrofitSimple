package com.example.administrator.rxjavaandretrofitsimple.ui.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/4/18
 *
 * 类描述：RecycleView间隔 实现瀑布流
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpacesItemDecoration(int space) {
        this.space=space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left=space;
        outRect.right=space;
        outRect.bottom=space+20;
        if(parent.getChildAdapterPosition(view)==0){
            outRect.top=space;
        }
    }
}
