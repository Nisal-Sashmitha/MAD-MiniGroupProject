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

}
