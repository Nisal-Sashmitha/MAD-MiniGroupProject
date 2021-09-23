package com.example.privatetutorplanner.ModalClasses;

public class Student {
    private int studentID;
    private String name;
    private String dateOfBirth;
    private String address;
    private String contactNo;

    public String getName() {
        return name;
    }

    public int getStudentID() {
        return studentID;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNo() {
        return contactNo;
    }


    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }



    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public void setName(String name) {
        this.name = name;
    }
}
