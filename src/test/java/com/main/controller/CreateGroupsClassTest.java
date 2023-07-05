package com.main.controller;

import com.main.model.CourseManager;
import com.main.model.CsvReader;
import com.main.model.DataModel;
import com.main.model.FoodPreference;
import com.main.model.Gender;
import com.main.model.Group;
import com.main.model.Kitchen;
import com.main.model.KitchenStatus;
import com.main.model.Pair;
import com.main.model.PairList;
import com.main.model.Participant;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.Control;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.main.controller.CreateGroupsClass.createGroupsWithNinePairs;
import static org.junit.Assert.*;

public class CreateGroupsClassTest {

  PairList pairList = new PairList( );
  public CreateGroupsClassTest() {
  }

  @Test
  public void createPairs() throws IOException {
    String filename = "src\\main\\resources\\data\\teilnehmerliste.csv";
    PairList pairList = CsvReader.readData(filename);
    List<Participant> participants = pairList.getSuccessorParticipants();

    // Call the createPairs method and get the result
    try {
      System.out.println("size of participants: " + participants.size() + "\n");
      PairList newPairList = CreatePairsClass.createPairs3(participants);

      assertNotNull(newPairList);
      Assert.assertFalse(newPairList.getPairedParticipants().isEmpty());
      assertFalse(newPairList.getSuccessorParticipants().isEmpty());

      // Check for invalid pairings and repeated participants
      Set<Participant> participantSet = new HashSet<>();
      for (Pair<Control,Control> pair : newPairList.getPairedParticipants()) {
        if (pair.getParticipant1().getFoodPreference() == FoodPreference.meat &&
            pair.getParticipant2().getFoodPreference() == FoodPreference.veggie) {
          throw new IllegalArgumentException("Invalid pairing: Meat preference cannot be paired with Veggie preference.");
        }

        if (!participantSet.add(pair.getParticipant1()) || !participantSet.add(pair.getParticipant2())) {
          throw new IllegalStateException("Duplicate participants found in pairs.");
        }
      }

      Set<Participant> unpairedParticipantSet = new HashSet<>();
      for (Participant participant : newPairList.getSuccessorParticipants()) {
        if (!unpairedParticipantSet.add(participant)) {
          throw new IllegalStateException("Duplicate participant found in unpaired list.");
        }
      }

      System.out.println("--------------------------------------------------");
      System.out.println("Paired Participants");
      for (Pair<Control,Control> pair : newPairList.getPairedParticipants()) {
        System.out.println(pair.getParticipant1().getName() + " - " + pair.getParticipant2().getName() + " - " + pair.getFoodPreference() + " - kichen status : " + pair.getParticipant1().getKitchenStatus() + " & " + pair.getParticipant2().getKitchenStatus());
      }
      System.out.println("--------------------------------------------------");
      System.out.println("Unpaired Participants");
      System.out.println("--------------------------------------------------");
      for (Participant participant : newPairList.getSuccessorParticipants()) {
        System.out.println(participant.getName() + " - " + participant.getFoodPreference() + " - " + participant.getKitchenStatus());
      }
      System.out.println("--------------------------------------------------");
      System.out.println("Number of pairs: " + newPairList.getPairedParticipants().size());
      System.out.println("Number of unpaired participants: " + newPairList.getSuccessorParticipants().size());
    } catch (IllegalArgumentException e) {
      fail("Invalid pairing found: " + e.getMessage());
    } catch (IllegalStateException e) {
      fail("Duplicate participant found: " + e.getMessage());
    }
  }
  @Test
  public void testSortPairsByFoodPreference() throws IOException {
    // Dateiname der teilnehmerliste.csv-Datei
    String filename = "src/main/resources/data/teilnehmerliste.csv";

    // PairList aus der CSV-Datei einlesen
    PairList pairList = CsvReader.readData(filename);

    // Methode aufrufen, um die Paare nach den Essensvorlieben zu sortieren
    List<List<Pair<Control,Control>>> sortedPairs = sortPairsByFoodPreference(pairList);

    // Paare in den sortierten Paaren ausgeben
    for (int i = 0; i < sortedPairs.size(); i++) {
      System.out.println("Essensvorlieben: " + FoodPreference.values()[i]);
      for (Pair<Control,Control> pair : sortedPairs.get(i)) {
        System.out.println(pair.getParticipant1().getName() + " - " + pair.getParticipant2().getName());
      }
      System.out.println();
    }

    // Assertions durchführen, um die Korrektheit der sortierten Paare zu überprüfen
    // ...

    // Hier kannst du die entsprechenden Assertions basierend auf den erwarteten Ergebnissen für die sortierten Paare durchführen
    // ...
  }
  @Test
  public void testSortPairsByFoodPreferenceAndCreateGroups() throws IOException {
    // Dateiname der teilnehmerliste.csv-Datei
    String filename = "src/main/resources/data/teilnehmerliste.csv";

    // PairList aus der CSV-Datei einlesen
    PairList pairList = CsvReader.readData(filename);

    // Methode aufrufen, um die Paare nach den Essensvorlieben zu sortieren
    List<List<Pair<Control,Control>>> sortedPairs = sortPairsByFoodPreference(pairList);

    // Ausgabe der sortierten Paare
    System.out.println("Sortierte Paare:");
    for (int i = 0; i < sortedPairs.size(); i++) {
      System.out.println("Essensvorlieben: " + FoodPreference.values()[i]);
      for (Pair<Control,Control> pair : sortedPairs.get(i)) {
        System.out.println(pair.getParticipant1().getName() + " - " + pair.getParticipant2().getName());
      }
      System.out.println();
    }

    // Methode aufrufen, um Gruppen aus sortierten Paaren zu erstellen
    List<List<Pair<Control,Control>>> groupList = createGroupsFromSortedPairs(sortedPairs);

    // Ausgabe der Gruppen
    System.out.println("Gruppen:");
    for (List<Pair<Control,Control>> group : groupList) {
      System.out.println("Gruppe:");
      for (Pair<Control,Control>pair : group) {
        System.out.println(pair.getParticipant1().getName() + " - " + pair.getParticipant2().getName());
      }
      System.out.println();
    }

    // Assertions durchführen, um die Korrektheit der sortierten Paare und Gruppen zu überprüfen

    // Erwartete Anzahl von Gruppen
    int expectedGroupCount = 18;

    // Überprüfen, ob die richtige Anzahl von Gruppen vorhanden ist
    Assertions.assertEquals(expectedGroupCount, groupList.size());
  }
  public static List<List<Pair<Control,Control>>> createGroupsFromSortedPairs(List<List<Pair<Control,Control>>> sortedPairs) {
    List<List<Pair<Control,Control>>> groupList = new ArrayList<>();

    // Iteriere über jede Liste von sortierten Paaren
    for (List<Pair<Control,Control>> pairList : sortedPairs) {
      int pairCount = pairList.size();
      int groupCount = pairCount / 9; // Anzahl der vollständigen Gruppen
      int remainingPairs = pairCount % 9; // Anzahl der übrigen Paare

      // Erstelle vollständige Gruppen
      for (int i = 0; i < groupCount; i++) {
        List<Pair<Control,Control>> group = new ArrayList<>();

        // Füge 9 Paare zur Gruppe hinzu
        for (int j = 0; j < 9; j++) {
          group.add(pairList.get(i * 9 + j));
        }

        groupList.add(group);
      }

      // Erstelle Gruppe mit übrigen Paaren, falls vorhanden
      if (remainingPairs > 0) {
        List<Pair<Control,Control>> remainingGroup = new ArrayList<>();

        // Füge übrige Paare zur Gruppe hinzu
        for (int i = 0; i < remainingPairs; i++) {
          remainingGroup.add(pairList.get(groupCount * 9 + i));
        }

        groupList.add(remainingGroup);
      }
    }

    return groupList;
  }


