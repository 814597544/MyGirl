package com.mygirl.been;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String name="mytablebase";//数据库名称
    private static final int version=1;//数据库版本
    public DatabaseHelper(Context context){
        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的
        //工厂类，设置为null，代表使用系统默认的工厂类
        super(context, name, null, version);
    }
    public DatabaseHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

        db.execSQL("create table if not exists notepad( name varchar(20),time varchar(30),body varchar(300))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
      /*  // 联合主键
        db.execSQL(" ALTER TABLE mytable ADD PRIMARY KEY (tablename,week,num);");
        // 往表中增加一列
*/

       // db.execSQL("alter table person add phone varchar(12) null");
        //drop table if exists person删除表
    }

}

