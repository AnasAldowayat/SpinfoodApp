package com.main.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.Test;

public class PairTest {

  Pair pair = new Pair();

  @Test
  public void testPairConstructor() {
    // Create participants
    Participant participant1 = new Participant("1", "John", FoodPreference.none, 25, Gender.MALE, KitchenStatus.yes, null);
    Participant participant2 = new Participant("2", "Smith", FoodPreference.none, 25, Gender.MALE, KitchenStatus.yes, null);

    // Create a pair using the constructor
    pair = new Pair<>(participant1, participant2);

    // Assertions
    assertNotNull(pair);
    System.out.println("Assertion 1: Pair object is not null.");
    assertEquals(participant1, pair.getParticipant1());
    System.out.println("Assertion 2: Participant 1 is set correctly.");

    assertEquals(participant2, pair.getParticipant2());
    System.out.println("Assertion 3: Participant 2 is set correctly.");

    assertEquals(FoodPreference.meat, pair.calculateMainFoodPreference());
    System.out.println("Assertion 4: Main food preference is set correctly.");
  }

  /**
   * Tests the calculateMainFoodPreference() method of the Pair class.
   * Verifies that the main food preference of the pair is calculated correctly.
   * We tested all possible combinations of food preference.
   */
  @Test
  public void calculateMainFoodPreference() {

    Participant participant1 = new Participant("1", "John", FoodPreference.none, 25, Gender.MALE, KitchenStatus.yes, null);
    Participant participant2 = new Participant("2", "Smith", FoodPreference.none, 20, Gender.MALE, KitchenStatus.yes, null);

    Participant participant3 = new Participant("3", "John", FoodPreference.vegan, 25, Gender.MALE, KitchenStatus.yes, null);
    Participant participant4 = new Participant("4", "Smith", FoodPreference.vegan, 20, Gender.MALE, KitchenStatus.yes, null);

    Participant participant5 = new Participant("5", "John", FoodPreference.veggie, 25, Gender.MALE, KitchenStatus.yes, null);
    Participant participant6 = new Participant("6", "Smith", FoodPreference.veggie, 20, Gender.MALE, KitchenStatus.yes, null);

    Participant participant7 = new Participant("7", "John", FoodPreference.meat, 25, Gender.MALE, KitchenStatus.yes, null);
    Participant participant8 = new Participant("8", "Smith", FoodPreference.meat, 20, Gender.MALE, KitchenStatus.yes, null);

    Participant participant9 = new Participant("9", "John", FoodPreference.none, 25, Gender.MALE, KitchenStatus.yes, null);
    Participant participant10 = new Participant("10", "Smith", FoodPreference.meat, 20, Gender.MALE, KitchenStatus.yes, null);

    Participant participant11 = new Participant("9", "John", FoodPreference.vegan, 25, Gender.MALE, KitchenStatus.yes, null);
    Participant participant12 = new Participant("10", "Smith", FoodPreference.veggie, 20, Gender.MALE, KitchenStatus.yes, null);
    // Case 1: none  & none
    pair = new Pair(participant1, participant2);
    FoodPreference expectedFoodPreferenceP1P2 = FoodPreference.meat;
    FoodPreference actualFoodPreferenceP1P2 = pair.calculateMainFoodPreference();
    assertEquals(expectedFoodPreferenceP1P2, actualFoodPreferenceP1P2);
    System.out.println("Main food Preference for Participant1 & Participant2 is:  " + actualFoodPreferenceP1P2);
    //   Case 2: vegan  & vegan
    pair = new Pair(participant3, participant4);
    FoodPreference expectedFoodPreferenceP3P4 = FoodPreference.vegan;
    FoodPreference actualFoodPreferenceP3P4 = pair.calculateMainFoodPreference();
    assertEquals(expectedFoodPreferenceP3P4, actualFoodPreferenceP3P4);
    System.out.println("Main food Preference for Participant3 & Participant4 is:  " + actualFoodPreferenceP3P4);
    // Case 3: veggie  & veggie
    pair = new Pair(participant5, participant6);
    FoodPreference expectedFoodPreferenceP5P6 = FoodPreference.veggie;
    FoodPreference actualFoodPreferenceP5P6 = pair.calculateMainFoodPreference();
    assertEquals(expectedFoodPreferenceP5P6, actualFoodPreferenceP5P6);
    System.out.println("Main food Preference for Participant5 & Participant6 is:  " + actualFoodPreferenceP5P6);
    // Case 4: meat  & meat
    pair = new Pair(participant7, participant8);
    FoodPreference expectedFoodPreferenceP7P8 = FoodPreference.meat;
    FoodPreference actualFoodPreferenceP7P8= pair.calculateMainFoodPreference();
    assertEquals(expectedFoodPreferenceP7P8, actualFoodPreferenceP7P8);
    System.out.println("Main food Preference for Participant7 & Participant8 is:  " + actualFoodPreferenceP7P8);
    // Case 5: none & meat ||  meat & none
    pair = new Pair(participant9, participant10);
    FoodPreference expectedFoodPreferenceP9P10 = FoodPreference.meat;
    FoodPreference actualFoodPreferenceP9P10= pair.calculateMainFoodPreference();
    assertEquals(expectedFoodPreferenceP9P10, actualFoodPreferenceP9P10);
    System.out.println("Main food Preference for Participant9 & Participant10 is:  " + actualFoodPreferenceP9P10);
    // Case6: vegan & veggie ||  veggie & vegan
    pair = new Pair(participant11, participant12);
    FoodPreference expectedFoodPreferenceP11_P12 = FoodPreference.vegan;
    FoodPreference actualFoodPreferenceP11_P12= pair.calculateMainFoodPreference();
    assertEquals(expectedFoodPreferenceP11_P12, actualFoodPreferenceP11_P12);
    System.out.println("Main food Preference for Participant11 & Participant12 is:  " + actualFoodPreferenceP11_P12);
  }
  @Test
  public void containsParticipant() {
    }

  @Test
  public void testCalculateMainFoodPreference() {
    }

  @Test
  public void getAllPairs() {

    }
}