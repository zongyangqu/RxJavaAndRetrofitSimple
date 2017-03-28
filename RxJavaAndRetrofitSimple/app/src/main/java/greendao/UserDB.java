package greendao;

import android.content.ContentResolver;
import android.content.ContentValues;

import com.example.administrator.rxjavaandretrofitsimple.application.BaseApplication;

import java.util.List;

import greendao.core.DbCore;
import greendao.core.DbUtil;
import greendao.db.User;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/27
 *
 * 类描述：
 */

public class UserDB {

    /**添加*/
    public static boolean insertUser(User user) {
        DbUtil.getUserDao().insert(user);
        return true;
    }

    /**删除数据*/
    public static void deleteByID(String phone) {
        DbUtil.getUserDao().deleteByKey(phone);
    }

    /**主键查询数据*/
    public static User queryUser(String phone) {
        return DbUtil.getUserDao().load(phone);
    }

    /**
     * 判断用户是否登录
     * @return
     */
    public static boolean hasLogin(){
        boolean isLogin = queryAll().isEmpty()?false:true;
        return isLogin;
    }

    /** 删除全部数据*/
    public static void deleteAll() {
        DbUtil.getUserDao().deleteAll();
    }

    /**更新数据*/
    public static void updateUser(User shop) {
        DbUtil.getUserDao().update(shop);
    }

    /** 查询全部数据*/
    public static List<User> queryAll() {
        return DbUtil.getUserDao().loadAll();
    }

    /** 查询登录用户*/
    public static User queryLoginUser() {
        List<User> userList =  DbUtil.getUserDao().loadAll();
        User user = userList.isEmpty()?null:userList.get(0);
        return user;
    }

    /**查询条件为用户名和密码*/
    public static List<User> queryUser(String userPone,String password) {
        return DbUtil.getUserDao().queryBuilder()
                .where(UserDao.Properties.Phone.eq(userPone),UserDao.Properties.Psw.eq(password)).list();
    }


}
