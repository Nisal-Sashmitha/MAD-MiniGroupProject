package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Assignment;

public class Assignment_add extends AppCompatActivity {
    Button addbtn;
    EditText title,modname, qu, mark, date;
    boolean ans= false;
    Context c;

    DBHelper ob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_add);
        addbtn = findViewById(R.id.assign_btnDone);

        ob=new DBHelper(this);
        title =  findViewById(R.id.assign_etTitle);
        modname =  findViewById(R.id.assign_etModName);
        qu =findViewById(R.id.assign_etQ);
        mark =  findViewById(R.id.assign_etMark);
        date = findViewById(R.id.assign_Date);

    }
    @Override
    protected void onResume() {
        super.onResume();
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Assignment assign=new Assignment(title.getText().toString(),
                                                modname.getText().toString(),
                                                Integer.parseInt(qu.getText().toString()),
                                                Integer.parseInt(mark.getText().toString()),
                                                date.getText().toString());
                try {
                    ans = ob.addAssign(assign);

                    if (ans == true) {
                        Toast.makeText(getApplicationContext(), "Data Succesfully added", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Data Not inserted", Toast.LENGTH_LONG).show();
                    }
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"Error :"+e, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}