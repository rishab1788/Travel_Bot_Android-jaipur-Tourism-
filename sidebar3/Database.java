package com.example.rishabh.tiffinrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rishabh on 9/13/2017.
 */
public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="tiffinThing.db";
    public static final String TABLENAME="student_table";

    public static final String col1="ID";
    public static final String col2="DATE";
    public static final String col3="TIME";



    public Database(Context context ) {
        super(context, DATABASE_NAME, null, 1);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLENAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, DATE TEXT , TIME TEXT ); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLENAME);
        onCreate(db);
    }
    public boolean insertData(String date ,String time)
    {


        SQLiteDatabase db=this.getWritableDatabase();

        String query = "SELECT * FROM " + TABLENAME;
        Cursor res = db.rawQuery(query, null);

        if (res != null && res.getCount() != 0) {
            while (res.moveToNext()) {
                if (res.getString(1).equals(date) && res.getString(2).equals(time)) {
                    return false;
                }

            }
        }

             ContentValues contentValues = new ContentValues();
        contentValues.put(col2, date);
            contentValues.put(col3, time);
            long result = db.insert(TABLENAME, null, contentValues);
            if (result == 1) {
                return false;
            } else {
                return true;
            }

     }


    public Cursor getAllData ()
    {

        SQLiteDatabase db=this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLENAME;
        Cursor res = db.rawQuery(query, null);
        return res;
    }



}
