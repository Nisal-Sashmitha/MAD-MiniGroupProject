package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.ModalClasses.Student;
import com.example.privatetutorplanner.UtilityClasses.StudentDisplayClassListRecyclerViewAdapter;
import com.example.privatetutorplanner.UtilityClasses.StudentRecyclerViewAdapter;

import java.util.ArrayList;

public class StudentEditStudentForm extends AppCompatActivity {
    protected String instudentName,inDOB,inaddress,incontactNo;
    protected int ID;
    DBHelper dbHelper;
    EditText name,bDate,contactNo,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit_student_form);
        Intent intent = getIntent();

        dbHelper = new DBHelper(this);
        instudentName = intent.getExtras().getString("name");
        inDOB = intent.getExtras().getString("dob");
        inaddress = intent.getExtras().getString("address");
        incontactNo = intent.getExtras().getString("contact");
        ID= intent.getExtras().getInt("studentID");

        name = findViewById(R.id.student_editText_studentName);
        bDate = findViewById(R.id.student_addstd_Date);
        contactNo = findViewById(R.id.student_editTextPhone);
        address = findViewById( R.id.student_editTextEditAddress);
        name.setText(instudentName);
        bDate.setText(inDOB);
        address.setText(inaddress);
        contactNo.setText(incontactNo);




    }

    public void editStudentSubmitPressed(View v){


        instudentName = name.getText().toString();
        inDOB = bDate.getText().toString();
        inaddress = address.getText().toString();
        incontactNo = contactNo.getText().toString();

        boolean flag =dbHelper.UpdateStudent(ID,instudentName,inDOB,inaddress,incontactNo);
        if(flag){
            Intent i= new Intent(this, StudentDisplayStudent.class);

            i.putExtra("studentName",instudentName);
            i.putExtra("studentID",ID);
            startActivity(i);
        }else{
            Context context = getApplicationContext();
            CharSequence text = "Couldn't Update! somthing went Wrong!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}