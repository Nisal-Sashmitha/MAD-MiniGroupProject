package com.example.privatetutorplanner.Database;

import android.provider.BaseColumns;

public class UserMaster {
    private UserMaster(){}

    public static class Student implements BaseColumns{
        public static final String TABLE_NAME="Student";
        public static final String COLUMN_NAME_STUDENTID="studentID";
        public static final String COLUMN_NAME_NAME="name";
        public static final String COLUMN_NAME_DATE_OF_BIRTH="dateOfBirth";
        public static final String COLUMN_NAME_CONTACTNO="contactNo";
        public static final String COLUMN_NAME_ADDRESS="address";


    }
    public static class LastPaymentTable implements BaseColumns{
        public static final String TABLE_NAME="lastPayment";
        public static final String COLUMN_NAME_STUDENTID="studentID";
        public static final String COLUMN_NAME_CLASSID="classID";
        public static final String COLUMN_NAME_LAST_PAYMENT_MONTH ="lastPaymentMonth";
        public static final String COLUMN_NAME_LAST_PAYMENT_AMMOUNT ="lastPaymentAmmount";

    }
}