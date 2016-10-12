package com.example.administrator.rxjavaandretrofitsimple.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类描述：全局数据库基类，包括对数据库表的创建，修改，删除，以及数据的crud
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 11:54
 * 版本：
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static DbOpenHelper instance;

    private static final String USER_INFOMATION_TABLE = "CREATE TABLE "
            + UserDao.TABLE_NAME + " ( "
            + UserDao.AREA + " TEXT , "
            + UserDao.LOCATION + " TEXT )";


    private DbOpenHelper(Context context) {
        super(context, getUserDatabaseName(), null, DATABASE_VERSION);
    }

    public static DbOpenHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DbOpenHelper(context.getApplicationContext());
        }
        return instance;
    }

    private static String getUserDatabaseName() {
        return "suppliermanagermentsystem.db";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_INFOMATION_TABLE);  //用户个人信息表创建
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		if(oldVersion < 2){
//		    db.execSQL("ALTER TABLE "+ UserDao.TABLE_NAME +" ADD COLUMN "+
//		            UserDao.COLUMN_NAME_AVATAR + " TEXT ;");
//		}
//
//		if(oldVersion < 3){
//		    db.execSQL(CREATE_PREF_TABLE);
//        }
    }

    public void closeDB() {
        if (instance != null) {
            try {
                SQLiteDatabase db = instance.getWritableDatabase();
                db.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            instance = null;
        }
    }

}
