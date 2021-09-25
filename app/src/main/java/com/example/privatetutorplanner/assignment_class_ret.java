package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Assignment;
import com.example.privatetutorplanner.UtilityClasses.Assignment.Assign_PopBtn;

import java.util.ArrayList;
import java.util.HashSet;

public class assignment_class_ret extends AppCompatActivity {
    DBHelper ob;
    ArrayList<String> module_name;
    ArrayList<Assignment> details;
    ArrayList<Integer> id;
    ArrayList<String> module;


    HashSet<String> sorter ;
    assign_childAdpt child;
    RecyclerView recyclerView;
    ImageButton navAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_class_ret);

        recyclerView= findViewById(R.id.recycle_assign_class);
        ob= new DBHelper(this);
        details=new ArrayList<>();
        module_name = new ArrayList<>();

        module=new ArrayList<>();
        id=new ArrayList<>();
        sorter= new HashSet<String>();
        storeModules();
        DetailModules();


        try {
            child = new assign_childAdpt(assignment_class_ret.this,this,details,"Physics");
            recyclerView.setAdapter(child);
            recyclerView.setLayoutManager(new LinearLayoutManager(assignment_class_ret.this));
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error assign_class_ret :"+e, Toast.LENGTH_LONG).show();
        }

    }
    void storeModules(){
        try {
            Cursor cursor = ob.readModules();
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No assignments to show", Toast.LENGTH_LONG).show();
            } else {
                while (cursor.moveToNext()) {
                    id.add(cursor.getInt(0));
                    module_name.add(cursor.getString(1));
                }
                for (String i : module_name) {
                    sorter.add(i);
                }
                for(String j: sorter){
                    module.add(j);
                }
            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error storeModules:"+e, Toast.LENGTH_LONG).show();
        }
    }
    void DetailModules(){
        try{
            int z=id.size();
            for(int i=0;i<z;i++ ) {
                Assignment result = ob.getDetModules(id.get(i));
                details.add(result);
                Log.v("key:", result.getDate());
                Log.v("key:", Integer.toString(result.getMark()));
                Log.v("key:", result.getModulename());
            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error  DetailModules assign_class:"+e, Toast.LENGTH_LONG).show();
        }
    }

    public void nav(View view){
      /*  Intent intent = new Intent(this, Assignment_.class);
        startActivity(intent);*/

        Assign_PopBtn pop= new Assign_PopBtn();
        pop.show(getSupportFragmentManager(), "example dialog");



    }

}