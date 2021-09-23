package com.example.privatetutorplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Assignment;

public class assign_update extends AppCompatActivity {
    EditText title,module,qu,mark,date;
    Button upBtn;
    String id, atitle,amodule,amark,aqu,adate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assign_update);



        title=findViewById(R.id.assign_etTitle2);
        module=findViewById(R.id.assign_etModName2);
        qu=findViewById(R.id.assign_etQ2);
        mark=findViewById(R.id.assign_etMark2);
        date=findViewById(R.id.assign_Date2);
        upBtn=findViewById(R.id.assign_btnUp);

        getIntentData();

        upBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();

            }
        });

    }

    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("module") && getIntent().hasExtra("qu") &&
                    getIntent().hasExtra("marks") && getIntent().hasExtra("date")){



            id = getIntent().getStringExtra("id");
            atitle = getIntent().getStringExtra("title");
            amodule = getIntent().getStringExtra("module");
            amark = getIntent().getStringExtra("marks");
            aqu = getIntent().getStringExtra("qu");
            adate = getIntent().getStringExtra("date");


            title.setText(atitle);
            module.setText(amodule);
            qu.setText(aqu);
            mark.setText(amark);
            date.setText(adate);
            Log.i("Checking intent", title+" "+amodule+" "+adate);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_LONG).show();
        }
    }
    void validate(){
        int qval=Integer.parseInt(qu.getText().toString().trim());
        int m=Integer.parseInt(mark.getText().toString().trim());

        if(qval<=0 || qval>=50){
            Toast.makeText(getApplicationContext(), "Question field not in range 0 to 50", Toast.LENGTH_LONG).show();
        }
        else if(m>100 || m<=0){
            Toast.makeText(getApplicationContext(), "Marks field is not in a percentage range", Toast.LENGTH_LONG).show();
        }
        else{
            DBHelper DB = new DBHelper(assign_update.this);

            atitle = title.getText().toString().trim();
            amodule = module.getText().toString().trim();
            amark = mark.getText().toString().trim();
            aqu = qu.getText().toString().trim();
            adate = date.getText().toString().trim();


            DB.updateAssign(id, atitle, amodule, amark,aqu,adate);

            Intent intent = new Intent(assign_update.this, assignment_ret.class);
            startActivity(intent);

        }
    }
}