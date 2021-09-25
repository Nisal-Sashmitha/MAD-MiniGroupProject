package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.StudentClass;
import com.example.privatetutorplanner.ServiceClasses.StudentCount;
import com.example.privatetutorplanner.UtilityClasses.MainPageAdapter;
import com.example.privatetutorplanner.ModalClasses.Class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainPage extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<Class> classArryList;
    ArrayList<StudentClass> studentArray;
    MainPageAdapter mainPageAdapter;
    Map<Integer,Integer> Countmap;
    Map<Integer,Integer> Feemap2;
    HashMap<Integer,Double> resultTot;

    StudentCount std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        dbHelper = new DBHelper(MainPage.this);
        recyclerView = findViewById(R.id.recycleView_mainpage);
        classArryList = new ArrayList<>();
        studentArray = new ArrayList<>();
        Countmap=new HashMap<Integer,Integer>();
        Feemap2=new HashMap<Integer,Integer>();


        getCurrentClassData();
        getStudentsData();

        std=new StudentCount(classArryList,studentArray);
        Countmap=std.countStudent();

        resultTot=std.totClassFee(Countmap,classArryList);

        mainPageAdapter = new MainPageAdapter(MainPage.this,classArryList,Countmap,resultTot);
        recyclerView.setAdapter(mainPageAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainPage.this));
    }

    //just for example-to make sure working main page
    public void getCurrentClassData(){
        Cursor cursor = dbHelper.getCurrentClassDetails();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                Class c = new Class();

                c.setClassID(cursor.getInt(0));
                c.setClassName(cursor.getString(1));
                c.setClassDay(cursor.getString(2));
                c.setClassTime(cursor.getString(3));
                c.setClassFee(cursor.getDouble(4));

                classArryList.add(c);
            }
        }
    }

    public void getStudentsData(){
        Cursor cursor = dbHelper.getALLStudents();

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else{
            while(cursor.moveToNext()){
                StudentClass c = new StudentClass();

                c.setStudentID(cursor.getInt(0));
                c.setClassID(cursor.getInt(1));
                c.setLastPaymentMonth(" ");
                c.setLastPaymentAmount(0);
                c.setClassName(" ");

                studentArray.add(c);
            }
        }
    }
    //-------------------------------------------------------
}