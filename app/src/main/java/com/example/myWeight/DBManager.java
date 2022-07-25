package com.example.myWeight;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    // Query insert data
    public void insert(String tanggalinput,String tanggalubah, String tinggi, String berat, String ket) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.TANGGAL1, tanggalinput);
        contentValue.put(DatabaseHelper.TANGGAL2, tanggalubah);
        contentValue.put(DatabaseHelper.TINGGI, tinggi);
        contentValue.put(DatabaseHelper.BERAT, berat);
        contentValue.put(DatabaseHelper.KET, ket);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    // Query ambil/read data
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TANGGAL1, DatabaseHelper.TANGGAL2, DatabaseHelper.TINGGI, DatabaseHelper.BERAT, DatabaseHelper.KET };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }
    public Cursor ambiltgl() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TANGGAL1};
        Cursor cursor = this.database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }


    // Query update data
    public int update(long _id, String tanggalinput,String tanggalubah, String tinggi, String berat, String ket) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.TANGGAL1, tanggalinput);
        contentValues.put(DatabaseHelper.TANGGAL2, tanggalubah);
        contentValues.put(DatabaseHelper.TINGGI, tinggi);
        contentValues.put(DatabaseHelper.BERAT, berat);
        contentValues.put(DatabaseHelper.KET, ket);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    // Query delete data
    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }
}