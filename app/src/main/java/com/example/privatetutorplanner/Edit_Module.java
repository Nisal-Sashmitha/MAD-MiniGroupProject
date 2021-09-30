package com.example.privatetutorplanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.privatetutorplanner.Database.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Edit_Module extends AppCompatActivity {

    EditText moduleName;
    ImageView btn_EditModule,btn_moduledelete;

    String id,name;

    TextView navToStudentText, navToClassesText, navToAssignmentText, navToLessonsText ,navToHomeText;
    Boolean isAllFabsVisible;
    FloatingActionButton mAddFab, navToStudentFab, navtToClassesFab, navToAssignmentFab ,navToLessonsFab, navToHomeFab ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_module);
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
                        Intent intent = new Intent(Edit_Module.this,ClassDashboard.class);
                        startActivity(intent);
                    }
                });


        navToStudentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i= new Intent(Edit_Module.this,StudentStudentSearch.class);
                        startActivity(i);
                    }
                });
        navToAssignmentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Edit_Module.this, assignment_ret.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        navToHomeFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Edit_Module.this, MainPage.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        navToLessonsFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(Edit_Module.this, Modules_List.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });


        // *******************End Of Navigation*****************//


        moduleName = findViewById(R.id.et_EditModuleName);
        btn_EditModule = findViewById(R.id.btn_EditModule);
        btn_moduledelete = findViewById(R.id.btn_moduledelete);

        //first we call this
        getAndSetIntentData();

        btn_EditModule.setOnClickListener((view) -> {
            //and then we call this
            DBHelper dbHelper = new DBHelper(Edit_Module.this);
            name = moduleName.getText().toString().trim();
            boolean result = dbHelper.updateModule(id,name);  //update method is called

            if(result == true){
                Toast.makeText(Edit_Module.this,"Edited Successfully",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Edit_Module.this , Modules_List.class);
                startActivity(intent);
            }else{
                Toast.makeText(Edit_Module.this,"Failed to",Toast.LENGTH_SHORT).show();
            }

        });

        btn_moduledelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

    }

    //get intent data
    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")){
            //getting data
              id = getIntent().getStringExtra("id");
              name = getIntent().getStringExtra("name");

            //setting data
            moduleName.setText(name);
            Log.d("stev",name);

        }else{
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
        }
    }

    //delete confirmation dialog box
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + name +"?");
        builder.setMessage("Are you sure you want to delete ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int i) {

                DBHelper dbHelper = new DBHelper(Edit_Module.this);
                boolean result = dbHelper.deleteOneRowModule(id);
                if(result == true){
                    Toast.makeText(Edit_Module.this,"Successfully Deleted",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Edit_Module.this , Modules_List.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(Edit_Module.this,"Deletion failed",Toast.LENGTH_SHORT).show();
                }


            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        builder.create().show();
    }

    //Assignments Navigation
    public void assignNav(View v){

        Intent i = new Intent(this, assignment_class_ret.class);
        i.putExtra("mod_name",name);
        startActivity(i);

    }


}



