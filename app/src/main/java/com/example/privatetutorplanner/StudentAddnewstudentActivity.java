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

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Student;

public class StudentAddnewstudentActivity extends AppCompatActivity {

    Button newStudentAddbtn;
    DBHelper dbHelper;
    EditText name,date,contactNo,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_addnewstudent);
        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);

        dbHelper = new DBHelper(this);
        newStudentAddbtn = findViewById(R.id.student_AddNewStudentNavbutton);
        name = findViewById(R.id.student_addstd_Name);
        date = findViewById(R.id.student_addstd_Date);
        contactNo = findViewById(R.id.student_addstd_ContactNo);
        address = findViewById(R.id.student_addstd_Address);



// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.student_classes_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        
    }



    @Override
    protected void onResume() {
        super.onResume();
        newStudentAddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                }else{
                    Context context = getApplicationContext();
                    CharSequence text = "failed to add!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
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
}