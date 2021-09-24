package com.example.privatetutorplanner.UtilityClasses.Assignment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.privatetutorplanner.Assignment_Exist;
import com.example.privatetutorplanner.Assignment_add;
import com.example.privatetutorplanner.R;


public class Assign_PopBtn extends AppCompatDialogFragment {

    Button add, exist;


    @NonNull


    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.assign_popbtn, null);

        builder.setView(view);


        add= view.findViewById(R.id.new_Assign);
        exist= view.findViewById(R.id.exist_Assign);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Assignment_add.class);
                getContext().startActivity(intent);

            }
        });
        exist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Assignment_Exist.class);
                getContext().startActivity(intent);

            }
        });




        return builder.create();



    }


}
