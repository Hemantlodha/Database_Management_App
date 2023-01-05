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
        db.execSQL("create table Student(name TEXT,Scholar_no integer Primary Key,course_id TEXT not null,dob TEXT,Year_en TEXT,foreign key(course_id) references Course(course_id))");
        db.execSQL("create table Course(c_name TEXT ,course_id TEXT Primary Key,no_of_Student integer,dura integer)");
        db.execSQL("create table Dept(d_name TEXT ,dept_id TEXT Primary Key,no_of_Student integer,HOD TEXT)");
        db.execSQL("create table Professor(p_name TEXT ,emp_id TEXT Primary Key,salary integer,dept_id TEXT,dob TEXT,foreign key(dept_id) references Dept(dept_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
         db.execSQL("drop table if exists Student");
         db.execSQL("drop table if exists Course");
        db.execSQL("drop table if exists Dept");
        db.execSQL("drop table if exists Professor");
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
    public boolean insertprof(String p_name, int salary, String emp_id, String dob,String dept_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("p_name",p_name);
        cv.put("salary",salary);
        cv.put("emp_id",emp_id);
        cv.put("dob",dob);
        cv.put("dept_id",dept_id);
        long result = db.insert("Professor",null,cv);
        if(result==-1)
            return false;
        return true;
    }
    public void insertcourse(String c_name,String course_id, int no_of_Student,int dura){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("c_name",c_name);
        cv.put("course_id",course_id);
        cv.put("no_of_Student",no_of_Student);
        cv.put("dura",dura);
        long result = db.insert("Course",null,cv);
    }
    public void insertdept(String d_name,String dept_id,int no_of_Student,String HOD){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("d_name",d_name);
        cv.put("dept_id",dept_id);
        cv.put("no_of_Student",no_of_Student);
        cv.put("HOD",HOD);
        long result = db.insert("Dept",null,cv);
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
    public boolean deletedataProf(String Emp_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from Student where scholar_no=?",new String[]{Emp_id});
        if(cursor.getCount()>0) {
            long result = db.delete("Student", "scholar_no=?",new String[]{Emp_id});
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
    public Cursor getcourse(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from Course", null);
        return cursor;
    }
    public Cursor getdept(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from Dept", null);
        return cursor;
    }
    public Cursor getprof(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor= db.rawQuery("select * from Professor", null);
        return cursor;
    }
}
