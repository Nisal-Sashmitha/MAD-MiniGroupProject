package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Assignment;

import java.util.ArrayList;
import java.util.HashSet;

public class assignment_ret extends AppCompatActivity {


    DBHelper ob;
    ArrayList<String> module_name;
    ArrayList<Integer> id;
    ArrayList<String> module;
    ArrayList<Assignment> details;

    HashSet<String> sorter ;
    asssignment_ret_adapt1 adpt;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_ret);

        recyclerView= findViewById(R.id.recycle_assign1);
        ob= new DBHelper(this);
        module_name = new ArrayList<>();
        details=new ArrayList<>();
        module=new ArrayList<>();
        id=new ArrayList<>();
        sorter= new HashSet<String>();


        storeModules();
        DetailModules();
        try {
            adpt = new asssignment_ret_adapt1(assignment_ret.this,this, module,details);
            recyclerView.setAdapter(adpt);
            recyclerView.setLayoutManager(new LinearLayoutManager(assignment_ret.this));
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error assign_ret :"+e, Toast.LENGTH_LONG).show();
        }
    }

    void storeModules(){
        try {
            Cursor cursor = ob.readModules();
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No data in the Database", Toast.LENGTH_LONG).show();
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
            Toast.makeText(getApplicationContext(),"Error  DetailModules:"+e, Toast.LENGTH_LONG).show();
        }
    }
}