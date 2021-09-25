package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Class;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.Calendar;

public class ClassEdit extends AppCompatActivity {

    String classID;
    Spinner spinner;
    EditText className,classTime,classFee;
    DBHelper dbHelper;
    Button btn_edit;
    TimePickerDialog timePickerDiaglog;
    Calendar calendar;
    int currentHours,currentMinutes;
    String amPm;

    TextView navToStudentText, navToClassesText, navToAssignmentText, navToLessonsText ,navToHomeText;
    Boolean isAllFabsVisible;
    FloatingActionButton mAddFab, navToStudentFab, navtToClassesFab, navToAssignmentFab ,navToLessonsFab, navToHomeFab ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_edit);
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
                        Intent intent = new Intent(ClassEdit.this,ClassDashboard.class);
                        startActivity(intent);
                    }
                });


        navToStudentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i= new Intent(ClassEdit.this,StudentStudentSearch.class);
                        startActivity(i);
                    }
                });
        navToAssignmentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ClassEdit.this, assignment_ret.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        navToHomeFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(ClassEdit.this, MainPage.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        // *******************End Of Navigation*****************//





        spinner = findViewById(R.id.class_spinner_days_edit);
        className = findViewById(R.id.class_et_classNameEdit);
        classTime = findViewById(R.id.class_et_classtimeEdit);
        classFee = findViewById(R.id.class_et_classfeeEdit);
        btn_edit = findViewById(R.id.class_btn_classEdit);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ClassEdit.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.class_arryDays));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);


        //fetch the intent and display data
        Intent intent = getIntent();
        classID = intent.getStringExtra("classIDForEdit"); //current classID

        dbHelper = new DBHelper(ClassEdit.this);
        fetchClassData();
    }

    @Override
    protected void onResume() {

        super.onResume();

        //blocked the key input from classTime edit text
        classTime.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                return true; //blocked keyboard inputs
            }
        });

        classTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                currentHours = calendar.get(Calendar.HOUR_OF_DAY);
                currentMinutes = calendar.get(Calendar.MINUTE);

                timePickerDiaglog = new TimePickerDialog(ClassEdit.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        if(hourOfDay >= 12)
                            amPm = "PM";
                        else
                            amPm = "AM";

                        //now we can set the text
                        classTime.setText(String.format("%02d:%02d",hourOfDay,minutes)+amPm);
                    }
                },currentHours,currentMinutes,false);

                timePickerDiaglog.show();
            }
        });
    }

    //fetch the data
    public void fetchClassData(){

        Cursor cursor = dbHelper.getClassDetailsFromID(classID);
        if(cursor.getCount() == 0 || cursor == null){
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
        else{
            cursor.moveToFirst();
            className.setText(cursor.getString(1)); //className
            classTime.setText(cursor.getString(3)); //classTime
            classFee.setText(cursor.getDouble(4) + ""); //classFee

            //spinner value selected
            String day = cursor.getString(2); //classDay
            String [] classArry = getResources().getStringArray(R.array.class_arryDays); //get arrau
            spinner.setSelection(Arrays.asList(classArry).indexOf(day)); //check index of array and set to spinner
        }
    }

    //update data
    public void updateClassData(View view){

        String cName =className.getText().toString();
        String cDay = spinner.getSelectedItem().toString();
        String cTime = classTime.getText().toString();

        //check validation
        if(dbHelper.validateClassData(cName,cDay,cTime) && spinner.getSelectedItemPosition() > 0){

            Class class1 = new Class();

            class1.setClassID(Integer.parseInt(classID));
            class1.setClassName(className.getText().toString());
            class1.setClassDay(spinner.getSelectedItem().toString());
            class1.setClassTime(classTime.getText().toString());
            class1.setClassFee(Double.parseDouble(classFee.getText().toString()));

            if (dbHelper.updateClassData(class1)) {
                Toast.makeText(this, "Successfully updated", Toast.LENGTH_LONG).show();
              // recreate();
                finish();
            } else {
                Toast.makeText(this, "Update is Failed", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "Invalid class data. Records may exists already", Toast.LENGTH_SHORT).show();
        }
    }
}