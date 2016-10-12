package com.example.administrator.rxjavaandretrofitsimple.util.db;

import android.content.Context;

import com.example.administrator.rxjavaandretrofitsimple.bean.LoginEntity;


/**
 * Created by xiongtao on 2016/8/15.
 * 用户信息dao层
 */
public class UserDao {

    public static final String TABLE_NAME = "USER";
    public static final String AREA = "AREA";
    public static final String LOCATION = "LOCATION";

    private static UserDao mInstance;

    private UserDao() {

    }

    public static UserDao getInstance(Context _context) {
        DBManager.getInstance().onInit(_context);
        if (mInstance == null) {
            synchronized (UserDao.class) {
                if (mInstance == null) {
                    mInstance = new UserDao();
                }
            }
        }
        return mInstance;
    }


    /***
     * 保存用户信息
     *
     * @param _responseBean
     */
    public void saveUserEntity(LoginEntity.UserBean _responseBean) {
        DBManager.getInstance().saveUserEntity(_responseBean);
    }

    /***
     * 获取用户信息
     *
     * @return
     */
    public LoginEntity.UserBean loadUserEntity() {
        return DBManager.getInstance().getUserEntity();
    }

    /***
     * 注销用户信息
     */
    public void removeUserEntity() {
        DBManager.getInstance().removeUserEntity();
    }

    /***
     * 用户是否登录
     *
     * @return
     */
    public boolean hasLogined() {
        boolean login = false;
        LoginEntity.UserBean localLoginEntity = DBManager.getInstance().getUserEntity();
        if (localLoginEntity != null) {
            login = true;
        }
        return login;
    }
}
