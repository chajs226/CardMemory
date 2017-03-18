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

/*    public void insert(String kind, String contents) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO CONTENTSLIST VALUES(null, '" + kind + "', " + contents + ", null);");
        db.close();
    }

    public void update(String kind, String contents, int id) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE CONTENTSLIST SET kind= '" + kind + "' , contents = '" + contents + "' WHERE _id = " + id + ";");
        db.close();
    }

    public void delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM CONTENTSLIST WHERE _id=" + id + ";");
        db.close();
    }

    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM CONTENTSLIST", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getInt(2)
                    + " | "
                    + cursor.getString(3)
                    + "\n";
        }

        return result;
    }*/
}
