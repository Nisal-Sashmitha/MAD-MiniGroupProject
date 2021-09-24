package com.example.privatetutorplanner;

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
import com.example.privatetutorplanner.UtilityClasses.Class_CustomAdapterStudent;


import java.util.ArrayList;

public class ClassStudentList extends AppCompatActivity {

    String classID ;
    ArrayList<String> class_students_arryList;
    DBHelper dbHelper;
    TextView tv_noData;
    ImageView iv_noData;
    RecyclerView recyclerView;
    Class_CustomAdapterStudent customAdapterStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_student_list);

        tv_noData = findViewById(R.id.class_student_tv_noData);
        iv_noData = findViewById(R.id.class_student_iv_noData);
        recyclerView = findViewById(R.id.class_student_recyclerView);

        Intent intent = getIntent();
        classID = intent.getStringExtra("SelectedClassID");

        dbHelper = new DBHelper(ClassStudentList.this);

        class_students_arryList = new ArrayList<>();
        fetchClassStudentData();

        try {
            //view in recycle view
            customAdapterStudent = new Class_CustomAdapterStudent(ClassStudentList.this, class_students_arryList);
            recyclerView.setAdapter(customAdapterStudent);
            recyclerView.setLayoutManager(new LinearLayoutManager(ClassStudentList.this));
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void fetchClassStudentData(){
        if(classID.equals("")){
            Toast.makeText(this, "Invalid Selection", Toast.LENGTH_SHORT).show();
        }
        else{
            Cursor cursor = dbHelper.getStudentDataBasedID(classID);
            if(cursor.getCount() == 0){
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
                tv_noData.setVisibility(View.VISIBLE);
                iv_noData.setVisibility(View.VISIBLE);
            }
            else{
                tv_noData.setVisibility(View.GONE);
                iv_noData.setVisibility(View.GONE);

                while(cursor.moveToNext()){
                    class_students_arryList.add(cursor.getString(0));
                }
                Toast.makeText(this, "Succesfully students names fetched", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void navStudentSearch(View view){
        Intent i = new Intent(ClassStudentList.this,StudentStudentSearch.class);
        startActivity(i);
    }
}