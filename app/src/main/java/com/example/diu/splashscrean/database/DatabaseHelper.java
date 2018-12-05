package com.example.diu.splashscrean.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "registar_user";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "email";
    public static final String COL_3 = "password";
    // public static final String COL_4 = "register.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registar_user (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String user, String password){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put("Username", user);
        CV.put("Password", password);
        long res = database.insert("registar_user", null,CV);
        database.close();
        return res;
    }

    public boolean checkUser(String username, String password){
        String[] colums = { COL_1 };
        SQLiteDatabase database = this.getReadableDatabase();
        String selection = COL_2 + " =?" + " and " + COL_3 + "  =?";
        String[] selectionArgs = { username, password };
        Cursor cursor = database.query(TABLE_NAME,colums,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        database.close();

        if (count>0){
            return true;
        }else {
            return false;
        }
    }
}