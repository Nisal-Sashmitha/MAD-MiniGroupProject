package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Assignment;

import java.util.ArrayList;

public class Assignment_Exist extends AppCompatActivity {
    Button done;
    DBHelper ob;
    Spinner title,module;
    ArrayList<String> assign_title;
    ArrayList<String> assign_mod;
    Boolean ans=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_exist);


        done= findViewById(R.id.exist_add_btn);
        title= findViewById(R.id.title_spin);
        module= findViewById(R.id.mod_spin);
        assign_title=new ArrayList<>();
        assign_mod=new ArrayList<>();
        ob= new DBHelper(this);
        AddTitles();


        title.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,assign_title));
        module.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,assign_mod));

    }

    @Override
    protected void onResume() {
        super.onResume();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ti=title.getSelectedItem().toString();
                String mod_title=module.getSelectedItem().toString();
                Assignment newas1 ;

                newas1=ob.getAssignment(ti);

                if(newas1.getModulename().equals(mod_title)){
                    Toast.makeText(getApplicationContext(), "Module name already exists in the db", Toast.LENGTH_LONG).show();
                }
                else{
                    Assignment asNew= new Assignment(ti,mod_title,newas1.getQu(),newas1.getMark(),newas1.getDate());

                    ans=ob.addAssign(asNew);
                    if (ans == true) {
                        Toast.makeText(getApplicationContext(), "Data Succesfully added", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Data Not inserted", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    void AddTitles(){
        try {
            Cursor cursor = ob.readTitle();
            if (cursor.getCount() == 0) {
                Toast.makeText(getApplicationContext(), "No titles in assignments  to show", Toast.LENGTH_LONG).show();
            } else {
                while (cursor.moveToNext()) {
                    assign_title.add(cursor.getString(0));
                }

            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error storeModules in existform:"+e, Toast.LENGTH_LONG).show();
        }
    }
}