package com.main.model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;

public class KitchenTest {

  /**
   * Test case for the Kitchen constructor.
   * Verifies that the constructor correctly initializes the Kitchen object with the provided values.
   */
  @Test
  public void testKitchenConstructor() {
    // Test data
    double kitchenStory = 2.5;
    double longitude = 40.1234;
    double latitude = -74.5678;

    // Create a Kitchen object using the constructor
    Kitchen kitchen = new Kitchen(kitchenStory, longitude, latitude);

    // Verify that the object is initialized correctly
    assertEquals(kitchenStory, kitchen.getKitchenStory(), 0.001);
    assertEquals(longitude, kitchen.getLongitude(), 0.001);
    assertEquals(latitude, kitchen.getLatitude(), 0.001);

    // Print the results
    System.out.println("Kitchen Constructor Test:");
    System.out.println("Kitchen Story: " + kitchen.getKitchenStory());
    System.out.println("Longitude: " + kitchen.getLongitude());
    System.out.println("Latitude: " + kitchen.getLatitude());
  }
  /**
   * Test case for the Kitchen constructor that accepts string parameters for kitchenStory, longitude, and latitude.
   */
  @Test
  public void testKitchenConstructorWithStringParams() {
    // Create a Kitchen object with string parameters
    Kitchen kitchen = new Kitchen("2.5", "10.123", "20.456");

    // Verify the values are parsed correctly
    assertEquals(2.5, kitchen.getKitchenStory(), 0.001);
    assertEquals(10.123, kitchen.getLongitude(), 0.001);
    assertEquals(20.456, kitchen.getLatitude(), 0.001);

    // Create a Kitchen object with empty string parameters
    Kitchen kitchenEmpty = new Kitchen("", "", "");

    // Verify that the default values are assigned
    assertEquals(0.0, kitchenEmpty.getKitchenStory(), 0.001);
    assertEquals(0.0, kitchenEmpty.getLongitude(), 0.001);
    assertEquals(0.0, kitchenEmpty.getLatitude(), 0.001);

    // Print the results
    System.out.println("Kitchen Constructor Test (String params):");
    System.out.println("Kitchen Story : " + kitchen.getKitchenStory());
    System.out.println("Longitude : " + kitchen.getLongitude());
    System.out.println("Latitude : " + kitchen.getLatitude());
    System.out.println("_________________________________");
    System.out.println("The inputs are empty");
    System.out.println("Kitchen Story (empty): " + kitchenEmpty.getKitchenStory());
    System.out.println("Longitude (empty): " + kitchenEmpty.getLongitude());
    System.out.println("Latitude (empty): " + kitchenEmpty.getLatitude());
    System.out.println("Kitchen constructor test passed");
    System.out.println("--------------------------------------------");
  }
}