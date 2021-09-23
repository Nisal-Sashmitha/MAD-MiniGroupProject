package com.example.privatetutorplanner.ModalClasses;

public class StudentClass {
    private int StudentID;
    private int ClassID;
    private String lastPaymentMonth;
    private double lastPaymentAmount;
    private String ClassName;

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public int getClassID() {
        return ClassID;
    }

    public void setClassID(int classID) {
        ClassID = classID;
    }

    public String getLastPaymentMonth() {
        return lastPaymentMonth;
    }

    public void setLastPaymentMonth(String lastPaymentMonth) {
        this.lastPaymentMonth = lastPaymentMonth;
    }

    public double getLastPaymentAmount() {
        return lastPaymentAmount;
    }

    public void setLastPaymentAmount(double lastPaymentAmount) {
        this.lastPaymentAmount = lastPaymentAmount;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }
}
