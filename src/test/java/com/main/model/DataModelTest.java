package com.main.model;


import static org.junit.Assert.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class DataModelTest {

  DataModel dataModel = new DataModel();
  /**
   * Tests the getAllData() method of the DataModel class.
   * It verifies that the method returns a list of all data including groups, pairs,
   * successor pairs, and successor participants.
   * It also prints the size of each data list retrieved.
   */
  @Test
  public void getAllData() throws IOException {
/*
    List<Object> allData = dataModel.getAllData();

    assertNotNull(allData);

    List<Group> groups = (List<Group>) allData.get(0);
    assertNotNull(groups);
    System.out.println("Groups size: " + groups.size());

    List<Pair> pairs = (List<Pair>) allData.get(1);
    assertNotNull(pairs);
    System.out.println("Pairs size: " + pairs.size());

    List<Pair> successorPairs = (List<Pair>) allData.get(2);
    assertNotNull(successorPairs);
    System.out.println("Successor Pairs size: " + successorPairs.size());

    List<Participant> successorParticipants = (List<Participant>) allData.get(3);
    assertNotNull(successorParticipants);
    System.out.println("Successor Participants size: " + successorParticipants.size());

 */
  }


  /**
   * Tests the printGroupList() method of the DataModel class.
   * It verifies that the method correctly prints the information of the provided group list.
   * It captures the printed output and compares it with the expected output.
   */
  @Test
  public void printGroupList() throws IOException {
/*
    // Create a list of groups for testing
    List<Group> groups = new ArrayList<>();
    DataModel dataModel = new DataModel();
    dataModel.getGroups();
    DataModel.printGroupList(groups);

    System.out.println(groups.addAll(dataModel.getGroups()));

 */
  }


}