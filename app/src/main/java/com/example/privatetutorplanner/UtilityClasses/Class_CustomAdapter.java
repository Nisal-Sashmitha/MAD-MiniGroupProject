package com.example.privatetutorplanner.UtilityClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.R;
import com.example.privatetutorplanner.ClassIndividual;

public class Class_CustomAdapter extends RecyclerView.Adapter<Class_CustomAdapter.MyViewhHolder> {
    Context context;
    ArrayList<Class> class_arryList;
    Activity activity;

    public Class_CustomAdapter(Activity activity, Context context, ArrayList<Class>class_arryList){
        this.activity = activity;
        this.context = context;
        this.class_arryList = class_arryList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewhHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.class_recycle_list,parent,false);
        return new MyViewhHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull Class_CustomAdapter.MyViewhHolder holder, int position) {
        holder.tv_classID.setText(class_arryList.get(position).getClassID()+"");
        holder.tv_className.setText(class_arryList.get(position).getClassName());
        holder.tv_classTime.setText(class_arryList.get(position).getClassTime());
        holder.tv_classFee.setText("Rs. "+class_arryList.get(position).getClassFee());

        holder.class_recycle_list_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ClassIndividual.class);
                intent.putExtra("selectedClassID",String.valueOf(class_arryList.get(position).getClassID()));
//                context.startActivity(intent);
                activity.startActivityForResult(intent,200);
            }
        });
    }

    @Override
    public int getItemCount() {
        return class_arryList.size();
    }

    public class MyViewhHolder extends RecyclerView.ViewHolder {
        TextView tv_classID,tv_className, tv_classTime,tv_classFee;
        LinearLayout class_recycle_list_layout;

        public MyViewhHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);


            tv_classID = itemView.findViewById(R.id.class_tv_recycle_list_classID);
            tv_className = itemView.findViewById(R.id.class_tv_recycle_list_className);
            tv_classTime = itemView.findViewById(R.id.class_tv_recycle_list_classTime);
            tv_classFee = itemView.findViewById(R.id.class_tv_recycle_list_classFee);

            class_recycle_list_layout = itemView.findViewById(R.id.class_layout_recycle_list_linearLayout);
        }
    }
}
