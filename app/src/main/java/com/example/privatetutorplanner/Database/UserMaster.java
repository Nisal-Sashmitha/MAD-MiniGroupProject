package com.example.privatetutorplanner.Database;

import android.provider.BaseColumns;

public class UserMaster {
    private UserMaster(){}


    public static class Assignment implements BaseColumns{
        public static final String TABLE_NAME="Assignment";
        public static final String COLUMN_NAME_ASSIGNID="ID";
        public static final String COLUMN_NAME_TITLE="Title";
        public static final String COLUMN_NAME_MODULENAME="Module_Name";
        public static final String COLUMN_NAME_Q="Num_Questions";
        public static final String COLUMN_NAME_MARKS="Marks";
        public static final String COLUMN_NAME_DATE="Date";

    }


    public static class Student implements BaseColumns{
        public static final String TABLE_NAME="Student";
        public static final String COLUMN_NAME_STUDENTID="studentID";
        public static final String COLUMN_NAME_NAME="name";
        public static final String COLUMN_NAME_DATE_OF_BIRTH="dateOfBirth";
        public static final String COLUMN_NAME_CONTACTNO="contactNo";
        public static final String COLUMN_NAME_ADDRESS="address";


    }

    public static class StudentClass implements BaseColumns{
        public static final String TABLE_NAME="StudentClass";
        public static final String COLUMN_NAME_STUDENTID="StudentID";
        public static final String COLUMN_NAME_CLASSID="ClassID";
        public static final String COLUMN_NAME_LAST_PAYMENT_MONTH="lastPaymentMonth";
        public static final String COLUMN_NAME_LAST_PAYMENT_AMOUNT="lastPaymentAmount";


    }



    public static class Class implements BaseColumns {

        public static final String TABLE_NAME = "Class";
        public static final String COLUMN_NAME_CLASSID = "classID";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DAY = "day";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_MONTHLY_FEE = "monthlyFee";
    }

    //Module Table
    public static class Module implements BaseColumns{
        public static final String TABLE_NAME="module";
        public static final String COLUMN_NAME_MODULEID="moduleID";
        public static final String COLUMN_NAME_MODULENAME="name";

    }

}
