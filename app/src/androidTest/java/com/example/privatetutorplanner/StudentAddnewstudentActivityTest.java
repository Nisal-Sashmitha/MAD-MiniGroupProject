package com.example.privatetutorplanner;


import android.view.View;

import androidx.test.rule.ActivityTestRule;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentAddnewstudentActivityTest {

    @Rule
    public ActivityTestRule<StudentAddnewstudentActivity> StudentAddnewstudentActivityTestRule = new ActivityTestRule<StudentAddnewstudentActivity>(StudentAddnewstudentActivity.class);
    private StudentAddnewstudentActivity studentAddnewstudentActivity = null;
    @Before
    public void setUp() throws Exception {
        studentAddnewstudentActivity = StudentAddnewstudentActivityTestRule.getActivity();
    }
    @Test
    public void validationTest(){
        View view = studentAddnewstudentActivity.findViewById(R.id.studnt_main_title1);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        studentAddnewstudentActivity=null;
    }

}