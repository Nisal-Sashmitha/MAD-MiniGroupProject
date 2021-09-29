package com.example.privatetutorplanner;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.ModalClasses.Module;

import java.util.Arrays;
import java.util.Calendar;

public class Edit_Module extends AppCompatActivity {

    EditText moduleName;
    Button btn_EditModule, btn_moduledelete;

    String id,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_module);

        moduleName = findViewById(R.id.et_EditModuleName);
        btn_EditModule = findViewById(R.id.btn_EditModule);
        btn_moduledelete = findViewById(R.id.btn_moduledelete);

        //first we call this
        getAndSetIntentData();

        btn_EditModule.setOnClickListener((view) -> {
            //and then we call this
            DBHelper dbHelper = new DBHelper(Edit_Module.this);
            name = moduleName.getText().toString().trim();
            dbHelper.updateModule(id,name);  //update method is called

            Intent intent = new Intent(Edit_Module.this , Modules_List.class);
            startActivity(intent);

        });

        btn_moduledelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    //get intent data
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")){
            //getting data
              id = getIntent().getStringExtra("id");
              name = getIntent().getStringExtra("name");

            //setting data
            moduleName.setText(name);
            Log.d("stev",name);

        }else{
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
    }

    //delete confirmation dialog box
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + name +"?");
        builder.setMessage("Are you sure you want to delete ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int i) {

                DBHelper dbHelper = new DBHelper(Edit_Module.this);
                boolean result = dbHelper.deleteOneRowModule(id);
                if(result == true){
                    Toast.makeText(Edit_Module.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Edit_Module.this , Modules_List.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Edit_Module.this,"Deletion failed",Toast.LENGTH_SHORT).show();
                }


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.create().show();
    }


}



