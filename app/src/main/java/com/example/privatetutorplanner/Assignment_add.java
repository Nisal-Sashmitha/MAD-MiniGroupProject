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
        addbtn = findViewById(R.id.assign_btnUp);

        ob=new DBHelper(this);
        title =  findViewById(R.id.assign_etTitle2);
        modname =  findViewById(R.id.assign_etModName2);
        qu =findViewById(R.id.assign_etQ2);
        mark =  findViewById(R.id.assign_etMark2);
        date = findViewById(R.id.assign_Date2);

    }
    @Override
    protected void onResume() {
        super.onResume();
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate();
            }
        });
    }

    public void Nav(){
        Intent intent = new Intent(this,assignment_ret.class);
        startActivity(intent);
    }
    void validate(){
        int qval=Integer.parseInt(qu.getText().toString());
        int m=Integer.parseInt(mark.getText().toString());

        if(qval<=0 || qval>=50){
            Toast.makeText(getApplicationContext(), "Question field not in range 0 to 50", Toast.LENGTH_LONG).show();
        }
        else if(m>100 || m<=0){
            Toast.makeText(getApplicationContext(), "Marks field is not a percentage", Toast.LENGTH_LONG).show();
        }
        else{
            Assignment assign=new Assignment(title.getText().toString(),
                    modname.getText().toString(),
                    Integer.parseInt(qu.getText().toString()),
                    Integer.parseInt(mark.getText().toString()),
                    date.getText().toString());
            try {
                ans = ob.addAssign(assign);

                if (ans == true) {
                    Toast.makeText(getApplicationContext(), "Data Succesfully added", Toast.LENGTH_LONG).show();
                    Nav();

                } else {
                    Toast.makeText(getApplicationContext(), "Data Not inserted", Toast.LENGTH_LONG).show();
                }
            }catch(Exception e){
                Toast.makeText(getApplicationContext(),"Error :"+e, Toast.LENGTH_LONG).show();
            }

        }
    }
}