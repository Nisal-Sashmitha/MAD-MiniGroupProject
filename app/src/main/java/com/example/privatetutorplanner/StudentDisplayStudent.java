package com.example.privatetutorplanner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.ModalClasses.Student;
import com.example.privatetutorplanner.ModalClasses.StudentClass;
import com.example.privatetutorplanner.UtilityClasses.StudentAddClassToStdDialog;
import com.example.privatetutorplanner.UtilityClasses.StudentDisplayClassListRecyclerViewAdapter;
import com.example.privatetutorplanner.UtilityClasses.StudentUpdatePaymentNoteDialog;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

public class StudentDisplayStudent extends AppCompatActivity implements StudentAddClassToStdDialog.StudentAddClassToStdDialogListner,StudentUpdatePaymentNoteDialog.StudentEditPaymentNoteDialogListner {
    protected String studentName;
    protected int ID;
    protected Student student;
    DBHelper dbHelper;
    TextView birthdate,contactNo,address;

    TextView navToStudentText, navToClassesText, navToAssignmentText, navToLessonsText ,navToHomeText;
    Boolean isAllFabsVisible;
    FloatingActionButton mAddFab, navToStudentFab, navtToClassesFab, navToAssignmentFab ,navToLessonsFab, navToHomeFab ;

    private ArrayList<Class> classes = new ArrayList<>();
    private ArrayList<StudentClass> stdclasses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_display_student);
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
                        Intent intent = new Intent(StudentDisplayStudent.this,ClassDashboard.class);
                        startActivity(intent);
                    }
                });


        navToStudentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i= new Intent(StudentDisplayStudent.this,StudentStudentSearch.class);
                        startActivity(i);
                    }
                });
        navToAssignmentFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(StudentDisplayStudent.this, assignment_ret.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        navToHomeFab.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(StudentDisplayStudent.this, MainPage.class);
                        // Intent intent = new Intent(this, assignment_class_ret.class);
                        startActivity(intent);
                    }
                });

        // *******************End Of Navigation*****************//


        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        studentName = intent.getExtras().getString("studentName");
        ID= intent.getExtras().getInt("studentID");
        final TextView helloTextView = (TextView) findViewById(R.id.displaystudentTitile);

        helloTextView.setText(studentName);

        birthdate = findViewById(R.id.displayStudent_details_age_detail);
        contactNo = findViewById(R.id.displayStudent_details_contactNo_detail);
        address = findViewById(R.id.displayStudent_details_adderss_detail);
        student=dbHelper.getStudentByID(ID);
        birthdate.setText(student.getDateOfBirth());
        contactNo.setText(student.getContactNo());
        address.setText(student.getAddress());

        InitClasses();




    }
    //end of the on create
    public void InitClasses(){


        stdclasses = dbHelper.getClassesOfStudent(ID);
        initRecyclerView();

    }
    public void initRecyclerView(){
        RecyclerView recyclerView= findViewById(R.id.student_display_classlist_recyclerView);

        StudentDisplayClassListRecyclerViewAdapter adapter = new StudentDisplayClassListRecyclerViewAdapter(stdclasses,this);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void editStudentPressed(View v){
        Intent intent = new Intent(this,StudentEditStudentForm.class);

        intent.putExtra("studentID", student.getStudentID());
        intent.putExtra("name", student.getName());
        intent.putExtra("dob", student.getDateOfBirth());
        intent.putExtra("contact", student.getContactNo());
        intent.putExtra("address", student.getAddress());
        startActivity(intent);
    }
    public void deleteStudentPressed(View v){
        AlertDialog dialog = new MaterialAlertDialogBuilder(this)
                .setTitle("Warning!")
                .setMessage("Do you really want to delete this student? (this process cannot be undone)")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean flag = dbHelper.deleteStudent(ID);
                        if(flag){
                            Toast.makeText(StudentDisplayStudent.this,"Successfully Deleted!",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            Intent i= new Intent(StudentDisplayStudent.this,StudentStudentSearch.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(StudentDisplayStudent.this,"Failed to delete!",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }


                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(StudentDisplayStudent.this,"Deleting Canceled",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
    public void removeStudentPressed(int classID,String classname){
        AlertDialog dialog = new MaterialAlertDialogBuilder(this)
                .setTitle("Warning!")
                .setMessage("Do you really want remove this student from "+classname+"? (this process cannot be undone)")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean flag = dbHelper.deleteStudentClass(classID,ID);
                        if(flag){
                            Toast.makeText(StudentDisplayStudent.this,"Successfully Removed!",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            InitClasses();
                        }else{
                            Toast.makeText(StudentDisplayStudent.this,"Failed To Remove!",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }


                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(StudentDisplayStudent.this,"Removing Canceled",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }).create();
        dialog.show();
    }
    public void editStudentClassPressed(StudentClass stdcls){

        openEditDialog(stdcls);
        }
    public void openEditDialog(StudentClass stdcls){
        StudentUpdatePaymentNoteDialog dialog = new StudentUpdatePaymentNoteDialog(stdcls);

        dialog.show(getSupportFragmentManager(),"student add to class dialog");


    }


    public void addClassToStudentPressed(View v){
        openDialog();

    }
    public void openDialog(){
        StudentAddClassToStdDialog dialog = new StudentAddClassToStdDialog();

        dialog.show(getSupportFragmentManager(),"student add to class dialog");


    }

    @Override
    public void updateClassDetFromDiolog(int classID, String month, double fee) {


        boolean flag =dbHelper.UpdateStudentClass(ID,classID,month,fee);
        if(flag){
            Toast toast = Toast.makeText(StudentDisplayStudent.this, "Class details Updated", Toast.LENGTH_SHORT);
            toast.show();
            InitClasses();
        }else{
            Toast toast = Toast.makeText(StudentDisplayStudent.this, "Failed To Update", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    public void applyTexts(int classID, String month, double fee) {
        boolean alreadyInFlag=false;
        String className;
        /**/
        for (int i = 0; i < stdclasses.size(); i++){
            System.out.print(stdclasses.get(i) + " ");
            if(stdclasses.get(i).getClassID()==classID){
                alreadyInFlag = true;
                Toast toast = Toast.makeText(this, "Student already in "+stdclasses.get(i).getClassName(), Toast.LENGTH_SHORT);
                toast.show();
                break;
            }
        }
        if(!alreadyInFlag){

            boolean flag = dbHelper.addStudentToAclass(ID,classID,fee,month);
            if(flag){

                CharSequence text = "Succesfully added!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(StudentDisplayStudent.this, text, duration);
                toast.show();
                InitClasses();
                /*Intent i= new Intent(this, StudentDisplayStudent.class);

                i.putExtra("studentName",studentName);
                i.putExtra("studentID",ID);
                startActivity(i);*/
            }else{

                CharSequence text = "faild to insert!";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(StudentDisplayStudent.this, text, duration);
                toast.show();
            }
        }

    }
}