package com.example.administrator.rxjavaandretrofitsimple.api.exception;

import android.util.MalformedJsonException;

import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;
import com.example.administrator.rxjavaandretrofitsimple.bean.base.BaseResponse;
import com.example.administrator.rxjavaandretrofitsimple.util.AbAppUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/1/22
 *
 * 类描述：根据接口返回code检查是否错误
 */

public class ResponseExceptionJobber {

    /**
   {
   *     "reason": "请求成功",
            "result": {
        "list": [
        {
            "id": "wechat_20170116034547",
                "title": "整容脸也可以是演技派，瞅瞅李小璐就知道了！",
                "source": "电影天堂",
                "firstImg": "http://zxpic.gtimg.com/infonew/0/wechat_pics_-12136083.jpg/640",
                "mark": "",
                "url": "http://v.juhe.cn/weixin/redirect?wid=wechat_20170116034547"
        }
        ],
        "totalPage": 7478,
                "ps": 1,
                "pno": 1
    },
        "error_code": 0
    }*/

    /**
     *
     * @param response
     */
    public static void check(Object response) throws ResponseStatusFailException {
        if (response instanceof BaseResponse) {
            if(!"0".equals(((BaseResponse) response).error_code)){
                throw new ResponseStatusFailException();
            }
        }
    }

    public static String analyze(Throwable e) {
        if (!AbAppUtil.isNetworkAvailable(BaseApplication.getInstance())) {
            return "当前网络不可用,请检查网络设置";
        } else if (e instanceof UnknownHostException) {
            return "网络错误,请重试";
        } else if (e instanceof ConnectException) {
            return "请求失败,请重试";
        } else if (e instanceof MalformedJsonException) {
            return "请求失败,请重试";
        } else if (e instanceof SocketTimeoutException) {
            return "请求超时,请重试";
        } else {
            return "请求失败,请重试";
        }
    }

}

