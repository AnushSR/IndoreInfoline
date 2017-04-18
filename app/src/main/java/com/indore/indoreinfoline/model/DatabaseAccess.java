package com.indore.indoreinfoline.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
//    public List<String> getBus() {
//        List<String> list = new ArrayList<>();
//        Cursor cursor = database.rawQuery("SELECT * FROM businfo", null);
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            Log.d("Database",cursor.getString(1));
//            list.add(cursor.getString(1));
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return list;
//    }
    public List<DataBean> getBus(String source, String dest) {
        List<DataBean> list = new ArrayList<>();
//        Cursor cursor = database.rawQuery("SELECT * FROM businfo", null);
        Cursor cursor = database.rawQuery("SELECT * FROM businfo where source='" + source + "' and destination='" + dest + "'", null);
        while (cursor.moveToNext()) {
            int routeno = cursor.getInt(2);
            String sc = cursor.getString(0);
            String destination = cursor.getString(1);
            String time = cursor.getString(3);
            DataBean bean = new DataBean(sc, destination, routeno, time);
            list.add(bean);
        }
        cursor.close();
        return list;
    }
}

