package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.Database.DBHelper;

public class ClassAdd extends AppCompatActivity {

    Spinner spinner;
    EditText className, classTime, classFee;
    Button btn_addClass;
    DBHelper dbHelper;

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
        btn_addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    Class class1 = new Class();
                    class1.setClassName(className.getText().toString());
                    class1.setClassDay(spinner.getSelectedItem().toString());
                    class1.setClassTime(classTime.getText().toString());
                    class1.setClassFee(Double.parseDouble(classFee.getText().toString()));


                    boolean rslt = dbHelper.addNewClass(class1);

                    if (rslt) {
                        Toast.makeText(ClassAdd.this, "New Class Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ClassAdd.this, "Failed to add new class", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(ClassAdd.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }


        });

    }
}