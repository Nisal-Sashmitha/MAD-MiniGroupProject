package com.example.privatetutorplanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;

public class ClassIndividual extends AppCompatActivity {

    TextView tv_className,tv_classDay,tv_classTime,tv_classFee;
    String classID;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_individual);

        tv_className = findViewById(R.id.class_tv_selectedClassName);
        tv_classDay  = findViewById(R.id.class_tv_selectedClassDay);
        tv_classTime = findViewById(R.id.class_tv_selected_classTime);
        tv_classFee = findViewById(R.id.class_tv_selectedClassFee);

        dbHelper = new DBHelper(this);

        Intent intent = getIntent();
        classID = intent.getStringExtra("selectedClassID");
        fetchClassData();
    }

    //fetch class data
    public void fetchClassData(){

        Cursor cursor = dbHelper.getClassDetailsFromID(classID);

        if(cursor.getCount() == 0){
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
        else{
            if(cursor.moveToFirst()){

                tv_className.setText(cursor.getString(1));
                tv_classDay.setText(cursor.getString(2));
                tv_classTime.setText(cursor.getString(3));
                tv_classFee.setText(cursor.getDouble(4) + "");

                Toast.makeText(this, "Successfully fetched", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Unsuccessfully fetching", Toast.LENGTH_SHORT).show();
            }


        }
    }

    //navigate to edit page
    public void navClassEdit(View view){

        Intent intent1 = new Intent(ClassIndividual.this,ClassEdit.class);
        intent1.putExtra("classIDForEdit",classID);
//        startActivity(intent1);
        startActivityForResult(intent1,100); //to refresh activity
        //deprecated but working correctly

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            recreate(); //to refresh activity
        }
    }

    //delete class data
    public void deleteClass(){
        try {
            if (dbHelper.deleteClassDetails(classID)) {
                Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                finish(); //close activity

            } else {
                Toast.makeText(this, "Deletion Failed", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //confirmation message for class deletion
    public void confirmaDiaglogDeleteClass(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Class " + tv_className.getText() + " ?");

        builder.setMessage("Are you sure you want to delete " + tv_className.getText() + " class?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteClass();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}