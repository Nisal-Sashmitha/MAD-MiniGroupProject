package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Assignment;
import com.example.privatetutorplanner.UtilityClasses.Assignment.Assign_PopBtn;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashSet;

public class assignment_ret extends AppCompatActivity {


    DBHelper ob;
    ArrayList<String> module_name;
    ArrayList<Integer> id;
    ArrayList<String> module;
    ArrayList<Assignment> details;

    HashSet<String> sorter ;
    asssignment_ret_adapt1 adpt;
    RecyclerView recyclerView;
    ImageView navAdd;
    Dialog btnDialog;

    TextView navToStudentText, navToClassesText, navToAssignmentText, navToLessonsText ,navToHomeText;
    Boolean isAllFabsVisible;
    FloatingActionButton mAddFab, navToStudentFab, navtToClassesFab, navToAssignmentFab ,navToLessonsFab, navToHomeFab ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assignment_ret);
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
                        Intent intent = new Intent(assignment_ret.this,ClassDashboard.class);
                        startActivity(intent);
                    }
                });


        navToStudentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i= new Intent(assignment_ret.this,StudentStudentSearch.class);
                        startActivity(i);
                    }
                });
        navToAssignmentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(assignment_ret.this, assignment_ret.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        navToHomeFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(assignment_ret.this, MainPage.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        // *******************End Of Navigation*****************//




        recyclerView= findViewById(R.id.recycle_assign1);
        ob= new DBHelper(this);
        module_name = new ArrayList<>();
        details=new ArrayList<>();
        module=new ArrayList<>();
        id=new ArrayList<>();
        sorter= new HashSet<String>();

        btnDialog =new Dialog(this);


        storeModules();

        DetailModules();

        try { //Retieved arraylist is sent to recycle view
            adpt = new asssignment_ret_adapt1(assignment_ret.this,this, module,details);
            recyclerView.setAdapter(adpt);
            recyclerView.setLayoutManager(new LinearLayoutManager(assignment_ret.this));
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error assign_ret :"+e, Toast.LENGTH_LONG).show();
        }
    }
    //Only Modules Columns which is stored in the Assignment DB is retrieved
    void storeModules(){
        try {
            Cursor cursor = ob.readModules();
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "No assignments to show", Toast.LENGTH_LONG).show();
            } else {
                while (cursor.moveToNext()) {
                    id.add(cursor.getInt(0));
                    module_name.add(cursor.getString(1)); //Module name is assigned to Arraylist
                }
                for (String i : module_name) {
                    sorter.add(i); //Using HashSet retrieved list is made unique
                }
                for(String j: sorter){
                    module.add(j); //From HashSet to Arraylist to RecyclerView
                }
            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error storeModules:"+e, Toast.LENGTH_LONG).show();
        }
    }

    //All details of the assignment is retrieved from the DB
    void DetailModules(){
        try{
            int z=id.size();
            for(int i=0;i<z;i++ ) {
                Assignment result = ob.getDetModules(id.get(i));
                details.add(result);
                Log.v("key:", result.getDate());
                Log.v("key:", Integer.toString(result.getMark()));
                Log.v("key:", result.getModulename());
            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(),"Error  DetailModules:"+e, Toast.LENGTH_LONG).show();
        }
    }

    public void nav(View view){
       /* Intent intent = new Intent(this, Assignment_add.class);
        startActivity(intent);*/

        Assign_PopBtn pop= new Assign_PopBtn();
       pop.show(getSupportFragmentManager(), "example dialog");


    }
}