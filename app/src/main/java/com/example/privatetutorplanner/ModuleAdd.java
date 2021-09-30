package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Module;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ModuleAdd extends AppCompatActivity {

    Button btnModuleAdd;
    DBHelper dbHelper;
    EditText et_ModuleName;
    private Context context;

    TextView navToStudentText, navToClassesText, navToAssignmentText, navToLessonsText ,navToHomeText;
    Boolean isAllFabsVisible;
    FloatingActionButton mAddFab, navToStudentFab, navtToClassesFab, navToAssignmentFab ,navToLessonsFab, navToHomeFab ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_add);
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
                        Intent intent = new Intent(ModuleAdd.this,ClassDashboard.class);
                        startActivity(intent);
                    }
                });


        navToStudentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i= new Intent(ModuleAdd.this,StudentStudentSearch.class);
                        startActivity(i);
                    }
                });
        navToAssignmentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ModuleAdd.this, assignment_ret.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        navToHomeFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ModuleAdd.this, MainPage.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        navToLessonsFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(ModuleAdd.this, Modules_List.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });


        // *******************End Of Navigation*****************//



        context = this;
        dbHelper=new DBHelper(context);

        //map button and text
        btnModuleAdd = findViewById(R.id.btn_AddModule);
        et_ModuleName = findViewById(R.id.et_ModuleName);


        btnModuleAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mn=et_ModuleName.getText().toString();
             //validate inputs
             if(TextUtils.isEmpty(et_ModuleName.getText().toString())) {
                 Toast.makeText(ModuleAdd.this, "Empty Fields", Toast.LENGTH_SHORT).show();
             }else{

                     Module module = new Module(mn);

                     dbHelper.addModule(module);

             }
                startActivity(new Intent(context,Modules_List.class));
            }
        });
    }
}