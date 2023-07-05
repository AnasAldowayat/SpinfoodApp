package com.main.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GenderTest {

    @Test
    public void values() {
        Gender[] expectedValues = {Gender.FEMALE, Gender.MALE, Gender.OTHER};
        Gender[] actualValues = Gender.values();
        assertArrayEquals(expectedValues, actualValues);
        System.out.println(actualValues);
        System.out.println(expectedValues);
    }

    @Test
    public void valueOf() {
        String name = "MALE";
        Gender expectedGender = Gender.MALE;
        Gender actualGender = Gender.valueOf(name);
        assertEquals(expectedGender, actualGender);
        System.out.println(actualGender);
        System.out.println(expectedGender);
    }

}
