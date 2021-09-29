package com.example.privatetutorplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.privatetutorplanner.ModalClasses.Assignment;

import java.util.ArrayList;
import java.util.List;

public class asssignment_ret_adapt1 extends RecyclerView.Adapter<asssignment_ret_adapt1.MyViewHolder> {
    private Context context;
    private Activity activity;

    private ArrayList<String> module_name;
    private ArrayList<Assignment> asign;


    asssignment_ret_adapt1(Activity act, Context con, ArrayList module_name,ArrayList assign){
        this.activity = act;
        this.context = con;
        this.module_name=module_name;
        this.asign=assign;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=null;
        try{
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.asssignment_ret_adapt1, parent, false);
        }catch(Exception e){
            Toast.makeText(activity,"Error oncreate ret_adapt1:"+e, Toast.LENGTH_LONG).show();
        }
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position ) {
        //Only Module Name is assigned to Textview, which is obtained via HashSet
       holder.modname.setText(String.valueOf(module_name.get(position)));

        try {
            //Remaining assignment details are sent to next adapter
            assign_childAdpt child = new assign_childAdpt( activity,
                                    context,
                                    asign,
                                String.valueOf(module_name.get(position)));

            holder.rv.setAdapter(child);
            holder.rv.setLayoutManager(new LinearLayoutManager(activity));
            holder.rv.setNestedScrollingEnabled(true);
        }catch(Exception e){
            Toast.makeText(activity,"Error ret_adapt1 61 :"+e, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public int getItemCount() {
        return module_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView modname;
        RecyclerView rv;
        ImageButton add;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            modname=itemView.findViewById(R.id.adapt_tvMod);
            rv=itemView.findViewById(R.id.recycle_childAssign);

            add=itemView.findViewById(R.id.addbtn_assign);

            add.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity,Assignment_add.class);
                    activity.startActivity(intent);
                }
            });

        }

    }
}