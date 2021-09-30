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

import com.example.privatetutorplanner.Edit_Module;
import com.example.privatetutorplanner.R;

import java.util.ArrayList;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.MyViewHolder> {

    private Context context;
    private ArrayList moduleID,moduleName ;
    Activity activity;


    public ModuleAdapter(Activity activity,
                         Context context,
                         ArrayList moduleID,
                         ArrayList moduleName){
         this.activity=activity;
         this.context = context;
         this.moduleID = moduleID;
         this.moduleName = moduleName;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.module_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

          holder.moduleName.setText(String.valueOf(moduleName.get(position)));
          holder.modulerowlayout.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                   Intent intent = new Intent(context, Edit_Module.class);
                  intent.putExtra("id",String.valueOf(moduleID.get(position)));
                  intent.putExtra("name",String.valueOf(moduleName.get(position)));
                  activity.startActivityForResult(intent,1);
              }
          });
    }

    @Override
    public int getItemCount() {
        return moduleID.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView moduleName;
        // TextView moduleId;
        LinearLayout modulerowlayout;

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            moduleName = itemView.findViewById(R.id.moduIetext);
            modulerowlayout = itemView.findViewById(R.id.modulerowlayout);
        }
    }
}
