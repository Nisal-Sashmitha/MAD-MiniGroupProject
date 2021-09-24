package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ClassDashboard extends AppCompatActivity {

    Button btn_monday,btn_tuesday,btn_wednesday,btn_thursday,btn_friday,btn_saturday,btn_sunday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_dashboard);

        btn_monday = findViewById(R.id.class_btn_Monday);
        btn_tuesday = findViewById(R.id.class_btn_tuesday);
        btn_wednesday = findViewById(R.id.class_btn_Wednesday);
        btn_thursday = findViewById(R.id.class_btn_thursday);
        btn_friday = findViewById(R.id.class_btn_friday);
        btn_saturday = findViewById(R.id.class_btn_saturday);
        btn_sunday =findViewById(R.id.class_btn_sunday);
    }
    public void navigateClassAdd(View view){
        Intent intent = new Intent(ClassDashboard.this,ClassAdd.class);
        startActivity(intent);
    }

    public void navigateClassBasedDays(View view){
        String day = "";
        if(view == btn_monday){
            day = "Monday";
        }
        if(view == btn_tuesday){
            day = "Tuesday";
        }
        if(view == btn_wednesday){
            day = "Wednesday";
        }
        if(view == btn_thursday){
            day = "Thursday";
        }
        if(view == btn_friday){
            day = "Friday";
        }
        if(view == btn_saturday){
            day = "Saturday";
        }
        if(view == btn_sunday){
            day = "Sunday";
        }


        Intent intent = new Intent(ClassDashboard.this,ClassBasedDays.class);
        intent.putExtra("classDay",day);
        startActivity(intent);

    }

    //example for main navigation
    public void navMain(View view){
        Intent i = new Intent(this,MainPage.class);
        startActivity(i);
    }
}