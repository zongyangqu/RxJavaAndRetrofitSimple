package com.example.administrator.rxjavaandretrofitsimple.util.xmlparse;



import com.example.administrator.rxjavaandretrofitsimple.bean.SplashAdvEntity;

import java.io.InputStream;
import java.util.List;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/23
 *
 * 类描述：
 */

public interface SplashAdvParser {
    /**
     * 解析输入流 得到Book对象集合
     * @param is
     * @return
     * @throws Exception
     */
    public List<SplashAdvEntity> parse(InputStream is) throws Exception;

    /**
     * 序列化Book对象集合 得到XML形式的字符串
     * @param advs
     * @return
     * @throws Exception
     */
    public String serialize(List<SplashAdvEntity> advs) throws Exception;
}
