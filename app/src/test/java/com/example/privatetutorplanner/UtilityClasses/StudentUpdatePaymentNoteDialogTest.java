package com.example.privatetutorplanner.UtilityClasses;

import com.example.privatetutorplanner.Database.UserMaster;
import com.example.privatetutorplanner.ModalClasses.StudentClass;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentUpdatePaymentNoteDialogTest {
    private StudentUpdatePaymentNoteDialog studentUpdatePaymentNoteDialog;
    @Before
    public void setUp() throws Exception {
        StudentClass stdcls = new StudentClass();

        studentUpdatePaymentNoteDialog = new StudentUpdatePaymentNoteDialog(stdcls);
    }
    @Test
    public void checkFee() {
        double result = studentUpdatePaymentNoteDialog.checkFee("");
        assertEquals(0.0  ,result,0.1);
    }
    @After
    public void tearDown() throws Exception {
    }


}