package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void assignNav(View v){
        Intent intent = new Intent(this, assignment_ret.class);
        startActivity(intent);

    }
    public void classNav(View view){
        Intent intent = new Intent(this,ClassDashboard.class);
        startActivity(intent);
    }
}