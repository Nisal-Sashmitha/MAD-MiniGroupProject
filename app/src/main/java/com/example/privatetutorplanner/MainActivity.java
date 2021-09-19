package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    Button addStudetnavbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Intent i = new Intent();

    }
    public void navToAddNewStudent(View v){
        addStudetnavbtn = findViewById(R.id.studentNav);


        Intent i= new Intent(this,StudentStudentSearch.class);
        //i.putExtra("ABC","nisal");
        startActivity(i);
    }

}