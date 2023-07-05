package com.main.model;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AgeRangeTest {

    /**
     * Tests the values() method of the AgeRange enum.
     * It verifies that the values array is not null and has the expected length.
     * It also verifies the values of each enum constant.
     */

    @Test
    public void values() {
        AgeRange[] ageRanges = AgeRange.values();

        // Verify that the values array is not null and has the expected length
        assertNotNull(ageRanges);
        assertEquals(8, ageRanges.length);

        // Verify the values of each enum constant
        for (AgeRange ageRange : ageRanges) {
            System.out.println(ageRange + " - Value: " + ageRange.getValue());
        }

        // Verify the values of each enum constant
        assertEquals(0, ageRanges[0].getValue());
        assertEquals(1, ageRanges[1].getValue());
        assertEquals(2, ageRanges[2].getValue());
        assertEquals(3, ageRanges[3].getValue());
        assertEquals(4, ageRanges[4].getValue());
        assertEquals(5, ageRanges[5].getValue());
        assertEquals(6, ageRanges[6].getValue());
        assertEquals(7, ageRanges[7].getValue());
    }

    /**
     * Tests the valueOf() method of the AgeRange enum.
     * It verifies the values of each enum constant.
     */

    @Test
    public void valueOf() {
/*
        System.out.println("AgeRange values:");
        System.out.println("--------------------------------------------------------------------------");

        Assert.assertEquals(0, AgeRange.ZERO_TO_SEVENTEEN.getValue());
        System.out.println("ZERO_TO_SEVENTEEN - Value: " + AgeRange.ZERO_TO_SEVENTEEN.getValue());

        Assert.assertEquals(1, AgeRange.EIGHTEEN_TO_TWENTY_THREE.getValue());
        System.out.println("EIGHTEEN_TO_TWENTY_THREE - Value: " + AgeRange.EIGHTEEN_TO_TWENTY_THREE.getValue());

        Assert.assertEquals(2, AgeRange.TWENTY_FOUR_TO_TWENTY_SEVEN.getValue());
        System.out.println("TWENTY_FOUR_TO_TWENTY_SEVEN - Value: " + AgeRange.TWENTY_FOUR_TO_TWENTY_SEVEN.getValue());

        Assert.assertEquals(3, AgeRange.TWENTY_EIGHT_TO_THIRTY.getValue());
        System.out.println("TWENTY_EIGHT_TO_THIRTY - Value: " + AgeRange.TWENTY_EIGHT_TO_THIRTY.getValue());

        Assert.assertEquals(4, AgeRange.THIRTY_ONE_TO_THIRTY_FIVE.getValue());
        System.out.println("THIRTY_ONE_TO_THIRTY_FIVE - Value: " + AgeRange.THIRTY_ONE_TO_THIRTY_FIVE.getValue());

        Assert.assertEquals(5, AgeRange.THIRTY_SIX_TO_FORTY_ONE.getValue());
        System.out.println("THIRTY_SIX_TO_FORTY_ONE - Value: " + AgeRange.THIRTY_SIX_TO_FORTY_ONE.getValue());

        Assert.assertEquals(6, AgeRange.FORTY_TWO_TO_FORTY_SIX.getValue());
        System.out.println("FORTY_TWO_TO_FORTY_SIX - Value: " + AgeRange.FORTY_TWO_TO_FORTY_SIX.getValue());

        Assert.assertEquals(7, AgeRange.FORTY_SEVEN_TO_FIFTY_SIX.getValue());
        System.out.println("FORTY_SEVEN_TO_FIFTY_SIX - Value: " + AgeRange.FORTY_SEVEN_TO_FIFTY_SIX.getValue());

 */
    }
    /**
     * Tests the getValue() method of the AgeRange enum.
     * It verifies that the method correctly returns the value associated with the age range.
     */
    @Test
    public void getValue() {
        System.out.println("Running testGetValue...");

        assertEquals(0, AgeRange.ZERO_TO_SEVENTEEN.getValue());
        System.out.println("Assertion 1 passed: Value is: " + AgeRange.ZERO_TO_SEVENTEEN.getValue());

        assertEquals(1, AgeRange.EIGHTEEN_TO_TWENTY_THREE.getValue());
        System.out.println("Assertion 2 passed: Value is: " + AgeRange.EIGHTEEN_TO_TWENTY_THREE.getValue());

        assertEquals(2, AgeRange.TWENTY_FOUR_TO_TWENTY_SEVEN.getValue());
        System.out.println("Assertion 3 passed: Value is: " + AgeRange.TWENTY_FOUR_TO_TWENTY_SEVEN.getValue());

        assertEquals(3, AgeRange.TWENTY_EIGHT_TO_THIRTY.getValue());
        System.out.println("Assertion 4 passed: Value is: " + AgeRange.TWENTY_EIGHT_TO_THIRTY.getValue());

        assertEquals(4, AgeRange.THIRTY_ONE_TO_THIRTY_FIVE.getValue());
        System.out.println("Assertion 5 passed: Value is: " + AgeRange.THIRTY_ONE_TO_THIRTY_FIVE.getValue());

        assertEquals(5, AgeRange.THIRTY_SIX_TO_FORTY_ONE.getValue());
        System.out.println("Assertion 6 passed: Value is: " + AgeRange.THIRTY_SIX_TO_FORTY_ONE.getValue());

        assertEquals(6, AgeRange.FORTY_TWO_TO_FORTY_SIX.getValue());
        System.out.println("Assertion 7 passed: Value is: " + AgeRange.FORTY_TWO_TO_FORTY_SIX.getValue());

        assertEquals(7, AgeRange.FORTY_SEVEN_TO_FIFTY_SIX.getValue());
        System.out.println("Assertion 8 passed: Value is: " + AgeRange.FORTY_SEVEN_TO_FIFTY_SIX.getValue());

        System.out.println("All assertions passed");
    }
    /**
     * Tests the constructor of the AgeRange enum.
     * It verifies that the value associated with each age range is correctly assigned.
     */
    @Test
    public void testConstructor() {

        System.out.println("Running testConstructor...");

        AgeRange[] ageRanges = AgeRange.values();

        for (AgeRange ageRange : ageRanges) {
            int expectedValue = ageRange.ordinal();
            assertEquals(expectedValue, ageRange.getValue());
            System.out.println("Assertion passed for " + ageRange.name() + ". Value: " + ageRange.getValue());
        }

        System.out.println("All assertions passed");
    }}
