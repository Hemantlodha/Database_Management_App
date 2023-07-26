package com.hemant.university_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.util.Date;

public class DataHelper extends SQLiteOpenHelper {
    public DataHelper(Context context)
    {
        super(context,"University.db",null,4);
    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        }
        super.onConfigure(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Student(name TEXT,Scholar_no integer Primary Key,course_id TEXT,dob TEXT,Year_en TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
         db.execSQL("drop table if exists Student");
    }
    public boolean insertdata(String name, int scholar_no, String course_id, String dob,String Year_en){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("scholar_no",scholar_no);
        cv.put("course_id",course_id);
        cv.put("dob",dob);
        cv.put("Year_en",Year_en);
        long result = db.insert("Student",null,cv);
        if(result==-1)
            return false;
        return true;
    }
    public boolean deletedata(int scholar_no){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Student where scholar_no=?",new String[]{String.valueOf(scholar_no)});
        if(cursor.getCount()>0) {
            long result = db.delete("Student", "scholar_no=?",new String[]{String.valueOf(scholar_no)});
            if (result == -1)
                return false;
            return true;
        }
        return false;
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from Student", null);
        return cursor;
    }
}
