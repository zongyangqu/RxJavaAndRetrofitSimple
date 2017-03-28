package com.example.administrator.rxjavaandretrofitsimple.util.rx.event;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/27
 *
 * 类描述：注册成功事件
 */

public class RegisterEvent {

    public String userPhone;
    public String password;

    public RegisterEvent(String userPhone,String password){
        this.userPhone = userPhone;
        this.password = password;
    }
}
