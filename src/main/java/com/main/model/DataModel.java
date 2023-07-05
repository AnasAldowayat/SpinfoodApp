package com.main.model;

import com.main.controller.CreateGroupsClass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Control;

/**
 * This class represents the data model of the application. DEUTSCH: Diese Klasse stellt das
 * Datenmodell der Anwendung dar.
 */
public class DataModel {

  private List<Pair<Control, Control>> successorPairs;
  // private List<Participant> successorParticipants;
  private ObservableList<Participant> successorParticipants = FXCollections.observableArrayList();
  CreateGroupsClass createGroupsClass;

  public DataModel(CreateGroupsClass createGroupsClass) {
    this.createGroupsClass = createGroupsClass;
  }

  public DataModel() {
    this.createGroupsClass = new CreateGroupsClass();
  }

  /**
   * Gets the successor participants.
   *
   * @return the successor participants
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public List<Group> getGroups() throws IOException {
    // Ausgabe der Gruppen
    List<List<Pair<Control, Control>>> sortedPairs = CreateGroupsClass.sortPairsByFoodPreference();
    GroupList groupList = CreateGroupsClass.createGroupsFromSortedPairs(sortedPairs);
    List<Group> groupListAsList = new ArrayList<>(groupList.getGroups());
    printGroupList(groupListAsList);
    return groupListAsList;
  }

  /**
   * Gets the pairs.
   *
   *  @return the pairs
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public List<Pair<Control, Control>> getPairs() throws IOException {
    List<Pair<Control, Control>> pairs = Pair.getAllPairs().getPairedParticipants();
    System.out.println("pairs size: " + pairs.size() + "\n");
    return pairs;
  }

  public void setPairs(List<Pair<Control, Control>> pairs) {
  }

  /**
   * Gets the successor pairs.
   *
   * @return the successor pairs
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public List<Pair<Control, Control>> getSuccessorPairs() throws IOException {
    List<List<Pair<Control, Control>>> sortedPairs = CreateGroupsClass.sortPairsByFoodPreference();
    GroupList groupList = CreateGroupsClass.createGroupsFromSortedPairs(sortedPairs);
    successorPairs = groupList.getSuccessorPairs();
    System.out.println("successorPairs size: " + successorPairs.size() + "\n");
    return successorPairs;
  }

  /**
   * Gets the successor participants.
   *
   * @return the successor participants
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public List<Participant> getSuccessorParticipants() throws IOException {
    PairList pairList = CsvReader.readData("teilnehmerliste.csv");
    List<Participant> participants = pairList.getSuccessorParticipants();

    // Verwende die Benutzerwahl, um die geeignete Methode auszuwählen
    PairList newPairList =
        createGroupsClass.chooseCreatePairsMethod(createGroupsClass.getUserChoice(), participants);

    List<Participant> successorParticipants = newPairList.getSuccessorParticipants();
    System.out.println("SuccessorParticipants size: " + successorParticipants.size() + "\n");
    return successorParticipants;
  }



  /**
   * Prints the group list.
   *
   * @param groups the groups
   */
  public static void printGroupList(List<Group> groups) {
    // Definiert einen benutzerdefinierten Comparator, der die Reihenfolge der Gänge berücksichtigt
    Comparator<Group> groupComparator =
        (group1, group2) -> {
          Course course1 = group1.getCourse();
          Course course2 = group2.getCourse();

          // definiert die Sortierreihenfolge für die Gänge
          List<Course> order = Arrays.asList(Course.first, Course.main, Course.dessert);

          // vergleicht die Positionen der Gänge in der definierten Reihenfolge
          return Integer.compare(order.indexOf(course1), order.indexOf(course2));
        };

    // Sortiert die Gruppenliste mit dem benutzerdefinierten Vergleichen
    groups.sort(groupComparator);

    // Ausgabe der sortierten Gruppeninformationen
    System.out.println("Anzahl der Gruppen: " + groups.size());
    System.out.println("====================\n");
    for (int i = 0; i < groups.size(); i++) {
      System.out.println("Gruppe Nummer " + (i + 1) + " : " + groups.get(i).getName() + ".te");
      System.out.println("--------------------");
      if (groups.get(i).getCourse() != null) {
        System.out.println("Gang: " + groups.get(i).getCourse().name());
      }
      Group group = groups.get(i);

      // Ausgabe des kochenden Paares
      Pair<Control, Control> cookingPair = group.getCookingPair();
      if (cookingPair != null) {
        System.out.println(
            "Kochendes Paar: "
                + cookingPair.getParticipant1().getName()
                + " und "
                + cookingPair.getParticipant2().getName());
      }
      System.out.println("--------------------");

      // Ausgabe der restlichen Paare
      for (Pair<Control, Control> pair : group.getPairs()) {
        System.out.println(
            pair.getParticipant1().getName()
                + " und "
                + pair.getParticipant2().getName()
                + " - "
                + pair.getFoodPreference()
                + " - Küchenstatus: "
                + pair.getParticipant1().getKitchenStatus()
                + " & "
                + pair.getParticipant2().getKitchenStatus());
        System.out.println("--------------------");
      }
      System.out.println("====================\n");
    }
  }

  /**
   * Gets the successor participants observable list.
   *
   * @return the successor participants observable list
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public ObservableList<Participant> getParticipantsObservableList() throws IOException {
    List<Participant> successorParticipantsList = getSuccessorParticipants();
    this.successorParticipants = FXCollections.observableArrayList(successorParticipantsList);
    return successorParticipants;
  }

  public ObservableList<Participant> getSuccessorParticipantsObservableList() {
    return successorParticipants;
  }

  public void addSuccessorParticipant(Participant participant) {
    this.successorParticipants.add(participant);
  }


  public void removeSuccessorParticipant(Participant successorParticipant) {
    this.successorParticipants.remove(successorParticipant);
  }

}
