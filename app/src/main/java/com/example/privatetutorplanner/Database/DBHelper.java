package com.example.privatetutorplanner.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.privatetutorplanner.ModalClasses.Student;

import java.util.ArrayList;

import com.example.privatetutorplanner.ModalClasses.Assignment;
import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.ModalClasses.StudentClass;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TutorPlannerDB";
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create tables
        String SQL_CREATE_STUDENT_TABLE="CREATE TABLE "+UserMaster.Student.TABLE_NAME+"("+
                UserMaster.Student.COLUMN_NAME_STUDENTID+" INTEGER PRIMARY KEY,"+
                UserMaster.Student.COLUMN_NAME_NAME+" TEXT,"+
                UserMaster.Student.COLUMN_NAME_ADDRESS+" TEXT,"+
                UserMaster.Student.COLUMN_NAME_CONTACTNO+" TEXT,"+
                UserMaster.Student.COLUMN_NAME_DATE_OF_BIRTH+" TEXT)";
        db.execSQL(SQL_CREATE_STUDENT_TABLE);

        String SQL_CREATE_STUDENTCLASS_TABLE="CREATE TABLE "+UserMaster.StudentClass.TABLE_NAME+"("+
                UserMaster.StudentClass.COLUMN_NAME_STUDENTID+" INTEGER,"+
                UserMaster.StudentClass.COLUMN_NAME_CLASSID+" INTEGER,"+
                UserMaster.StudentClass.COLUMN_NAME_LAST_PAYMENT_AMOUNT+" REAL,"+
                UserMaster.StudentClass.COLUMN_NAME_LAST_PAYMENT_MONTH+" TEXT,"+
                "FOREIGN KEY ("+UserMaster.StudentClass.COLUMN_NAME_STUDENTID+
                ") REFERENCES "+UserMaster.Student.TABLE_NAME+" ("+UserMaster.Student.COLUMN_NAME_STUDENTID+") ON DELETE CASCADE ,"+
                "FOREIGN KEY ("+UserMaster.StudentClass.COLUMN_NAME_CLASSID+
                ") REFERENCES "+UserMaster.Class.TABLE_NAME+" ("+UserMaster.Class.COLUMN_NAME_CLASSID+") ON DELETE CASCADE,"+
                "PRIMARY KEY("+UserMaster.StudentClass.COLUMN_NAME_STUDENTID+","+UserMaster.StudentClass.COLUMN_NAME_CLASSID+"))";
        db.execSQL(SQL_CREATE_STUDENTCLASS_TABLE);

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
    public Student getStudentByID(int ID){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+UserMaster.Student.TABLE_NAME+" WHERE "+UserMaster.Student.COLUMN_NAME_STUDENTID+"="+ID, null );

        Student student = new Student();
        try {
            while (res.moveToNext()) {

                student.setStudentID(res.getInt(0));
                student.setName(res.getString(1));
                student.setAddress(res.getString(2));
                student.setContactNo(res.getString(3));
                student.setDateOfBirth(res.getString(4));
                Log.d("value",res.getString(2));

            }
        } finally {
            res.close();
            Log.d("value","outer");
        }

        return student;
    }
    public ArrayList getClassListForStudent(){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+UserMaster.Class.TABLE_NAME, null );
        ArrayList<Class> classes = new ArrayList<>();

        try {
            while (res.moveToNext()) {
                Class cls = new Class();

                cls.setClassID(res.getInt(0));
                cls.setClassName(res.getString(1));

                classes.add(cls);
            }
        } finally {
            res.close();
        }

        return classes;
    }

    public boolean addStudentToAclass(int studentID,int classID,double lastPaidAmount, String month){
        SQLiteDatabase db= getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put(UserMaster.StudentClass.COLUMN_NAME_STUDENTID,studentID);
        values.put(UserMaster.StudentClass.COLUMN_NAME_CLASSID,classID);
        values.put(UserMaster.StudentClass.COLUMN_NAME_LAST_PAYMENT_AMOUNT,lastPaidAmount);
        values.put(UserMaster.StudentClass.COLUMN_NAME_LAST_PAYMENT_MONTH,month);
        long newrowid = db.insert(UserMaster.StudentClass.TABLE_NAME,null,values);

        if(newrowid>=1){
            return true;
        }else{
            return false;
        }

    }
    public ArrayList getClassesOfStudent(int ID){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select SC.ClassID, SC.lastPaymentAmount, SC.lastPaymentMonth, C.name from "+
                UserMaster.Class.TABLE_NAME+" C, "+
                UserMaster.StudentClass.TABLE_NAME+" SC WHERE SC.ClassID = C.classID AND SC.StudentID ="+ID, null );
                ArrayList<StudentClass> stdclasses = new ArrayList<>();

        try {
            while (res.moveToNext()) {
                StudentClass stdcls = new StudentClass();
                stdcls.setStudentID(ID);
                stdcls.setClassID(res.getInt(0));
                stdcls.setLastPaymentAmount(res.getDouble(1));
                stdcls.setLastPaymentMonth(res.getString(2));
                stdcls.setClassName(res.getString(3));

                stdclasses.add(stdcls);
            }
        } finally {
            res.close();
        }

        return stdclasses;
    }


    public boolean UpdateStudent(int ID,String name,String date, String address, String contactNo){
        Log.d("Edit Check","in the Update method");
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("UPDATE "+UserMaster.Student.TABLE_NAME+" SET "+UserMaster.Student.COLUMN_NAME_NAME+
                " = '"+name+"', "+UserMaster.Student.COLUMN_NAME_DATE_OF_BIRTH+" = '"+date+"', "+
                UserMaster.Student.COLUMN_NAME_ADDRESS+" = '"+address+"', "+
                UserMaster.Student.COLUMN_NAME_CONTACTNO+" = '"+contactNo+"' WHERE "+
                UserMaster.Student.COLUMN_NAME_STUDENTID+" = "+"'"+ID+"'");
        return true;
    }
    public boolean UpdateStudentClass(int studentID,int classID,String month, double fee){
        Log.d("Edit Check","in the Update method");
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("UPDATE "+UserMaster.StudentClass.TABLE_NAME+" SET "+UserMaster.StudentClass.COLUMN_NAME_LAST_PAYMENT_MONTH+
                " = '"+month+"', "+UserMaster.StudentClass.COLUMN_NAME_LAST_PAYMENT_AMOUNT+" = '"+fee+"'"+
                " WHERE "+
                UserMaster.StudentClass.COLUMN_NAME_STUDENTID+" = '"+studentID+"' AND "+
                UserMaster.StudentClass.COLUMN_NAME_CLASSID+" = '"+classID+"'");
        return true;
    }
    public int getLastStudentID(){
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select MAX(studentID) from "+UserMaster.Student.TABLE_NAME, null );
        int ID=0;
        try {
            while (res.moveToNext()) {

                ID=res.getInt(0);
             }
        } finally {
            res.close();
        }

        return ID;
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


    //Read only module names
    public Cursor readModules(){
        String modulequery = "SELECT ID,Module_Name FROM " + UserMaster.Assignment.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(modulequery, null);
        }
        return cursor;
    }

    //Read specific module names

    public Assignment getDetModules(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        //String modquery = "SELECT * FROM Assignment WHERE Module_Name = '" +module+ "'";
       // String modquery = "select * from " + UserMaster.Assignment.TABLE_NAME + " where " + UserMaster.Assignment.COLUMN_NAME_MODULENAME + "='" + module + "'";
        //db.rawQuery(modquery, null); db.query(UserMaster.Assignment.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        String selection = UserMaster.Assignment.COLUMN_NAME_ASSIGNID + "=?";
        String[] selectionArgs = {Integer.toString(id)};
        Assignment as2= null;
        try {
            Cursor cursor = db.query(UserMaster.Assignment.TABLE_NAME, null, selection, selectionArgs, null, null, null);

                    if (db != null){
                        cursor.moveToFirst();

                                as2 = new Assignment(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                                        cursor.getString(2),
                                        Integer.parseInt(cursor.getString(3)),
                                        Integer.parseInt(cursor.getString(4)),
                                        cursor.getString(5));

                    }
        }
        catch(Exception E){
            Log.i("Error",E.getMessage());
            E.printStackTrace();

        }
        // return assignment
        return as2;
    }
    //Delete One Row
    public void deleteAssign(int row){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(UserMaster.Assignment.TABLE_NAME, " ID = ?", new String[]{Integer.toString(row)});
        if(result == -1){
            Log.i("Error","Data did not deleted");
           // Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Log.i("Error","Data deleted successfully");
           // Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }
    public void updateAssign(String row_id, String title, String module, String mark,String qu, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(UserMaster.Assignment.COLUMN_NAME_TITLE, title);
        cv.put(UserMaster.Assignment.COLUMN_NAME_MODULENAME, module);
        cv.put(UserMaster.Assignment.COLUMN_NAME_MARKS, Integer.parseInt(mark));
        cv.put(UserMaster.Assignment.COLUMN_NAME_Q, Integer.parseInt(qu));
        cv.put(UserMaster.Assignment.COLUMN_NAME_DATE, date);

        long result = db.update(UserMaster.Assignment.TABLE_NAME, cv, "ID = ?", new String[]{row_id});
        if(result == -1){
            //Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
            Log.i("Msg","Failed in Update");
        }else {
            //Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
            Log.i("Msg","Updated Succesfully");
        }

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

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }



}


