package com.main.controller;

import static com.main.controller.CreatePairsClass.createPairs;
import static com.main.controller.CreatePairsClass.createPairs2;
import static com.main.controller.CreatePairsClass.createPairs3;
import static com.main.model.CourseManager.createAndAddGroup;

import com.main.model.Course;
import com.main.model.CourseManager;
import com.main.model.FoodPreference;
import com.main.model.Group;
import com.main.model.GroupList;
import com.main.model.Kitchen;
import com.main.model.Pair;
import com.main.model.PairList;
import com.main.model.Participant;
import com.main.view.UIUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;

/**
 * The CreateGroupsClass class contains the methods used to create pairs and groups of participants.
 *
 * <p>It also contains the main method, which is used to test the algorithm.
 */
public class CreateGroupsClass {

  List<Pair<Control, Control>> pairs;
  List<Group> groups;
  List<Kitchen> kitchens;

  /**
   * Constructor for the CreateGroupsClass class. Initializes the lists of pairs, groups, and
   * kitchens.
   */
  public CreateGroupsClass() {
    this.pairs = new ArrayList<>();
    this.groups = new ArrayList<>();
    this.kitchens = new ArrayList<>();
  }

  /**
   * Lässt den Benutzer eine Methode zur Paarbildung auswählen.
   *
   * @param userChoice Die Methode, die der Benutzer auswählt.
   * @param participants Die Liste der Teilnehmer, die gepaart werden sollen.
   * @return Eine PairList mit den gepaarten und ungepaarten Teilnehmern.
   */
  public PairList chooseCreatePairsMethod(String userChoice, List<Participant> participants) {

    if (userChoice == null) {
      throw new IllegalArgumentException("Du musst eine Auswahl treffen!");
    }
    switch (userChoice) {
      case "a":
        return createPairs(participants);
      case "b":
        return createPairs2(participants);
      case "c":
        return createPairs3(participants);
      default:
        throw new IllegalArgumentException("Ungültige Auswahl: " + userChoice);
    }
  }

  /**
   * Sortiert die Paare nach ihrer FoodPreference.
   *
   * @return eine Liste von Paaren, die nach ihrer FoodPreference sortiert ist.
   */
  public static List<List<Pair<Control, Control>>> sortPairsByFoodPreference() throws IOException {

    List<Pair<Control, Control>> fleischList = new ArrayList<>();
    List<Pair<Control, Control>> egalList = new ArrayList<>();
    List<Pair<Control, Control>> veganList = new ArrayList<>();
    List<Pair<Control, Control>> veggieList = new ArrayList<>();

    PairList allPairs = Pair.getAllPairs();

    // Durchlaufe die Paare und ordne sie den entsprechenden Listen zu
    for (Pair<Control, Control> pair : allPairs.getPairedParticipants()) {
      FoodPreference foodPreference = FoodPreference.valueOf(pair.getFoodPreference());
      if (foodPreference == FoodPreference.meat) {
        fleischList.add(pair);
      } else if (foodPreference == FoodPreference.vegan) {
        veganList.add(pair);
      } else if (foodPreference == FoodPreference.veggie) {
        veggieList.add(pair);
      } else if (foodPreference == FoodPreference.none) {
        egalList.add(pair);
      }
    }
    List<List<Pair<Control, Control>>> sortedPairs = new ArrayList<>();
    sortedPairs.add(fleischList);
    sortedPairs.add(egalList);
    sortedPairs.add(veganList);
    sortedPairs.add(veggieList);
    return sortedPairs;
  }

  /**
   * Erstellt Gruppen aus den sortierten Paaren.
   *
   * @return eine Liste von Gruppen.
   */
  public static GroupList createGroupsFromSortedPairs(
      List<List<Pair<Control, Control>>> sortedPairs) {
    List<Pair<Control, Control>> remainingPairs = new ArrayList<>(); // Liste für nachrückende Paare
    GroupList groupList = new GroupList(); // Liste für die Gruppen

    for (List<Pair<Control, Control>> list : sortedPairs) {
      int pairCount = list.size();
      int groupCount = pairCount / 9;

      if (pairCount >= 9) {
        for (int i = 0; i < groupCount; i++) {
          PairList pairList = new PairList(list.subList(i * 9, (i + 1) * 9));
          List<Group> groups = createGroupsWithNinePairs(pairList);
          groupList.addAll(groups);
        }
        int remainingPairCount = pairCount % 9;
        if (remainingPairCount > 0) {
          remainingPairs.addAll(list.subList(groupCount * 9, pairCount));
        }
      } else {
        remainingPairs.addAll(list);
      }
    }
    return new GroupList(groupList.getGroups(), remainingPairs);
  }

  /**
   * Erstellt Gruppen mit je 9 Paaren.
   *
   * @return eine Liste von Gruppen.
   */
  public static List<Group> createGroupsWithNinePairs(PairList pairList) {
    List<Pair<Control, Control>> pairs = pairList.getPairedParticipants();

    if (pairs.size() != 9) {
      throw new IllegalArgumentException("Die Liste muss genau 9 Paare enthalten.");
    }
    CourseManager courseManager = new CourseManager();
    createAndAddGroup(courseManager, pairs, "1", Course.first, 2, 0, 1, 0);
    createAndAddGroup(courseManager, pairs, "2", Course.first, 5, 3, 4, 5);
    createAndAddGroup(courseManager, pairs, "3", Course.first, 8, 6, 7, 6);
    createAndAddGroup(courseManager, pairs, "4", Course.main, 6, 0, 3, 3);
    createAndAddGroup(courseManager, pairs, "5", Course.main, 7, 1, 4, 4);
    createAndAddGroup(courseManager, pairs, "6", Course.main, 8, 2, 5, 2);
    createAndAddGroup(courseManager, pairs, "7", Course.dessert, 8, 0, 4, 8);
    createAndAddGroup(courseManager, pairs, "8", Course.dessert, 6, 1, 5, 1);
    createAndAddGroup(courseManager, pairs, "9", Course.dessert, 7, 2, 3, 7);
    return courseManager.getAllGroups();
  }

  /**
   * Erstellt eine Gruppe und fügt sie dem CourseManager hinzu.
   *
   * @return die erstellte Gruppe.
   */
  public String getUserChoice() {
    // von auswahl der Benutzer der in klasse UIUtils
    return UIUtils.getUserChoice(); // a, b oder c


  }
}
