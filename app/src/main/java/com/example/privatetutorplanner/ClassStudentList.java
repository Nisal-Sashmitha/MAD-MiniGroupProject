package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.UtilityClasses.Class_CustomAdapterStudent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;

public class ClassStudentList extends AppCompatActivity {

    String classID ;
    ArrayList<String> class_students_arryList;
    DBHelper dbHelper;
    TextView tv_noData;
    ImageView iv_noData;
    RecyclerView recyclerView;
    Class_CustomAdapterStudent customAdapterStudent;

    TextView navToStudentText, navToClassesText, navToAssignmentText, navToLessonsText ,navToHomeText;
    Boolean isAllFabsVisible;
    FloatingActionButton mAddFab, navToStudentFab, navtToClassesFab, navToAssignmentFab ,navToLessonsFab, navToHomeFab ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_student_list);
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
                        Intent intent = new Intent(ClassStudentList.this,ClassDashboard.class);
                        startActivity(intent);
                    }
                });


        navToStudentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i= new Intent(ClassStudentList.this,StudentStudentSearch.class);
                        startActivity(i);
                    }
                });
        navToAssignmentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ClassStudentList.this, assignment_ret.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        navToHomeFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(ClassStudentList.this, MainPage.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        // *******************End Of Navigation*****************//



        tv_noData = findViewById(R.id.class_student_tv_noData);
        iv_noData = findViewById(R.id.class_student_iv_noData);
        recyclerView = findViewById(R.id.class_student_recyclerView);

        Intent intent = getIntent();
        classID = intent.getStringExtra("SelectedClassID");

        dbHelper = new DBHelper(ClassStudentList.this);

        class_students_arryList = new ArrayList<>();
        fetchClassStudentData();

        try {
            //view in recycle view
            customAdapterStudent = new Class_CustomAdapterStudent(ClassStudentList.this, class_students_arryList);
            recyclerView.setAdapter(customAdapterStudent);
            recyclerView.setLayoutManager(new LinearLayoutManager(ClassStudentList.this));
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void fetchClassStudentData(){
        if(classID.equals("")){
            Toast.makeText(this, "Invalid Selection", Toast.LENGTH_SHORT).show();
        }
        else{
            Cursor cursor = dbHelper.getStudentDataBasedID(classID);
            if(cursor.getCount() == 0){
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
                tv_noData.setVisibility(View.VISIBLE);
                iv_noData.setVisibility(View.VISIBLE);
            }
            else{
                tv_noData.setVisibility(View.GONE);
                iv_noData.setVisibility(View.GONE);

                while(cursor.moveToNext()){
                    class_students_arryList.add(cursor.getString(0));
                }
                Toast.makeText(this, "Succesfully students names fetched", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void navStudentSearch(View view){
        Intent i = new Intent(ClassStudentList.this,StudentStudentSearch.class);
        startActivity(i);
    }
}