package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Assignment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Assignment_add extends AppCompatActivity {
    Button addbtn;
    EditText title,modname, qu, mark, date;
    boolean ans= false;
    Context c;
    Spinner module;

    DBHelper ob;
    ArrayList<String> getmodule;

    TextView navToStudentText, navToClassesText, navToAssignmentText, navToLessonsText ,navToHomeText;
    Boolean isAllFabsVisible;
    FloatingActionButton mAddFab, navToStudentFab, navtToClassesFab, navToAssignmentFab ,navToLessonsFab, navToHomeFab ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_add);
        addbtn = findViewById(R.id.assign_btnUp);
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
                        Intent intent = new Intent(Assignment_add.this,ClassDashboard.class);
                        startActivity(intent);
                    }
                });


        navToStudentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i= new Intent(Assignment_add.this,StudentStudentSearch.class);
                        startActivity(i);
                    }
                });
        navToAssignmentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Assignment_add.this, assignment_ret.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        navToHomeFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(Assignment_add.this, MainPage.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        // *******************End Of Navigation*****************//



        getmodule=new ArrayList<>();
        ob=new DBHelper(this);
        readModules();
        title =  findViewById(R.id.assign_etTitle2);
        module=findViewById(R.id.assign_ModSpin);
        qu =findViewById(R.id.assign_etQ2);
        mark =  findViewById(R.id.assign_etMark2);
        date = findViewById(R.id.assign_Date2);

        module.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,getmodule));
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validate();
            }
        });
    }

    void readModules(){
        try{
            Cursor m1= ob.readAllModuleData();
            if (m1.getCount() == 0) {
                Toast.makeText(this, "No modules to show", Toast.LENGTH_LONG).show();
            }else{
                while (m1.moveToNext()) {
                    //Module name is assigned to Arraylist
                    getmodule.add(m1.getString(1));
                }
            }

        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error addModules:"+e, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

    }



    public void Nav(){
        Intent intent = new Intent(this,assignment_ret.class);
        startActivity(intent);
    }
    void validate(){
       // int qval=Integer.parseInt(qu.getText().toString());
        //int m=Integer.parseInt(mark.getText().toString());

        if(title.getText().toString().trim().length()==0 || module.getSelectedItem().toString().trim().length()==0 ||
               qu.getText().toString().trim().length()==0 || mark.getText().toString().trim().length()==0 ||
                date.getText().toString().trim().length()==0 ){
            Toast.makeText(getApplicationContext(), "Some fields are empty", Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(qu.getText().toString())<=0 || Integer.parseInt(qu.getText().toString())>=50){
            Toast.makeText(getApplicationContext(), "Question field not in range 0 to 50", Toast.LENGTH_LONG).show();
        }
        else if(Integer.parseInt(mark.getText().toString())>100 || Integer.parseInt(mark.getText().toString())<=0){
            Toast.makeText(getApplicationContext(), "Marks field is not a percentage", Toast.LENGTH_LONG).show();
        }
        else{
            Assignment assign=new Assignment(title.getText().toString(),
                    module.getSelectedItem().toString(),
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