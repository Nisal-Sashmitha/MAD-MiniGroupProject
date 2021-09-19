package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Student;
import com.example.privatetutorplanner.UtilityClasses.StudentRecyclerViewAdapter;

import java.util.ArrayList;

public class StudentStudentSearch extends AppCompatActivity {
    private ArrayList<Student> students = new ArrayList<>();
    DBHelper dbHelper;
    Button addStudetnavbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_student_search);
        dbHelper = new DBHelper(this);
        InitStudents();
    }

    public void InitStudents(){
        /*Student s1= new Student();
        s1.setName("Nisal");
        Student s2= new Student();
        s2.setName("Kasun");
        Student s3= new Student();
        s3.setName("Dasun");
        Student s4= new Student();
        s4.setName("Pasan");
        Student s5= new Student();
        s5.setName("Nisal");
        Student s6= new Student();
        s6.setName("Kasun");
        Student s7= new Student();
        s7.setName("Dasun");
        Student s8= new Student();
        s8.setName("Pasan");

        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);
        students.add(s7);
        students.add(s8);*/

        students = dbHelper.getStudents();
        initRecyclerView();

    }
    public void initRecyclerView(){
        RecyclerView recyclerView= findViewById(R.id.student_search_results_recycler_view);

        StudentRecyclerViewAdapter adapter = new StudentRecyclerViewAdapter(students,this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void navToAddNewStudent(View v){
        addStudetnavbtn = findViewById(R.id.student_addNewbtn_In_search);


        Intent i= new Intent(this,StudentAddnewstudentActivity.class);
        //i.putExtra("ABC","nisal");
        startActivity(i);
    }
}