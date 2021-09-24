package com.example.privatetutorplanner.UtilityClasses;

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
import com.example.privatetutorplanner.ModalClasses.Class;

import java.util.ArrayList;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.MyViewhHolder>{

    Context context;
    ArrayList<Class> class_arryList;

    public MainPageAdapter(Context context, ArrayList<Class>class_arryList){

        this.context = context;
        this.class_arryList = class_arryList;
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
    }

    @Override
    public int getItemCount() {
        return class_arryList.size();
    }

    public class MyViewhHolder extends RecyclerView.ViewHolder {
        TextView tv_name,tv_fee;
        LinearLayout linearLayout_recycle_main_item;

        public MyViewhHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.tv_className_mainPage);
            tv_fee = itemView.findViewById(R.id.tv_classFee_mainPage);

            linearLayout_recycle_main_item = itemView.findViewById(R.id.linearLayout_mainpage);
        }
    }
}
