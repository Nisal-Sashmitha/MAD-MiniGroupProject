package com.example.privatetutorplanner.UtilityClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.privatetutorplanner.ModalClasses.Student;
import com.example.privatetutorplanner.R;

import com.example.privatetutorplanner.StudentDisplayStudent;


import java.util.ArrayList;

public class StudentRecyclerViewAdapter extends RecyclerView.Adapter<StudentRecyclerViewAdapter.ViewHolder>{

    private ArrayList<Student> students = new ArrayList<>();
    private Context context;

    public StudentRecyclerViewAdapter(ArrayList<Student> students, Context context) {
        this.students = students;
        this.context = context;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        //createing the View holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_search_list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull  StudentRecyclerViewAdapter.ViewHolder holder, int position) {
        //loading data
        holder.studentName.setText(students.get(position).getName());
        holder.studenttID.setText(Integer.toString(students.get(position).getStudentID()));
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(v.getContext(), StudentDisplayStudent.class);

                i.putExtra("studentName",students.get(position).getName());
                i.putExtra("studentID",students.get(position).getStudentID());
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView studentName,studenttID;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            studentName =itemView.findViewById(R.id.personName);
            studenttID = itemView.findViewById(R.id.student_search_details_studentID);
            parentLayout = itemView.findViewById(R.id.parent);
        }
    }
}
