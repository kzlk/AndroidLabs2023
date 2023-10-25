package com.example.lab5_sql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "OperatingSystem.db";
    private static final String TABLE_NAME = "System_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "SYSTEM";
    private static final String COL_3 = "COMPANY";
    private static final String COL_4 = "VERSION";
    private static final String COL_5 = "ARCHITECTURE";
    private static final String COL_6 = "DESTINY";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,SYSTEM TEXT,COMPANY TEXT,VERSION TEXT,ARCHITECTURE TEXT,DESTINY TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String subject_name, String scope, String teacher, String schedule, String success_rate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, subject_name);
        contentValues.put(COL_3, scope);
        contentValues.put(COL_4, teacher);
        contentValues.put(COL_5, schedule);
        contentValues.put(COL_6, success_rate);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + TABLE_NAME, null);
    }

    @SuppressLint("Range")
    public List<OperatingSystem> fetchDataFromDatabase() {
        List<OperatingSystem> data = new ArrayList<>();
        Cursor cursor = this.getAllData();

        if (cursor != null) {
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COL_1));
                @SuppressLint("Range") String subjectName = cursor.getString(cursor.getColumnIndex(COL_2));
                @SuppressLint("Range") String scope = cursor.getString(cursor.getColumnIndex(COL_3));
                @SuppressLint("Range") String teacher = cursor.getString(cursor.getColumnIndex(COL_4));
                @SuppressLint("Range") String schedule = cursor.getString(cursor.getColumnIndex(COL_5));
                @SuppressLint("Range") String successRate = cursor.getString(cursor.getColumnIndex(COL_6));

                // Create a Subject object and add it to the list
                OperatingSystem operatingSystem = new OperatingSystem(id, subjectName, scope, teacher, schedule, successRate);
                data.add(operatingSystem);
            }
            cursor.close();
        }

        return data;
    }

    public void deleteSubject(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL_1 + " = ? ", new String[] { Integer.toString(id) });
    }

    public boolean deleteSubjectById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, COL_1 + "=" + id, null) > 0;
    }

}
