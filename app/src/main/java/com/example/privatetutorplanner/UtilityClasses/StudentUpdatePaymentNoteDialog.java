package com.example.privatetutorplanner.UtilityClasses;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.ModalClasses.StudentClass;
import com.example.privatetutorplanner.R;
import com.example.privatetutorplanner.StudentDisplayStudent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class StudentUpdatePaymentNoteDialog extends AppCompatDialogFragment{
    private Spinner spinner;
    private EditText editTextMonth;
    private EditText editTextFee;
    DBHelper dbHelper;
    private StudentClass stdcls;
    HashMap<String, Integer> classNameAndID = new HashMap<String, Integer>();
    AwesomeValidation awesomeValidation;

    public StudentUpdatePaymentNoteDialog(StudentClass stdcls){
        this.stdcls =stdcls;
    }

    private StudentUpdatePaymentNoteDialog.StudentEditPaymentNoteDialogListner listner;
    @Override
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_student_addto_a_classs_dialog,null);


        builder.setView(view)
                .setTitle("Edit Payment Note")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                String className =spinner.getSelectedItem().toString();
                String month =editTextMonth.getText().toString();
                double fee= checkFee(editTextFee.getText().toString());

                /*if(editTextFee.getText().toString().isEmpty()){
                    fee =0;
                }else {
                    fee =Double.parseDouble(editTextFee.getText().toString());
                }*/


                int classID = stdcls.getClassID();
                listner.updateClassDetFromDiolog(classID,month,fee);
            }
        });



        dbHelper = new DBHelper(view.getContext());
        spinner = view.findViewById(R.id.student_selectClass_in_disply_addtocls_spinner);
        editTextMonth = view.findViewById(R.id.student_semesterOrMonthEditText);
        editTextFee = view.findViewById(R.id.student_Addclass_paidAmountForClass);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        //validation for name




        final String clslistArray[] = new String[1];
        clslistArray[0]= stdcls.getClassName();


// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(view.getContext(),
                android.R.layout.simple_spinner_item, clslistArray);

// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        editTextMonth.setText(stdcls.getLastPaymentMonth());
        editTextFee.setText(String.valueOf(stdcls.getLastPaymentAmount()));


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listner = (StudentUpdatePaymentNoteDialog.StudentEditPaymentNoteDialogListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implemet ExampleDialogListner");
        }
    }

    public interface StudentEditPaymentNoteDialogListner{
        void updateClassDetFromDiolog(int classID,String month,double fee);
    }

    public Double checkFee(String value){
        double fee;

        if(value.isEmpty()){
            fee =0;
        }else {
            fee =Double.parseDouble(value);
        }
        return fee;
    }
}
