package com.example.auth;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "SignLog.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "SignLog.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create table covid (name TEXT, address TEXT, dose1 TEXT, dose2 TEXT, dose3 TEXT, pic INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists covid");
    }

    public Boolean insertData(String n, String a, String d1, String d2, String d3, int p){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", n);
        contentValues.put("address", a);
        contentValues.put("dose1", d1);
        contentValues.put("dose2", d2);
        contentValues.put("dose3", d3);
        contentValues.put("pic", p);
        long result = MyDatabase.insert("covid", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<User> fetch(String c) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        List<User> userList = new ArrayList<>();

        // Fetch all users
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM covid", null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                String dose1 = cursor.getString(cursor.getColumnIndexOrThrow("dose1"));
                String dose2 = cursor.getString(cursor.getColumnIndexOrThrow("dose2"));
                String dose3 = cursor.getString(cursor.getColumnIndexOrThrow("dose3"));
                int pic = cursor.getInt(cursor.getColumnIndexOrThrow("pic"));

                int yesCount = 0;
                if (dose1.equals("Yes")) yesCount++;
                if (dose2.equals("Yes")) yesCount++;
                if (dose3.equals("Yes")) yesCount++;

                // Filter users based on the parameter c
                if ((c.equals("1") && yesCount == 1) ||
                        (c.equals("2") && yesCount == 2) || (c.equals("3") && yesCount == 3)) {
                    userList.add(new User(name, address, dose1, dose2, dose3, pic));
                }
            } while (cursor.moveToNext());
            cursor.close();
        }

        return userList;
    }
}