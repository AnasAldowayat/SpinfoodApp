package com.main.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import org.junit.Test;


//There is no Methods in Class KitchenStatus but I wrote a test for it anyway
public class KitchenStatusTest {

    /**
     * Tests the values() method of the KitchenStatus enum.
     * Verifies that the values() method returns an array containing the expected values.
     */
    @Test
    public void values() {
        KitchenStatus[] expectedValues = {KitchenStatus.yes, KitchenStatus.no, KitchenStatus.maybe};
        KitchenStatus[] actualValues = KitchenStatus.values();
        assertArrayEquals(expectedValues, actualValues);

        System.out.println("Actual values of the kitchen status are : " + Arrays.toString(actualValues));
    }

    /**
     * Tests the valueOf() method of the KitchenStatus enum.
     * Verifies that the valueOf() method correctly returns the corresponding enum constant based on the provided string.
     */
    @Test
    public void valueOf() {

        System.out.println("Testing valid input 'yes'...");
        KitchenStatus kitchenStatusYes = KitchenStatus.valueOf("yes");
        assertEquals(KitchenStatus.yes, kitchenStatusYes);
        System.out.println("Testing valid input 'no'...");
        KitchenStatus kitchenStatusNo = KitchenStatus.valueOf("no");
        assertEquals(KitchenStatus.no, kitchenStatusNo);
        System.out.println("Testing valid input 'maybe'...");
        KitchenStatus kitchenStatusMaybe = KitchenStatus.valueOf("maybe");
        assertEquals(KitchenStatus.maybe, kitchenStatusMaybe);
        System.out.println("testEnumValueOf completed successfully.");
    }
}
