package com.example.myWeight;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "weight";
    public static final String _ID = "_id";
    public static final String TANGGAL1 = "tanggalinput";
    public static final String TANGGAL2 = "tanggalubah";
    public static final String TINGGI = "tinggi";
    public static final String BERAT = "berat";
    public static final String KET = "ket";

    static final String DB_NAME = "db_weight.DB";

    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TANGGAL1 + " DATE, " + TANGGAL2 + " DATE , " + TINGGI + " INTEGER, " + BERAT + " INTEGER,  " + KET + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
