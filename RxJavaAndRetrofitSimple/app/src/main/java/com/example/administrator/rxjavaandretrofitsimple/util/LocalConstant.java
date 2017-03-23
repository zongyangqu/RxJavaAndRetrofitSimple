package com.example.administrator.rxjavaandretrofitsimple.util;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/18
 *
 * 类描述：本地常量类
 */

public class LocalConstant {

    public static final String JOKE_REQURST_KAY =  "379618956b7f04290922c73c27a2feb6";
    public static final String WECHAT_REQURST_KAY =  "5c6868ae0010858fab351ac83921d9b3";
    public static final int DEFAULT_PAGE =  10;

    public static final String MAIN_CURRENT_TAB_POSITION="MAIN_CURRENT_TAB_POSITION";
    /**
     * 生产服务器
     */
    //public static String BASE_URL = "http://www.tusousouxr.com/";

    /**
     * 测试服务器
     */
    public static String BASE_URL = "http://114.215.128.252:1919/";
    public static final int ACCESS_COARSE_LOCATION_ID = 2; // 访问位置权限ID
    /***
     * 微信id号
     */
    public static final String APP_ID = "wx678a8d37c4aec635";
    /**
     * 登录标示
     */
    public static String TYPE_LOGIN = "1";
    /**
     * 注销标示
     */
    public static String TYPE_LOGINOUT = "2";

    public static final int CALL_PHONE_ID = 1; // 拨打电话权限ID

    /**
     * 网络请求状态成功
     */
    public final static String RESULT_OK = "ok";
    /***
     * 网络请求状态失败
     */
    public final static String RESULT_FAIL = "fail";





    public static final int MALPHA_TRANSPARENT = 0;
    public static final int ALL_TRANSPARENT_ALPHA = 0;


    /**
     * 单一余额订单支付
     **/
    public static final String ORDER_PAY_BY_ONLY_BALANCE = "3";
    /***
     * 单一支付宝订单支付
     */
    public static final String ORDER_PAY_BY_ONLY_ALIPAY = "2";
    /***
     * 单一微信订单支付
     */
    public static final String ORDER_PAY_BY_ONLY_WEIXIN = "4";
    /***
     * 支付宝混合订单支付
     */
    public static final String ORDER_PAY_BY_FIEXED_ALIPAY = "5";
    /***
     * 微信订单支付
     */
    public static final String ORDER_PAY_BY_FIEXED_WEIXIN = "6";

    /***
     * 刷新余额
     */
    public static final String REFRESH_BALANCE = "refresh_balance";


    public static final String TEXTADDRESS = "text_address";

    /**
     * 地球半径（单位：公里）
     */
    public final static double EARTH_RADIUS_KM = 6378.137;
    /***
     * 请先登录描述
     */
    public static final String LOGIN_DESC = "请先登录";

}
