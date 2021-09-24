package com.example.privatetutorplanner.UtilityClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.privatetutorplanner.ModalClasses.StudentClass;
import com.example.privatetutorplanner.R;

import org.jetbrains.annotations.NotNull;
import com.example.privatetutorplanner.ModalClasses.Class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MyViewhHolder>{

    Context context;
    ArrayList<Class> class_arryList;
    ArrayList<StudentClass> students;
    Map<Integer,Integer> Countmap;
    HashMap<Integer,Double> Feemap;

    public MainPageAdapter(Context context, ArrayList<Class>class_arryList,Map<Integer,Integer> Countmap, HashMap<Integer,Double> Feemap  ){

        this.context = context;
        this.class_arryList = class_arryList;
        this.students=students;
        this.Countmap=Countmap;
        this.Feemap=Feemap;
    }


    @NonNull
    @NotNull
    @Override
    public MainPageAdapter.MyViewhHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_page_recycle_item,parent,false);
        return new MainPageAdapter.MyViewhHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MainPageAdapter.MyViewhHolder holder, int position) {
        holder.tv_name.setText(class_arryList.get(position).getClassName());
        holder.tv_fee.setText(class_arryList.get(position).getClassFee() +"");

          for(Map.Entry cm:Countmap.entrySet()) {
            if(cm.getKey().equals(class_arryList.get(position).getClassID())){

                holder.count.setText(cm.getValue().toString());
            }
          }
        for(Map.Entry fm:Feemap.entrySet()) {
            if(fm.getKey().equals(class_arryList.get(position).getClassID())){

                holder.tot_fee.setText("Rs:"+fm.getValue().toString());
            }
        }


    }

    @Override
    public int getItemCount() {
        return class_arryList.size();
    }

    public class MyViewhHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_fee,tot_fee,count;
        LinearLayout linearLayout_recycle_main_item;

        public MyViewhHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_className_mainPage);
            tv_fee = itemView.findViewById(R.id.tv_classFee_mainPage);
            tot_fee=itemView.findViewById(R.id.tv_classTotalAmount_mainPage);
            count=itemView.findViewById(R.id.tv_studentCount_mainPage);

            linearLayout_recycle_main_item = itemView.findViewById(R.id.linearLayout_mainpage);
        }
    }
}
