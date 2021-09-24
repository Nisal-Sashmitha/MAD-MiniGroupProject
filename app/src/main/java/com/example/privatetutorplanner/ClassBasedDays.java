package com.example.privatetutorplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.UtilityClasses.Class_CustomAdapter;
import com.example.privatetutorplanner.ModalClasses.Class;

import java.util.ArrayList;

public class ClassBasedDays extends AppCompatActivity {

    TextView tv_day_header,tv_noData;
    String classDay;
    DBHelper dbHelper;
    RecyclerView recyclerView;
    ArrayList<Class> classes_arry_list;
    Class_CustomAdapter customAdapter;
    ImageView iv_noData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_based_days);

        tv_day_header = findViewById(R.id.classs_tv_header_classDay);
        recyclerView = findViewById(R.id.class_recyclerView);
        tv_noData = findViewById(R.id.class_tv_noData);
        iv_noData = findViewById(R.id.class_iv_noData);

        Intent intent = getIntent();
        classDay = intent.getStringExtra("classDay");
        tv_day_header.setText(classDay);

        dbHelper = new DBHelper(this);
        classes_arry_list = new ArrayList<>();

        fetchClassDataBasedOnDay();

        //view in recycle view
        customAdapter = new Class_CustomAdapter(ClassBasedDays.this,ClassBasedDays.this,classes_arry_list);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ClassBasedDays.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 200){
            recreate();
        }
    }


    public void fetchClassDataBasedOnDay(){
        if(classDay.equals("")){
            Toast.makeText(this, "Invalid selection", Toast.LENGTH_SHORT).show();
        }
        else{
            Cursor cursor = dbHelper.getClassesFromDay(classDay);
            if(cursor.getCount() == 0) {
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
                //show image view when no data
                tv_noData.setVisibility(View.VISIBLE);
                iv_noData.setVisibility(View.VISIBLE);
            }
            else{

                tv_noData.setVisibility(View.GONE);
                iv_noData.setVisibility(View.GONE);

                while(cursor.moveToNext()){
                    Class myclass = new Class();
                    myclass.setClassID(cursor.getInt(0));
                    myclass.setClassName(cursor.getString(1));
                    myclass.setClassDay(cursor.getString(2));
                    myclass.setClassTime(cursor.getString(3));
                    myclass.setClassFee(cursor.getDouble(4));

                    classes_arry_list.add(myclass);
                }
                Toast.makeText(this, "Successfully fetched", Toast.LENGTH_SHORT).show();

            }
        }
    }
}