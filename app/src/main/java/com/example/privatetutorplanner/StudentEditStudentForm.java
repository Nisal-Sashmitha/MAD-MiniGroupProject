package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.privatetutorplanner.Database.DBHelper;

public class StudentEditStudentForm extends AppCompatActivity {
    protected String instudentName,inDOB,inaddress,incontactNo;
    protected int ID;
    DBHelper dbHelper;
    EditText name,bDate,contactNo,address;

    AwesomeValidation awesomeValidation;

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

        name = findViewById(R.id.student_editTextSearch_studentName);
        bDate = findViewById(R.id.student_addstd_Date);
        contactNo = findViewById(R.id.student_editTextPhone);
        address = findViewById( R.id.student_editTextEditAddress);
        name.setText(instudentName);
        bDate.setText(inDOB);
        address.setText(inaddress);
        contactNo.setText(incontactNo);



        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //validation for name
        awesomeValidation.addValidation(this,R.id.student_editTextSearch_studentName,
                RegexTemplate.NOT_EMPTY,R.string.student_validation_error_name);
        //validation for phone no
        awesomeValidation.addValidation(this,R.id.student_editTextPhone,
                "[0-9]{10}",R.string.student_validation_error_phoneNo);
        awesomeValidation.addValidation(this,R.id.student_addstd_Date,
                "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$",R.string.student_validation_error_phoneNo);




    }



    public void editStudentSubmitPressed(View v){
        if(awesomeValidation.validate()){
            instudentName = name.getText().toString();
            inDOB = bDate.getText().toString();
            inaddress = address.getText().toString();
            incontactNo = contactNo.getText().toString();

            boolean flag =dbHelper.UpdateStudent(ID,instudentName,inDOB,inaddress,incontactNo);
            if(flag){
                Context context = getApplicationContext();
                CharSequence text = "Updated!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                Intent i= new Intent(this, StudentDisplayStudent.class);

                i.putExtra("studentName",instudentName);
                i.putExtra("studentID",ID);
                startActivity(i);
            }else {
                Context context = getApplicationContext();
                CharSequence text = "Couldn't Update! somthing went Wrong!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }else{

        }


    }
}
