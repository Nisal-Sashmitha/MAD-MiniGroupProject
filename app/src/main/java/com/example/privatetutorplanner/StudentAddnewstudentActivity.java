package com.example.privatetutorplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.ModalClasses.Student;
import com.example.privatetutorplanner.UtilityClasses.StudentValidations;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentAddnewstudentActivity extends AppCompatActivity {

    Button newStudentAddbtn;
    DBHelper dbHelper;
    EditText name,date,contactNo,address;
    AwesomeValidation awesomeValidation;
    StudentValidations studentValidations = new StudentValidations();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_addnewstudent);


        dbHelper = new DBHelper(this);
        newStudentAddbtn = findViewById(R.id.student_AddNewStudentNavbutton);
        name = findViewById(R.id.student_addstd_Name);
        date = findViewById(R.id.student_addstd_Date);
        contactNo = findViewById(R.id.student_addstd_ContactNo);
        address = findViewById(R.id.student_addstd_Address);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //validation for name
        awesomeValidation.addValidation(this,R.id.student_addstd_Name,
                RegexTemplate.NOT_EMPTY,R.string.student_validation_error_name);
        //validation for phone no
        awesomeValidation.addValidation(this,R.id.student_addstd_ContactNo,
                "[0-9]{10}",R.string.student_validation_error_phoneNo);
        awesomeValidation.addValidation(this,R.id.student_addstd_Date,
                "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$",R.string.student_validation_error_phoneNo);


        
    }



    @Override
    protected void onResume() {
        super.onResume();
        newStudentAddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate() || studentValidations.phoneNoValidator(contactNo.getText().toString())){
                    Student s = new Student();
                    s.setAddress(address.getText().toString());
                    s.setContactNo(contactNo.getText().toString());
                    s.setDateOfBirth(date.getText().toString());
                    s.setName(name.getText().toString());

                    boolean flag = dbHelper.addStudent(s);
                    if(flag){
                        Context context = getApplicationContext();
                        CharSequence text = "student added!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();


                        Intent i= new Intent(StudentAddnewstudentActivity.this,StudentDisplayStudent.class);
                        i.putExtra("studentName",name.getText().toString());
                        i.putExtra("studentID",dbHelper.getLastStudentID());

                        startActivity(i);
                        //Intent i= new Intent(this,PaymentnoteActivity.class);
                    }else{
                        Context context = getApplicationContext();
                        CharSequence text = "failed to add!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                }else {
                    Toast toast = Toast.makeText(StudentAddnewstudentActivity.this,"validation failed", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
    }

    public void navTopaymentNoteStudentForm(View v){
        Button addStudetnavbtn = findViewById(R.id.student_AddNewStudentNavbutton);


        //Intent i= new Intent(this,PaymentnoteActivity.class);
        //i.putExtra("ABC","nisal");
        //startActivity(i);
    }

    public boolean phoneNoValidator(String phoneNo){
        if (phoneNo.matches("[0-9]{10}"))
            return true ;
        else
            return false;

    }
}