package com.example.privatetutorplanner.ServiceClasses;

import com.example.privatetutorplanner.ModalClasses.Class;
import com.example.privatetutorplanner.ModalClasses.StudentClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StudentCount {

    ArrayList<Class> class_s;
    ArrayList<StudentClass> students;

    Map<Integer,Integer> map=new HashMap<Integer,Integer>();
    Map<Integer,Double> map2=new HashMap<Integer,Double>();

    private int count=0;

    public StudentCount(ArrayList<Class> class_s, ArrayList<StudentClass> students) {
        this.class_s = class_s;
        this.students = students;
    }

    //Number of students per class
    public HashMap<Integer,Integer> countStudent(){
        int i=students.size();
        int y=class_s.size();
        for (int j=0;j<y;j++){
            count=0;
            for (int z=0;z<i;z++){
                if(class_s.get(j).getClassID()==students.get(z).getClassID()){
                    count++;

                }
                
            }
            map.put(class_s.get(j).getClassID(),count);
            
        }
        return (HashMap<Integer,Integer>)map;
    }
    //Fee per class
    public HashMap<Integer,Double> totClassFee(Map<Integer,Integer> count,ArrayList<Class> cls){
        int c=cls.size();
        Double totfee=0.0;

        
        for(int z=0;z<c;z++){
            totfee=0.0;
            for (Map.Entry<Integer,Integer> result: count.entrySet()){
                if(result.getKey().equals(cls.get(z).getClassID())){
                    int fee=result.getValue();
                   totfee= cls.get(z).getClassFee()* fee;
                }
                map2.put(cls.get(z).getClassID(),totfee);
            }
        }
        return (HashMap<Integer,Double>)map2;
    }
    
}
