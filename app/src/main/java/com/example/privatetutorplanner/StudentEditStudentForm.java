package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.privatetutorplanner.Database.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StudentEditStudentForm extends AppCompatActivity {
    protected String instudentName,inDOB,inaddress,incontactNo;
    protected int ID;
    DBHelper dbHelper;
    EditText name,bDate,contactNo,address;

    AwesomeValidation awesomeValidation;


    TextView navToStudentText, navToClassesText, navToAssignmentText, navToLessonsText ,navToHomeText;
    Boolean isAllFabsVisible;
    FloatingActionButton mAddFab, navToStudentFab, navtToClassesFab, navToAssignmentFab ,navToLessonsFab, navToHomeFab ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit_student_form);

        getSupportActionBar().hide();


        // *******************Start Of Navigation*****************//
        // This FAB button is the Parent
        mAddFab = findViewById(R.id.add_fab);
        // FAB button
        navToStudentFab = findViewById(R.id.nav_to_student);
        navtToClassesFab = findViewById(R.id.nav_to_classes);
        navToAssignmentFab = findViewById(R.id.nav_to_assignment);
        navToLessonsFab = findViewById(R.id.nav_to_lesson);
        navToHomeFab = findViewById(R.id.nav_To_home);

        // Also register the action name text, of all the FABs.
        navToStudentText = findViewById(R.id.nav_to_student_text);
        navToClassesText = findViewById(R.id.nav_to_classes_text);
        navToAssignmentText = findViewById(R.id.nav_to_assignment_text);
        navToLessonsText =findViewById(R.id.nav_to_lesson_text);
        navToHomeText = findViewById(R.id.nav_To_home_text);

        // Now set all the FABs and all the action name
        // texts as GONE
        navToStudentFab.setVisibility(View.GONE);
        navtToClassesFab.setVisibility(View.GONE);
        navToAssignmentFab.setVisibility(View.GONE);
        navToLessonsFab.setVisibility(View.GONE);
        navToHomeFab.setVisibility(View.GONE);
        navToStudentText.setVisibility(View.GONE);
        navToClassesText.setVisibility(View.GONE);
        navToAssignmentText.setVisibility(View.GONE);
        navToLessonsText.setVisibility(View.GONE);
        navToHomeText.setVisibility(View.GONE);

        // make the boolean variable as false, as all the
        // action name texts and all the sub FABs are invisible
        isAllFabsVisible = false;

        // We will make all the FABs and action name texts
        // visible only when Parent FAB button is clicked So
        // we have to handle the Parent FAB button first, by
        // using setOnClickListener you can see below
        mAddFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!isAllFabsVisible) {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs VISIBLE.
                            navToStudentFab.show();
                            navtToClassesFab.show();
                            navToAssignmentFab.show();
                            navToLessonsFab.show();
                            navToHomeFab.show();
                            navToStudentText.setVisibility(View.VISIBLE);
                            navToClassesText.setVisibility(View.VISIBLE);
                            navToAssignmentText.setVisibility(View.VISIBLE);
                            navToLessonsText.setVisibility(View.VISIBLE);
                            navToHomeText.setVisibility(View.VISIBLE);

                            // make the boolean variable true as
                            // we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = true;
                        } else {

                            // when isAllFabsVisible becomes
                            // true make all the action name
                            // texts and FABs GONE.
                            navToStudentFab.hide();
                            navtToClassesFab.hide();
                            navToAssignmentFab.hide();
                            navToLessonsFab.hide();
                            navToHomeFab.hide();
                            navToStudentText.setVisibility(View.GONE);
                            navToClassesText.setVisibility(View.GONE);
                            navToAssignmentText.setVisibility(View.GONE);
                            navToLessonsText.setVisibility(View.GONE);
                            navToHomeText.setVisibility(View.GONE);

                            // make the boolean variable false
                            // as we have set the sub FABs
                            // visibility to GONE
                            isAllFabsVisible = false;
                        }
                    }
                });

        // below is the sample action to handle add person
        // FAB. Here it shows simple Toast msg. The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        navtToClassesFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(StudentEditStudentForm.this,ClassDashboard.class);
                        startActivity(intent);
                    }
                });


        navToStudentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i= new Intent(StudentEditStudentForm.this,StudentStudentSearch.class);
                        startActivity(i);
                    }
                });
        navToAssignmentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(StudentEditStudentForm.this, assignment_ret.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });
        navToHomeFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(StudentEditStudentForm.this, MainPage.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        // *******************End Of Navigation*****************//




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
        if(awesomeValidation.validate()){ //check whether validation are passed
            instudentName = name.getText().toString();// get data from fields
            inDOB = bDate.getText().toString();
            inaddress = address.getText().toString();
            incontactNo = contactNo.getText().toString();

            //edit details on database
            boolean flag =dbHelper.UpdateStudent(ID,instudentName,inDOB,inaddress,incontactNo);
            if(flag){// check success of editing
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
        }
    }
}
