package com.example.privatetutorplanner.ModalClasses;

public class Class {
    private String className;
    private String classDay;
    private String classTime;
    private double classFee;

    public void setClassName(String name){
        this.className = name;
    }
    public void setClassDay(String day){
        this.classDay = day;
    }
    public void setClassTime(String time){
        this.classTime = time;
    }
    public void setClassFee(Double fee){
        this.classFee= fee;
    }

    public double getClassFee() {
        return classFee;
    }

    public String getClassDay() {
        return classDay;
    }

    public String getClassName() {
        return className;
    }

    public String getClassTime() {
        return classTime;
    }
}
