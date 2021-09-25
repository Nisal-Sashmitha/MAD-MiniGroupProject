package com.example.privatetutorplanner.UtilityClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.ModalClasses.StudentClass;
import com.example.privatetutorplanner.R;

import com.example.privatetutorplanner.StudentDisplayStudent;
import java.util.ArrayList;

public class StudentDisplayClassListRecyclerViewAdapter extends RecyclerView.Adapter<StudentDisplayClassListRecyclerViewAdapter.ViewHolder>{
    private ArrayList<StudentClass> stdclasses = new ArrayList<>();
    private Context context;

    public StudentDisplayClassListRecyclerViewAdapter(ArrayList<StudentClass> stdclasses, Context context) {
        this.stdclasses = stdclasses;
        this.context = context;
    }

    @NonNull

    @Override
    public StudentDisplayClassListRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        //createing the View holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_displaystudent_calss_item,parent,false);
        return new StudentDisplayClassListRecyclerViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  StudentDisplayClassListRecyclerViewAdapter.ViewHolder holder, int position) {
        //loading data
        holder.className.setText(stdclasses.get(position).getClassName());
        holder.classDescription.setText("Last Payment: "+stdclasses.get(position).getLastPaymentMonth()+"("+stdclasses.get(position).getLastPaymentAmount()+")");
        /*holder.calsssID.setText(Integer.toString(classes.get(position).getClassID()));*/
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                CharSequence text = stdclasses.get(position).getClassName();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                /*Intent i= new Intent(v.getContext(), StudentDisplayStudent.class);

                i.putExtra("studentName",classes.get(position).getClassName());
                i.putExtra("studentID",classes.get(position).getClassID());
                context.startActivity(i);*/
            }
        });
        holder.edtiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof StudentDisplayStudent) {
                    ((StudentDisplayStudent)context).editStudentClassPressed(stdclasses.get(position));
                }
                /*Intent i= new Intent(v.getContext(), StudentDisplayStudent.class);

                i.putExtra("studentName",classes.get(position).getClassName());
                i.putExtra("studentID",classes.get(position).getClassID());
                context.startActivity(i);*/
            }
        });
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (context instanceof StudentDisplayStudent) {
                    ((StudentDisplayStudent)context).removeStudentPressed(stdclasses.get(position).getClassID(),stdclasses.get(position).getClassName());
                }
                /*Intent i= new Intent(v.getContext(), StudentDisplayStudent.class);

                i.putExtra("studentName",classes.get(position).getClassName());
                i.putExtra("studentID",classes.get(position).getClassID());
                context.startActivity(i);*/
            }
        });

    }

    @Override
    public int getItemCount() {
        return stdclasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView className,classDescription;
        Button edtiButton,deleteButton;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            className =itemView.findViewById(R.id.student_display_classes_className);
            classDescription = itemView.findViewById(R.id.student_display_classes_payemnent_detail);
            parentLayout = itemView.findViewById(R.id.parent);
            edtiButton = itemView.findViewById(R.id.student_display_classes_editButton);
            deleteButton = itemView.findViewById(R.id.student_display_classes_delete_btn);
        }
    }
}
