package com.example.administrator.rxjavaandretrofitsimple.bean;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 11:19
 * 版本：
 */
public class LoginEntity {

    /**
     * resultcode : 200
     * reason : Return Successd!
     * result : {"area":"江苏省苏州市","location":"电信"}
     */

    private int resultcode;
    private String reason;
    /**
     * area : 江苏省苏州市
     * location : 电信
     */

    private UserBean result;

    public int getResultcode() {
        return resultcode;
    }

    public void setResultcode(int resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UserBean getResult() {
        return result;
    }

    public void setResult(UserBean result) {
        this.result = result;
    }

    public static class UserBean {
        public String area;
        public String location;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
