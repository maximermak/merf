package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "myDB";

    public static final int DATABASE_VERSION = 1;

    ////////////////////////////////

    public static final String TABLE_DISCRIMINANT = "discTable";

    public static final String KEY_ID = "_id";

    public static final String KEY_A = "a";

    public static final String KEY_B = "b";

    public static final String KEY_C = "c";
    ////////////////////////////////

    public static final String TABLE_CONTACTS = "contactsTable";

    public static final String LP_ID = "_id";

    public static final String KEY_LOGIN = "login";

    public static final String KEY_TIME = "time";

    public static final String KEY_PASSWORD = "password";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + TABLE_DISCRIMINANT + "(" + KEY_ID + " integer primary key,"
                + KEY_A + " text," + KEY_B + " text," + KEY_C + " text," + KEY_TIME + " text" + ")");

        sqLiteDatabase.execSQL("create table " + TABLE_CONTACTS + "(" + LP_ID + " intger primary key,"
        + KEY_LOGIN + " text," + KEY_PASSWORD + " text" + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    sqLiteDatabase.execSQL("drop table if exists " + TABLE_DISCRIMINANT);
    sqLiteDatabase.execSQL("drop table if exists " + TABLE_CONTACTS);
    onCreate(sqLiteDatabase);

    }
}
