package com.chajs226.cardmemory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amc on 2017-03-15.
 */

public final class CardDAO {

    private DBHelper dbHelper;

    public CardDAO(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(String kind, String contents) {
        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("kind", kind);
        values.put("contents", contents);

        // Inserting Row
        long cards_Id = db.insert("CARDS", null, values);
        db.close(); // Closing database connection
        return (int) cards_Id;
    }

    public void update(String kind, String contents, int id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("kind", kind);
        values.put("contents", contents);
        values.put("id", id);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update("CARDS", values, "ID = ?", new String[] { String.valueOf(id) });
        db.close(); // Closing database connection
    }

    public void delete(int id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete("CARDS", "ID = ?", new String[] { String.valueOf(id) });
        db.close(); // Closing database connection
    }


    public String getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String result = "";

        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM CARDS", null);
        while (cursor.moveToNext()) {
            result += cursor.getString(0)
                    + " : "
                    + cursor.getString(1)
                    + " | "
                    + cursor.getString(2)
                    + "\n";
        }
        return result;
    }

/*    public ArrayList<HashMap<String, String>>  getStudentList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Student.KEY_ID + "," +
                Student.KEY_name + "," +
                Student.KEY_email + "," +
                Student.KEY_age +
                " FROM " + Student.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(Student.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(Student.KEY_name)));
                studentList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;

    }*/
}
