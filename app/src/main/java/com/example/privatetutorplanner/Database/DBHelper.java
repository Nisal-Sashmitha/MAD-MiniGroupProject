package com.example.privatetutorplanner.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.privatetutorplanner.ModalClasses.Assignment;
import com.example.privatetutorplanner.ModalClasses.Class;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TutorPlannerDB";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ASSIGN = "CREATE TABLE " + UserMaster.Assignment.TABLE_NAME + " (" +
                UserMaster.Assignment.COLUMN_NAME_ASSIGNID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserMaster.Assignment.COLUMN_NAME_TITLE + " TEXT NOT NULL," +
                UserMaster.Assignment.COLUMN_NAME_MODULENAME + " TEXT NOT NULL," +
                UserMaster.Assignment.COLUMN_NAME_Q + " INTEGER NOT NULL," +
                UserMaster.Assignment.COLUMN_NAME_MARKS + " INTEGER NOT NULL," +
                UserMaster.Assignment.COLUMN_NAME_DATE + " TEXT NOT NULL)";
        db.execSQL(CREATE_ASSIGN);

        //create Class table
        String SQL_CREATE_CLASS_TABLE = "CREATE TABLE " + UserMaster.Class.TABLE_NAME + " (" +
                UserMaster.Class.COLUMN_NAME_CLASSID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserMaster.Class.COLUMN_NAME_NAME + " TEXT," +
                UserMaster.Class.COLUMN_NAME_DAY + " TEXT," +
                UserMaster.Class.COLUMN_NAME_TIME + " TEXT," +
                UserMaster.Class.COLUMN_NAME_MONTHLY_FEE + " REAL)";

        db.execSQL(SQL_CREATE_CLASS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }


    //----------Asignmeent SQL Queries--------------

    public boolean addAssign(Assignment as1) {

        boolean result = false;
        SQLiteDatabase db = getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put(UserMaster.Assignment.COLUMN_NAME_TITLE, as1.getTitle());
        val.put(UserMaster.Assignment.COLUMN_NAME_MODULENAME, as1.getModulename());
        val.put(UserMaster.Assignment.COLUMN_NAME_Q, as1.getQu());
        val.put(UserMaster.Assignment.COLUMN_NAME_MARKS, as1.getMark());
        val.put(UserMaster.Assignment.COLUMN_NAME_DATE, as1.getDate());

        long rowid = db.insert(UserMaster.Assignment.TABLE_NAME, null, val);

        if (rowid >= 1) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }
    //----------End of Assignment Queries-----------

    //------Class queries

    public boolean addNewClass(Class c){
        SQLiteDatabase db = getWritableDatabase();

        //create map of values
        ContentValues values = new ContentValues();
        values.put(UserMaster.Class.COLUMN_NAME_NAME,c.getClassName());
        values.put(UserMaster.Class.COLUMN_NAME_DAY,c.getClassDay());
        values.put(UserMaster.Class.COLUMN_NAME_TIME,c.getClassTime());
        values.put(UserMaster.Class.COLUMN_NAME_MONTHLY_FEE,c.getClassFee());

        long newRowID = db.insert(UserMaster.Class.TABLE_NAME,null,values);

        //insert data
        if(newRowID == -1)
            return false;
        else
            return true;

    }


    //------------end of class queries
}


