package com.example.privatetutorplanner.UtilityClasses;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentValidationsTest {
    private StudentValidations studentValidations;
    @Before
    public void setUp() throws Exception {
        studentValidations = new StudentValidations();
    }


    @Test
    public void testPhoneNoValidatorForTrue() {
        boolean flag =studentValidations.phoneNoValidator("0778966555");
        assertEquals(true  ,flag);
    }
    @Before
    public void setUp2() throws Exception {
        studentValidations = new StudentValidations();
    }


    @Test
    public void testPhoneNoValidatorForFalse() {
        boolean flag =studentValidations.phoneNoValidator("077896");
        assertEquals(false  ,flag);
    }
}