  // Die ursprüngliche Methode sortPairsByFoodPreference
  public static List<List<Pair<Control,Control>>> sortPairsByFoodPreference(PairList pairList) {
    List<Pair<Control,Control>> fleischList = new ArrayList<>();
    List<Pair<Control,Control>> egalList = new ArrayList<>();
    List<Pair<Control,Control>> veganList = new ArrayList<>();
    List<Pair<Control,Control>> veggieList = new ArrayList<>();

    // Durchlaufe die Paare und ordne sie den entsprechenden Listen zu
    for (Pair<Control,Control> pair : pairList.getPairedParticipants()) {
      FoodPreference preference1 = pair.getParticipant1().getFoodPreference();
      FoodPreference preference2 = pair.getParticipant2().getFoodPreference();

      if (preference1 == FoodPreference.meat) {
        fleischList.add(pair);
      } else if (preference1 == FoodPreference.none) {
        egalList.add(pair);
      } else if (preference1 == FoodPreference.vegan) {
        veganList.add(pair);
      } else if (preference1 == FoodPreference.veggie) {
        veggieList.add(pair);
      }

      if (preference2 == FoodPreference.meat) {
        fleischList.add(pair);
      } else if (preference2 == FoodPreference.none) {
        egalList.add(pair);
      } else if (preference2 == FoodPreference.vegan) {
        veganList.add(pair);
      } else if (preference2 == FoodPreference.veggie) {
        veggieList.add(pair);
      }
    }

    // Erstelle eine Liste von Listen, um die sortierten Paare zurückzugeben
    List<List<Pair<Control,Control>>> sortedPairs = new ArrayList<>();
    sortedPairs.add(fleischList);
    sortedPairs.add(egalList);
    sortedPairs.add(veganList);
        sortedPairs.add(veggieList);

    return sortedPairs;
  }


