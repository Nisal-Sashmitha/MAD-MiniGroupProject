package com.example.privatetutorplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.UtilityClasses.ModuleAdapter;

import java.util.ArrayList;

public class Modules_List extends AppCompatActivity {

   private ImageView iv_addmodule;
   Context context;

   DBHelper dbHelper;
   RecyclerView moduleRecyclerView;
   ArrayList<String> moduleID , moduleName ;
   ModuleAdapter moduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modules_list);

        moduleRecyclerView = findViewById(R.id.moduleRecyclerView);
        iv_addmodule = findViewById(R.id.iv_addmodule);
        context = this;

        //navigate to add modules activity using intent
        iv_addmodule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context,ModuleAdd.class));
            }
        });

        dbHelper = new DBHelper(Modules_List.this);
        moduleID = new ArrayList<>();
        moduleName = new ArrayList<>();

        storeDataInArrays();
        moduleAdapter = new ModuleAdapter(Modules_List.this,this,moduleID,moduleName);
        moduleRecyclerView.setAdapter( moduleAdapter);
        moduleRecyclerView.setLayoutManager(new LinearLayoutManager(Modules_List.this));

    }
    //overide method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = dbHelper.readAllModuleData();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                moduleID.add(cursor.getString(0));
                moduleName.add(cursor.getString(1));
            }
        }

    }
}