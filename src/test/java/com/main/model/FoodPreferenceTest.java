package com.main.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class FoodPreferenceTest {

    @Test
    public void getName() {
        FoodPreference preference = FoodPreference.veggie;
        String expectedName = "veggie";
        String actualName = preference.name();
        assertEquals(expectedName, actualName);
        System.out.println(actualName);
    }

    @Test
    public void fromName() {
        String name = "vegan";
        FoodPreference expectedPreference = FoodPreference.vegan;
        FoodPreference actualPreference = FoodPreference.fromName(name);
        assertEquals(expectedPreference, actualPreference);
        System.out.println(actualPreference);
    }

    @Test
    public void values() {
        FoodPreference[] expectedValues = {FoodPreference.none, FoodPreference.meat, FoodPreference.veggie, FoodPreference.vegan};
        FoodPreference[] actualValues = FoodPreference.values();
        assertArrayEquals(expectedValues, actualValues);
        System.out.println(actualValues);
    }

    @Test
    public void valueOf() {
        String name = "meat";
        FoodPreference expectedPreference = FoodPreference.meat;
        FoodPreference actualPreference = FoodPreference.valueOf(name);
        assertEquals(expectedPreference, actualPreference);
        System.out.println(actualPreference);
    }
}