  @Test
  public void testCreateGroupsWithNinePairs() {
   Participant p1 = new Participant("1345", "pair 1  ",FoodPreference.none, 25, Gender.MALE, KitchenStatus.no, new Kitchen(3.0, 10.0, 20.0));
        Participant p2 = new Participant("1345234", "11", FoodPreference.meat, 25, Gender.MALE, KitchenStatus.yes,  new Kitchen(3.0, 10.0, 20.0));
        Participant p3 = new Participant("2341", "pair 2", FoodPreference.vegan, 25, Gender.FEMALE, KitchenStatus.no, new Kitchen(3.0, 10.0, 20.0));
        Participant p4 = new Participant("13455", "22", FoodPreference.meat, 25, Gender.FEMALE, KitchenStatus.no,  new Kitchen(3.0, 10.0, 20.0));
        Participant p5 = new Participant("14435", "pair 3", FoodPreference.none, 25, Gender.MALE, KitchenStatus.no,  new Kitchen(3.0, 10.0, 20.0));
        Participant p6 = new Participant("14564", "33 ", FoodPreference.veggie, 25, Gender.MALE, KitchenStatus.yes,  new Kitchen(3.0, 10.0, 20.0));
        Participant p7 = new Participant("1457", "pair 4 ", FoodPreference.none, 25, Gender.FEMALE, KitchenStatus.yes,  new Kitchen(3.0, 10.0, 20.0));
        Participant p8 = new Participant("174567456", " 44", FoodPreference.none, 25, Gender.MALE, KitchenStatus.no,  new Kitchen(3.0, 10.0, 20.0));
        Participant p9 = new Participant("4567451", " pair 5", FoodPreference.meat, 25, Gender.MALE, KitchenStatus.yes,  new Kitchen(3.0, 10.0, 20.0));
        Participant p10 = new Participant("5", "55", FoodPreference.none, 33, Gender.FEMALE, KitchenStatus.yes,  new Kitchen(3.0, 10.0, 20.0));
        Participant p11 = new Participant("1111", "pair 6", FoodPreference.none, 30, Gender.MALE, KitchenStatus.no,  new Kitchen(3.0, 10.0, 20.0));
        Participant p12 = new Participant("2222", "66", FoodPreference.vegan, 28, Gender.FEMALE, KitchenStatus.yes,  new Kitchen(3.0, 10.0, 20.0));
        Participant p13 = new Participant("3333", "pair 7", FoodPreference.meat, 32, Gender.MALE, KitchenStatus.no,  new Kitchen(3.0, 10.0, 20.0));
        Participant p14 = new Participant("4444", "77", FoodPreference.veggie, 27, Gender.FEMALE, KitchenStatus.yes,  new Kitchen(3.0, 10.0, 20.0));
        Participant p15 = new Participant("5555", "pair 8", FoodPreference.meat, 29, Gender.MALE, KitchenStatus.no,  new Kitchen(3.0, 10.0, 20.0));
        Participant p16 = new Participant("6666", "88", FoodPreference.none, 31, Gender.FEMALE, KitchenStatus.yes,  new Kitchen(3.0, 10.0, 20.0));
        Participant p17 = new Participant("7777", "pair 9", FoodPreference.meat, 26, Gender.MALE, KitchenStatus.no,  new Kitchen(3.0, 10.0, 20.0));
        Participant p18 = new Participant("8888", "99", FoodPreference.none, 33, Gender.FEMALE, KitchenStatus.yes,  new Kitchen(3.0, 10.0, 20.0));

        Pair pair1 = new Pair(p1, p2);
        Pair pair2 = new Pair(p3, p4);
        Pair pair3 = new Pair(p5, p6);
        Pair pair4 = new Pair(p7, p8);
        Pair pair5 = new Pair(p9, p10);
        Pair pair6 = new Pair(p11, p12);
        Pair pair7 = new Pair(p13, p14);
        Pair pair8 = new Pair(p15, p16);
        Pair pair9 = new Pair(p17, p18);

        pairList.addPairedParticipants(pair1);
        pairList.addPairedParticipants(pair2);
        pairList.addPairedParticipants(pair3);
        pairList.addPairedParticipants(pair4);
        pairList.addPairedParticipants(pair5);
        pairList.addPairedParticipants(pair6);
        pairList.addPairedParticipants(pair7);
        pairList.addPairedParticipants(pair8);
        pairList.addPairedParticipants(pair9);


    // Aufruf der Methode createGroupsWithNinePairs
    List<Group> groupList = createGroupsWithNinePairs(pairList);

    // Überprüfen, ob die Gruppenliste nicht null ist und genau 9 Gruppen enthält
    assertNotNull(groupList);
    assertEquals(9, groupList.size());

    // Ausgabe der Gruppen mit Gängen und dem aktuellen Kochpaar
    CourseManager courseManager = new CourseManager();
    for (Group group : groupList) {
      courseManager.addGroupToCourse(group.getCourse(), group);
    }
    //  courseManager.printCourseGroups();

  }
}





