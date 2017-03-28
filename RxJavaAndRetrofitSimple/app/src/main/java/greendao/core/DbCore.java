package greendao.core;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import greendao.DaoMaster;
import greendao.DaoSession;

/**
 * 作者：quzongyang
 *
 * 创建时间：2017/3/27
 *
 * 类描述：核心辅助类，用于获取DaoMaster和DaoSession
 */

public class DbCore {
    private static final String DEFAULT_DB_NAME = "simple.db";
    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    private static Context mContext;
    private static String DB_NAME;

    public static void init(Context context) {
        init(context, DEFAULT_DB_NAME);
    }

    public static void init(Context context, String dbName) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        mContext = context.getApplicationContext();
        DB_NAME = dbName;
    }

    //获取数据库对象
    public static DaoMaster getDaoMaster() {
        if (daoMaster == null) {
            //获取可写数据库
            //创建数据库simple.db"
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
            //获取可写数据库
            SQLiteDatabase db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
        }
        return daoMaster;
    }

    //获取Dao对象管理者
    public static DaoSession getDaoSession() {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster();
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

}

