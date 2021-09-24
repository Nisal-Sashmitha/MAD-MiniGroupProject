package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.Database.DBHelper;

import java.util.Calendar;

public class ClassAdd extends AppCompatActivity {

    Spinner spinner;
    EditText className, classTime, classFee;
    Button btn_addClass;
    DBHelper dbHelper;
    TimePickerDialog timePickerDiaglog;
    Calendar calendar;
    int currentHours,currentMinutes;
    String amPm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_add);

        spinner = findViewById(R.id.class_spinner_days);

        className = findViewById(R.id.class_et_className);
        classTime = findViewById(R.id.class_et_classtime);
        classFee = findViewById(R.id.class_et_classfee);

        btn_addClass = findViewById(R.id.class_btn_new_class_add);
        dbHelper = new DBHelper(this);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ClassAdd.this, android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.class_arryDays));
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
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

                timePickerDiaglog = new TimePickerDialog(ClassAdd.this, new TimePickerDialog.OnTimeSetListener() {
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



        btn_addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String cName =className.getText().toString();
                    String cDay = spinner.getSelectedItem().toString();
                    String cTime = classTime.getText().toString();

                    if(dbHelper.validateClassData(cName,cDay,cTime)) {

                        Class class1 = new Class();
                        class1.setClassName(className.getText().toString());
                        class1.setClassDay(spinner.getSelectedItem().toString());
                        class1.setClassTime(classTime.getText().toString());
                        class1.setClassFee(Double.parseDouble(classFee.getText().toString()));


                        boolean rslt = dbHelper.addNewClass(class1);

                        if (rslt) {
                            Toast.makeText(ClassAdd.this, "New Class Added", Toast.LENGTH_SHORT).show();

                            //refresh content
                            className.setText("");
                            classTime.setText("");
                            classFee.setText("");
                            spinner.setSelection(0);

                        } else {
                            Toast.makeText(ClassAdd.this, "Failed to add new class", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(ClassAdd.this, "Invalid class details.Records may exists already", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ClassAdd.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


        });

    }
}