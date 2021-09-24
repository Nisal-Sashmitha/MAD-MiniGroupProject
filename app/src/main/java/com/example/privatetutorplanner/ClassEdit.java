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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Class;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_edit);

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
        if(dbHelper.validateClassData(cName,cDay,cTime)){

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