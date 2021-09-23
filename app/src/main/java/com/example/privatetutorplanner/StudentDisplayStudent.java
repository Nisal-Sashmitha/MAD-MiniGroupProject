package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.ModalClasses.Student;
import com.example.privatetutorplanner.ModalClasses.StudentClass;
import com.example.privatetutorplanner.UtilityClasses.StudentAddClassToStdDialog;
import com.example.privatetutorplanner.UtilityClasses.StudentDisplayClassListRecyclerViewAdapter;
import com.example.privatetutorplanner.UtilityClasses.StudentUpdatePaymentNoteDialog;

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
        /*Class s1= new Class();
        s1.setClassName("class1");
        Class s2= new Class();
        s2.setClassName("class1");
        Class s3= new Class();
        s3.setClassName("class1");
        Class s4= new Class();
        s4.setClassName("class1");
        Class s5= new Class();
        s5.setClassName("class1");
        Class s6= new Class();
        s6.setClassName("class1");
        Class s7= new Class();
        s7.setClassName("class1");
        Class s8= new Class();
        s8.setClassName("class1");

        classes.add(s1);
        classes.add(s2);
        classes.add(s3);
        classes.add(s4);
        classes.add(s5);
        classes.add(s6);
        classes.add(s7);
        classes.add(s8);*/

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
                Context context = getApplicationContext();
                CharSequence text = "Succesfull added!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                Intent i= new Intent(this, StudentDisplayStudent.class);

                i.putExtra("studentName",studentName);
                i.putExtra("studentID",ID);
                startActivity(i);
            }else{
                Context context = getApplicationContext();
                CharSequence text = "faild to insert!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        }

    }
}