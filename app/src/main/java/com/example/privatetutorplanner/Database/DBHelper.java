package com.example.privatetutorplanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.privatetutorplanner.ModalClasses.Student;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TutorPlannerDB";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create tables
        String SQL_CREATE_ENTRIES="CREATE TABLE "+UserMaster.Student.TABLE_NAME+"("+
                UserMaster.Student.COLUMN_NAME_STUDENTID+" INTEGER PRIMARY KEY,"+
                UserMaster.Student.COLUMN_NAME_NAME+" TEXT,"+
                UserMaster.Student.COLUMN_NAME_ADDRESS+" TEXT,"+
                UserMaster.Student.COLUMN_NAME_CONTACTNO+" TEXT,"+
                UserMaster.Student.COLUMN_NAME_DATE_OF_BIRTH+" TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public boolean addStudent(Student s){
        SQLiteDatabase db= getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(UserMaster.Student.COLUMN_NAME_NAME,s.getName());
        values.put(UserMaster.Student.COLUMN_NAME_ADDRESS,s.getAddress());
        values.put(UserMaster.Student.COLUMN_NAME_CONTACTNO,s.getContactNo());
        values.put(UserMaster.Student.COLUMN_NAME_DATE_OF_BIRTH,s.getDateOfBirth());
        long newrowid = db.insert(UserMaster.Student.TABLE_NAME,null,values);

        if(newrowid>=1){
            return true;
        }else{
            return false;
        }

    }

    public ArrayList getStudents(){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+UserMaster.Student.TABLE_NAME, null );
        ArrayList<Student> std = new ArrayList<>();

        try {
            while (res.moveToNext()) {
                Student student = new Student();
                student.setStudentID(res.getInt(0));
                student.setName(res.getString(1));
                student.setAddress(res.getString(2));
                student.setContactNo(res.getString(3));
                student.setDateOfBirth(res.getString(4));
                std.add(student);
            }
        } finally {
            res.close();
        }

        return std;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
