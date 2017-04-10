package com.chajs226.cardmemory;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amc on 2017-03-15.
 */

public final class DBHelper extends SQLiteOpenHelper {

    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "cardmemory.db";

    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE = "CREATE TABLE CARDS ( _id INTEGER PRIMARY KEY AUTOINCREMENT, kind TEXT NOT NULL, contents TEXT, updt DATETIME DEFAULT CURRENT_TIMESTAMP );";

        db.execSQL(CREATE_TABLE);

    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS CARDS");

        // Create tables again
        onCreate(db);

    }

}
