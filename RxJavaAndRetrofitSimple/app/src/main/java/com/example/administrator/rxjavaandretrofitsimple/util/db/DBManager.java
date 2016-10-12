package com.example.administrator.rxjavaandretrofitsimple.util.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.administrator.rxjavaandretrofitsimple.bean.LoginEntity;
import com.example.administrator.rxjavaandretrofitsimple.util.AbLogUtil;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/10/12. 11:53
 * 版本：
 */
public class DBManager {
    static private DBManager dbMgr = new DBManager();
    private DbOpenHelper dbHelper;

    public void onInit(Context context) {
        dbHelper = DbOpenHelper.getInstance(context);
    }

    public static synchronized DBManager getInstance() {
        return dbMgr;
    }

    /***start**********个人登录信息******************/
    /**
     * 保存个人登录信息
     *
     * @param userEntity //
     *                   //
     */
    public void saveUserEntity(LoginEntity.UserBean userEntity) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            /**先查找本地数据库是否以及存在用户，如果已经存在，先删除本地数据，再插入到数据库中*/
            removeUserEntity();
            ContentValues values = new ContentValues();
            values.put(UserDao.AREA, userEntity.area);
            values.put(UserDao.LOCATION, userEntity.location);

            long insert = db.insert(UserDao.TABLE_NAME, null, values);
            AbLogUtil.i("dataBase", insert + "");
        }
    }

    /**
     * 删除个人信息
     */
    public void removeUserEntity() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        if (db.isOpen()) {
            db.delete(UserDao.TABLE_NAME, null, null);
        }
    }




    /**
     * 获取个人信息
     *
     * @return
     */
    public LoginEntity.UserBean getUserEntity() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        LoginEntity.UserBean userEntity = null;
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from " + UserDao.TABLE_NAME, null);
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    userEntity = new LoginEntity.UserBean();
                    userEntity.area = cursor.getString(cursor.getColumnIndex(UserDao.AREA));
                    userEntity.location = cursor.getString(cursor.getColumnIndex(UserDao.LOCATION));

                }
                cursor.close(); //关闭游标，防止内存泄漏
            }
        }
        return userEntity;
    }



    synchronized public void closeDB() {
        if (dbHelper != null) {
            dbHelper.closeDB();
        }
    }
}