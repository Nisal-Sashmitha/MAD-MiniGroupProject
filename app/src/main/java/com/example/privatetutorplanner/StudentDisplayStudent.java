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

import java.io.Serializable;
import java.util.ArrayList;

public class StudentDisplayStudent extends AppCompatActivity implements StudentAddClassToStdDialog.StudentAddClassToStdDialogListner,StudentUpdatePaymentNoteDialog.StudentEditPaymentNoteDialogListner {
    protected String studentName;
    protected int ID;
    protected Student student;
    DBHelper dbHelper;
    TextView birthdate,contactNo,address;

    private ArrayList<Class> classes = new ArrayList<>();
    private ArrayList<StudentClass> stdclasses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_display_student);
        getSupportActionBar().hide();

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