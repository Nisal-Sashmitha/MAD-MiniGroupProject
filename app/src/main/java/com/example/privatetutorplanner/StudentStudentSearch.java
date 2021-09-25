package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Student;
import com.example.privatetutorplanner.UtilityClasses.StudentRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StudentStudentSearch extends AppCompatActivity {
    private ArrayList<Student> students = new ArrayList<>();
    DBHelper dbHelper;
    Button addStudetnavbtn;
    EditText searchText;
    TextView addAlarmActionText, addPersonActionText, navToAssignmentText, navToLessonsText ,navToHomeText;
    Boolean isAllFabsVisible;

    FloatingActionButton mAddFab, mAddAlarmFab, mAddPersonFab, navToAssignmentFab ,navToLessonsFab, navToHomeFab ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_student_search);


        // Register all the FABs with their IDs
        // This FAB button is the Parent
        mAddFab = findViewById(R.id.add_fab);
        // FAB button
        mAddAlarmFab = findViewById(R.id.add_alarm_fab);
        mAddPersonFab = findViewById(R.id.add_person_fab);
        navToAssignmentFab = findViewById(R.id.nav_to_assignment);
        navToLessonsFab = findViewById(R.id.nav_to_lesson);
        navToHomeFab = findViewById(R.id.nav_To_home);

        // Also register the action name text, of all the FABs.
        addAlarmActionText = findViewById(R.id.add_alarm_action_text);
        addPersonActionText = findViewById(R.id.add_person_action_text);
        navToAssignmentText = findViewById(R.id.nav_to_assignment_text);
        navToLessonsText =findViewById(R.id.nav_to_lesson_text);
        navToHomeText = findViewById(R.id.nav_To_home_text);

        // Now set all the FABs and all the action name
        // texts as GONE
        mAddAlarmFab.setVisibility(View.GONE);
        mAddPersonFab.setVisibility(View.GONE);
        navToAssignmentFab.setVisibility(View.GONE);
        navToLessonsFab.setVisibility(View.GONE);
        navToHomeFab.setVisibility(View.GONE);
        addAlarmActionText.setVisibility(View.GONE);
        addPersonActionText.setVisibility(View.GONE);
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
                            mAddAlarmFab.show();
                            mAddPersonFab.show();
                            navToAssignmentFab.show();
                            navToLessonsFab.show();
                            navToHomeFab.show();
                            addAlarmActionText.setVisibility(View.VISIBLE);
                            addPersonActionText.setVisibility(View.VISIBLE);
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
                            mAddAlarmFab.hide();
                            mAddPersonFab.hide();
                            navToAssignmentFab.hide();
                            navToLessonsFab.hide();
                            navToHomeFab.hide();
                            addAlarmActionText.setVisibility(View.GONE);
                            addPersonActionText.setVisibility(View.GONE);
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
        mAddPersonFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(StudentStudentSearch.this, "Person Added", Toast.LENGTH_SHORT).show();
                    }
                });

        // below is the sample action to handle add alarm
        // FAB. Here it shows simple Toast msg The Toast
        // will be shown only when they are visible and only
        // when user clicks on them
        mAddAlarmFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(StudentStudentSearch.this, "Alarm Added", Toast.LENGTH_SHORT).show();
                    }
        });







        dbHelper = new DBHelper(this);
        InitStudents();

        searchText = (EditText) findViewById(R.id.student_editTextSearch_studentName);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                students = dbHelper.getStudentsByNameSearch(s.toString());
                InitStudentsbySearch();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });




    }
    public boolean InitStudentsbySearch(){

        initRecyclerView();
        return true;
    }

    public boolean InitStudents(){

        students = dbHelper.getStudents();
        initRecyclerView();
        return true;

    }
    public void initRecyclerView(){
        RecyclerView recyclerView= findViewById(R.id.student_search_results_recycler_view);

        StudentRecyclerViewAdapter adapter = new StudentRecyclerViewAdapter(students,this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public void navToStudent(View v,String name){
        Intent i= new Intent(this,StudentDisplayStudent.class);
        i.putExtra("studentName",name);

        startActivity(i);
    }

    public void navToAddNewStudent(View v){
        Intent i= new Intent(this,StudentAddnewstudentActivity.class);


        startActivity(i);
    }
}