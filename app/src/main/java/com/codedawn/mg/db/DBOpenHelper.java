package com.codedawn.mg.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBOpenHelper";

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,int version) {
        super(context,"user.db",null,1);//建立数据库名称为user.db

    }

    @Override
    public void onCreate(SQLiteDatabase db) { //第一次实例化时调用
        String sql = "create table user(name varchar(20) PRIMARY KEY ,password varchar(20))";
        Log.i(TAG,"create Database----------->");
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//当数据库更新时使用该方法
        Log.i(TAG,"update database------------->");
    }
}
