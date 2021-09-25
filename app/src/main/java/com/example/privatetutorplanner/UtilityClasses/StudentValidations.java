package com.example.privatetutorplanner.UtilityClasses;

public class StudentValidations {
    public boolean phoneNoValidator(String phoneNo){
        if (phoneNo.matches("[0-9]{10}"))
            return true ;
        else
            return false;

    }
}
