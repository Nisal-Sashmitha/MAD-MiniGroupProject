package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Module;

public class ModuleAdd extends AppCompatActivity {

    Button btnModuleAdd;
    DBHelper dbHelper;
    EditText et_ModuleName;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_add);
        context = this;
        dbHelper=new DBHelper(context);

        //map button and text
        btnModuleAdd = findViewById(R.id.btn_AddModule);
        et_ModuleName = findViewById(R.id.et_ModuleName);


        btnModuleAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mn=et_ModuleName.getText().toString();

                Module module=new Module(mn);

                dbHelper.addModule(module);

                startActivity(new Intent(context,Modules_List.class));
            }
        });
    }
}