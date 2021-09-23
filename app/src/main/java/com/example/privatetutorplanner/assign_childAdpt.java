package com.example.privatetutorplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.privatetutorplanner.Database.DBHelper;
import com.example.privatetutorplanner.ModalClasses.Assignment;

import java.util.ArrayList;
import java.util.List;

public class assign_childAdpt extends RecyclerView.Adapter<assign_childAdpt.MyViewHolder>  {


    private Activity activity;
    private Context cont;
    private ArrayList<Assignment> asign;
    private String current_name;


    assign_childAdpt(Activity activity,Context con,ArrayList assign,String current_name){

        this.asign=assign;
        this.cont=con;
        this.activity=activity;
        this.current_name=current_name;

    }


    @NonNull
    @Override
    public assign_childAdpt.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.assign_child_adpt, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position ) {
        try{
        Assignment as3 = asign.get(position);
            Log.i("Module current :",current_name);
            Log.i("Module Check current :",as3.getModulename());
            boolean val=as3.getModulename().equals(current_name);
        if(as3.getModulename().equals(current_name)){

                holder.name.setText(as3.getTitle());
                holder.ques.setText("Q : "+Integer.toString(as3.getQu()));
                holder.mark.setText("M :"+Integer.toString(as3.getMark())+"%");
                holder.date.setText(as3.getDate());

        }else{
            holder.itemView.setVisibility(View.GONE);
            holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        }


        }
        catch(Exception e){
            Toast.makeText(activity
                    ,"Error Child adapt 60:"+e, Toast.LENGTH_LONG).show();
        }
    }



    @Override
    public int getItemCount() {

        return asign.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,ques,mark,date;
        ImageButton deletebtn, updatebtn;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.AssignName_tv);
            ques=itemView.findViewById(R.id.AssignQ_tv);
            mark=itemView.findViewById(R.id.AssignMark_tv);
            date=itemView.findViewById(R.id.AssignDate_tv);

            deletebtn=itemView.findViewById(R.id.assign_del);
            updatebtn=itemView.findViewById(R.id.Assign_Up);

            deletebtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Assignment as3 = asign.get(position);
                    confirmDelete(as3.getId(),as3.getTitle());
                }
            });

            updatebtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Assignment as3 = asign.get(position);
                    Intent intent = new Intent(activity, assign_update.class);
                    intent.putExtra("id", Integer.toString(as3.getId()));
                    intent.putExtra("title", as3.getTitle());
                    intent.putExtra("module", as3.getModulename());
                    intent.putExtra("qu", Integer.toString(as3.getQu()));
                    intent.putExtra("marks", Integer.toString(as3.getMark()));
                    intent.putExtra("date", as3.getDate());
                    activity.startActivity(intent);

                }
            });

        }

    }


    void confirmDelete(int id,String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(cont);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper db = new DBHelper(cont);
                db.deleteAssign(id);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


}