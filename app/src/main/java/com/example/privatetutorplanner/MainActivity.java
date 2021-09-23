package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button addStudetnavbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Intent i = new Intent();

    }

    public void assignNav(View v){
        Intent intent = new Intent(this, assignment_ret.class);
        startActivity(intent);

    }
    public void classNav(View view){
        Intent intent = new Intent(this,ClassDashboard.class);
        startActivity(intent);
    }
    public void navToAddNewStudent(View v){
        Intent i= new Intent(this,StudentStudentSearch.class);
        startActivity(i);
    }

}