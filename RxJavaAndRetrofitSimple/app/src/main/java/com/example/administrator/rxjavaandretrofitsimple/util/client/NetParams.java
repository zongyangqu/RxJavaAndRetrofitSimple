package com.example.administrator.rxjavaandretrofitsimple.util.client;

import java.util.HashMap;
import java.util.Map;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 11:41
 * 版本：
 */
public class NetParams {
    private static NetParams params = new NetParams();

    private NetParams() {

    }

    public static NetParams getInstance() {
        return params;
    }

    /**
     * 获取微信精选
     * @param pno
     * @param ps
     * @param key
     * @return
     */
    public Map<String, String> getWeChat(String pno, String ps,String key) {
        Map<String, String> params = new HashMap<>();
        params.put("pno", key);
        params.put("ps", ps);
        params.put("key",key);
        return params;
    }

    public Map<String, String> getJoke(String page, String pagesize,String key) {
        Map<String, String> params = new HashMap<>();
        params.put("page", page);
        params.put("pagesize", pagesize);
        params.put("key",key);
        return params;
    }


    /**
     * 查询IP地址（模拟登陆）
     *
     * @param key
     * @param ip
     * @return
     */
    public Map<String, String> login(String key, String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("key", key);
        params.put("ip", ip);
        return params;
    }

    /**
     * 查询IP地址（模拟登陆）
     *
     * @param key   申请的appKey
     * @param subject   选择考试科目类型，1：科目1；4：科目4
     * @param model 驾照类型，可选择参数为：c1,c2,a1,a2,b1,b2；当subject=4时可省略
     * @return
     */
    public Map<String, String> getQuestion(String key, String subject,String model) {
        Map<String, String> params = new HashMap<>();
        params.put("key", key);
        params.put("subject", subject);
        params.put("model", model);
        return params;
    }
}
