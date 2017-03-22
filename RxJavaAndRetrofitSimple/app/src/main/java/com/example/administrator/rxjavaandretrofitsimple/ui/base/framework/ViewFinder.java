package com.example.administrator.rxjavaandretrofitsimple.ui.base.framework;

import android.support.annotation.IdRes;
import android.view.View;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/22
 *
 * 类描述：
 */

public interface ViewFinder {
    View viewBy(@IdRes int id);

    View viewWith(Object tag);
}
