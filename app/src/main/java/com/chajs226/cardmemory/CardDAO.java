package com.chajs226.cardmemory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by amc on 2017-03-15.
 */

public final class CardDAO {

    private DBHelper dbHelper;

    public CardDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int insert(String kind, String contents) {

        int rtn = 0;
        Date date = new Date();

        ContentValues values = new ContentValues();
        values.put("kind", kind);
        values.put("contents", contents);
        values.put("updt", DateFormat.getDateTimeInstance().format(date));

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            long cards_Id = db.insert("CARDS", null, values);
        } catch (Exception e) {
            rtn = -1;
        } finally {
            db.close(); // Closing database connection
        }

        return rtn;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void update(String kind, String contents, int id) {

/*        Date date = new Date();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kind", kind);
        values.put("contents", contents);
        values.put("id", id);
        values.put("updt", DateFormat.getDateTimeInstance().format(date));

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update("CARDS", values, "ID = ?", new String[] { String.valueOf(id) });
        db.close(); // Closing database connection*/
    }

    public void delete(int id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            // It's a good practice to use parameter ?, instead of concatenate string
            db.delete("CARDS", "_ID = ?", new String[] { String.valueOf(id) });
        } catch (Exception e ) {
            ;
        } finally {
            db.close(); // Closing database connection
        }


    }


    public ArrayList<CardVO> getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        ArrayList cardVOList = new ArrayList();

        cardVOList.clear();
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM CARDS", null);
        while (cursor.moveToNext()) {

            CardVO cardVo = new CardVO();

            cardVo.setId(cursor.getInt(0));
            cardVo.setKind(cursor.getString(1));
            cardVo.setContents(cursor.getString(2));
            cardVo.setUpdt(cursor.getString(3));

            cardVOList.add(cardVo);

        }
        return cardVOList;
    }

}
