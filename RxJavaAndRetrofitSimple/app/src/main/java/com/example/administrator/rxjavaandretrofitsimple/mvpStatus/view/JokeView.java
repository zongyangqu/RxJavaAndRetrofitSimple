package com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view;

import com.example.administrator.rxjavaandretrofitsimple.bean.JokeResponse;
import com.example.administrator.rxjavaandretrofitsimple.mvpStatus.view.base.BaseStatusView;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/20
 *
 * 类描述：
 */

public interface JokeView extends BaseStatusView{

    void provideJokeInfo(JokeResponse.ResultBean response);
}
