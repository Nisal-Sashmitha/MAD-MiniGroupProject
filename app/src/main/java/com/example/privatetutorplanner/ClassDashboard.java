package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClassDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_dashboard);
    }
    public void navigateClassAdd(View view){
        Intent intent = new Intent(ClassDashboard.this,ClassAdd.class);
        startActivity(intent);
    }
}