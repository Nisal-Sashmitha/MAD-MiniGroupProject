package com.example.privatetutorplanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.privatetutorplanner.ModalClasses.Assignment;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="TutorPlannerDB";

   public DBHelper(Context context){
       super(context, DATABASE_NAME,null,1);
   }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ASSIGN="CREATE TABLE " + UserMaster.Assignment.TABLE_NAME +" ("+
                UserMaster.Assignment.COLUMN_NAME_ASSIGNID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                UserMaster.Assignment.COLUMN_NAME_TITLE + " TEXT NOT NULL," +
                UserMaster.Assignment.COLUMN_NAME_MODULENAME + " TEXT NOT NULL," +
                UserMaster.Assignment.COLUMN_NAME_Q + " INTEGER NOT NULL," +
                UserMaster.Assignment.COLUMN_NAME_MARKS + " INTEGER NOT NULL," +
                UserMaster.Assignment.COLUMN_NAME_DATE + " TEXT NOT NULL)";
        db.execSQL(CREATE_ASSIGN);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }




    //----------Asignmeent SQL Queries--------------

    public boolean addAssign(Assignment as1){

        boolean result=false;
        SQLiteDatabase db = getWritableDatabase();

        ContentValues val = new ContentValues();
        val.put(UserMaster.Assignment.COLUMN_NAME_TITLE,as1.getTitle());
        val.put(UserMaster.Assignment.COLUMN_NAME_MODULENAME, as1.getModulename());
        val.put(UserMaster.Assignment.COLUMN_NAME_Q, as1.getQu());
        val.put(UserMaster.Assignment.COLUMN_NAME_MARKS,as1.getMark());
        val.put(UserMaster.Assignment.COLUMN_NAME_DATE,as1.getDate());

        long rowid=db.insert(UserMaster.Assignment.TABLE_NAME,null,val);

        if(rowid>=1){
            result=true;
        }
        else{
            result=false;
        }
        return result;
    }
    //----------End of Assignment Queries-----------
}
