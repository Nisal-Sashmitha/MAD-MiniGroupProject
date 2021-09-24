package com.example.privatetutorplanner.UtilityClasses;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.privatetutorplanner.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Class_CustomAdapterStudent extends RecyclerView.Adapter<Class_CustomAdapterStudent.MyViewhHolder>{
    Context context;
    ArrayList<String> studentName_arryList;

    public Class_CustomAdapterStudent(Context context, ArrayList<String> studentName_arryList){
        this.context = context;
        this.studentName_arryList = studentName_arryList;

    }

    @NonNull
    @NotNull
    @Override
    public Class_CustomAdapterStudent.MyViewhHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.class_student_recycle_list_item,parent,false);
        return new MyViewhHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Class_CustomAdapterStudent.MyViewhHolder holder, int position) {
        holder.tv_studentName.setText(studentName_arryList.get(position));
    }

    @Override
    public int getItemCount() {
        return studentName_arryList.size();
    }

    public class MyViewhHolder extends RecyclerView.ViewHolder {

        TextView tv_studentName;
        LinearLayout class_student_recycle_item_layout;

        public MyViewhHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            tv_studentName = itemView.findViewById(R.id.class_student_tv_recycle_studentName);
            class_student_recycle_item_layout = itemView.findViewById(R.id.class_student_layout_recycle_list_linearLayout);
        }
    }
}
