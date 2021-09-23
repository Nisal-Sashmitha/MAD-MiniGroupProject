package com.example.privatetutorplanner.UtilityClasses;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;



import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.R;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentAddClassToStdDialog extends AppCompatDialogFragment {
    private Spinner spinner;
    private EditText editTextMonth;
    private EditText editTextFee;
    DBHelper dbHelper;
    HashMap<String, Integer> classNameAndID = new HashMap<String, Integer>();

    private StudentAddClassToStdDialogListner listner;
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_student_addto_a_classs_dialog,null);


        builder.setView(view)
                .setTitle("Add To A Class")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                String className =spinner.getSelectedItem().toString();
                String month =editTextMonth.getText().toString();
                double fee;

                if(editTextFee.getText().toString().isEmpty()){
                    fee =0;
                }else {
                    fee =Double.parseDouble(editTextFee.getText().toString());
                }


                int classID = classNameAndID.get(className);
                listner.applyTexts(classID,month,fee);
            }
        });



        dbHelper = new DBHelper(view.getContext());
        spinner = view.findViewById(R.id.student_selectClass_in_disply_addtocls_spinner);
        editTextMonth = view.findViewById(R.id.student_semesterOrMonthEditText);
        editTextFee = view.findViewById(R.id.student_Addclass_paidAmountForClass);



        ArrayList<Class> clslist= dbHelper.getClassListForStudent();
        final String clslistArray[] = new String[clslist.size()];
        for (int i = 0; i < clslist.size(); i++){
            clslistArray[i]=clslist.get(i).getClassName();
            classNameAndID.put(clslist.get(i).getClassName(), clslist.get(i).getClassID());
        }

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(view.getContext(),
                android.R.layout.simple_spinner_item, clslistArray);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listner = (StudentAddClassToStdDialogListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implemet ExampleDialogListner");
        }
    }

    public interface StudentAddClassToStdDialogListner{
        void applyTexts(int classID,String month,double fee);
    }
}
