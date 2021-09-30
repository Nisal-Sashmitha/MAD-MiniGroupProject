package com.example.privatetutorplanner;

import android.view.View;

import androidx.test.rule.ActivityTestRule;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentStudentSearchTest {
    @Rule
    public ActivityTestRule<StudentStudentSearch> mActivityTestRule =
            new ActivityTestRule<StudentStudentSearch>(StudentStudentSearch.class);
    private StudentStudentSearch mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){
        View view = mActivity.findViewById(R.id.studnt_main_title1);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }


}