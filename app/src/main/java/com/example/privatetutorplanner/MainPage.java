package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.UtilityClasses.MainPageAdapter;
import com.example.privatetutorplanner.ModalClasses.Class;

import java.util.ArrayList;

public class MainPage extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<Class> classArryList;
    MainPageAdapter mainPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        dbHelper = new DBHelper(MainPage.this);
        recyclerView = findViewById(R.id.recycleView_mainpage);
        classArryList = new ArrayList<>();

        getCurrentClassData();
        mainPageAdapter = new MainPageAdapter(MainPage.this,classArryList);
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
    //-------------------------------------------------------
}