package com.main.model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import javafx.scene.control.Control;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import com.main.model.Group;

public class GroupTest {

  Group group = new Group();
  Participant participant1 =  new Participant("1345", "Ahmad ", FoodPreference.none, 25, Gender.MALE, KitchenStatus.no, new Kitchen("1", "2", "3"));
  Participant participant2 =  new Participant("1445", "Nebal ", FoodPreference.meat, 27, Gender.FEMALE, KitchenStatus.yes, new Kitchen("1", "2", "3"));
  Participant participant3 =  new Participant("1225", "David ", FoodPreference.vegan, 22, Gender.MALE, KitchenStatus.yes, new Kitchen("2", "3", "4"));
  Participant participant4 =  new Participant("1110", "Sara", FoodPreference.veggie, 20, Gender.MALE, KitchenStatus.no, new Kitchen("1", "2", "3"));

  Pair pair1 = new Pair(participant1, participant2);
  Pair pair2 = new Pair(participant3, participant4);
  Pair pair3 = null;



  /** testGroupConstructorWithName() Assert that the name of the group is set correctly
   */
  @Test
  public void testGroupConstructorWithName() {
    String groupName = "TestGroup";    // Create a group with a specified name
    Group group = new Group(groupName);
    assertEquals(groupName, group.getName());    // Assert that the name of the group is set correctly
    System.out.println("Group created with name: " + groupName);
    System.out.println("---------------------------------------------------------------------------------");
  }


  /**The test ensures that the added pair is contained in the list of pairs and prints a message confirming the addition.*/
  @Test
  public void addPair(){

    group.addPair(pair1);
    List<Pair<Control,Control> > pairs = group.getPairs();
    assertTrue((pairs).contains(pair1));
    System.out.println("new Pair added:  " + pair1);
  }

  /** containsPair() method returns true if the group contains the specificd pair, otherwise false. */
  @Test
  public void containsPair() {

    Group group = new Group("TestGroup");        // Create a new Group object
    // Create some pairs
    Pair pair1 = new Pair(new Participant("1", "John", FoodPreference.none, 25, Gender.MALE, KitchenStatus.yes, null),
        new Participant("2", "Smith", FoodPreference.none, 25, Gender.MALE, KitchenStatus.yes, null));
    Pair pair2 = new Pair(new Participant("3", "Alice", FoodPreference.vegan, 30, Gender.FEMALE, KitchenStatus.yes, null),
        new Participant("4", "Bob", FoodPreference.veggie, 28, Gender.MALE, KitchenStatus.yes, null));
    Pair pair3 = new Pair(new Participant("5", "Emma", FoodPreference.meat, 27, Gender.FEMALE, KitchenStatus.yes, null),
        new Participant("6", "David", FoodPreference.none, 29, Gender.MALE, KitchenStatus.yes, null));

    // Add pair1 and pair2 to the group
    group.addPair(pair1);
    group.addPair(pair2);
    Assertions.assertTrue(group.containsPair(pair1));  // Test pair1, which is already in the group
    System.out.println("Pair1 is present in the group.");

    assertTrue( group.containsPair(pair2));  // Test pair2, which is already in the group
    System.out.println("Pair2 is present in the group.");

    assertFalse("The group should not contain pair3", group.containsPair(pair3));  // Test pair3, which is not in the group
    System.out.println("Pair3 is not present in the group.");
    System.out.println("---------------------------------------------------------------");

    System.out.println("containsPair test completed successfully.");
  }
}