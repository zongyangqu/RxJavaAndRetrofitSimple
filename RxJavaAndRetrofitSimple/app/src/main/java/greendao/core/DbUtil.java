package greendao.core;

import greendao.UserDao;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/27
 *
 * 类描述：获取表的工具类
 */

public class DbUtil {

    public static UserDao getUserDao() {
        return DbCore.getDaoSession().getUserDao();
    }

}
