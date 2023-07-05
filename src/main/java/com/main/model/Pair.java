package com.main.model;

import com.main.controller.CreateGroupsClass;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javafx.scene.control.Control;

/** Represents a pair of participants that will be attending a dinner together. */
public class Pair<T extends Control, T1 extends Control> {
  private Participant participant1;
  private Participant participant2;
  private FoodPreference mainPreference;
  private final Set<Course> cookedCourses = new HashSet<>();

  /**
   * Creates a new Pair object with two given participants and no specific dish preference.
   *
   * @param participant1 the first participant
   * @param participant2 the second participant
   */
  public Pair(Participant participant1, Participant participant2) {
    this.participant1 = participant1;
    this.participant2 = participant2;
  }

  public Pair() {}

  /**
   * Creates a new Pair object with two given participants and a specific dish preference.
   *
   * @param p1 the first participant
   * @param p2 the second participant
   * @param mainPreference the preferred dish type
   */
  public Pair(Participant p1, Participant p2, FoodPreference mainPreference) {
    this.participant1 = p1;
    this.participant2 = p2;
    this.mainPreference = mainPreference;
  }

  /**
   * Returns the first participant of this pair.
   *
   * @return the first participant of this pair
   */
  public Participant getParticipant1() {
    return participant1;
  }

  /**
   * Returns the second participant of this pair.
   *
   * @return the second participant of this pair
   */
  public Participant getParticipant2() {
    return participant2;
  }



  boolean containsParticipant(Participant participant) {
    return this.participant1.equals(participant) || this.participant2.equals(participant);
  }

  /**
   * Calculates and returns the main food preference for this pair, based on the food preferences of
   * the two participants.
   *
   * @return the main food preference for this pair
   */
  public FoodPreference calculateMainFoodPreference() {
    if (participant1.getFoodPreference() == FoodPreference.none
        && participant2.getFoodPreference() == FoodPreference.none) {
      mainPreference = FoodPreference.meat;
    } else if (participant1.getFoodPreference() == participant2.getFoodPreference()) {
      mainPreference = participant1.getFoodPreference();
    } else if ((participant1.getFoodPreference() == FoodPreference.meat
            || participant1.getFoodPreference() == FoodPreference.none)
        && (participant2.getFoodPreference() == FoodPreference.meat
            || participant2.getFoodPreference() == FoodPreference.none)) {
      mainPreference = FoodPreference.meat;

    } else if ((participant1.getFoodPreference() == FoodPreference.vegan)
        && (participant2.getFoodPreference() == FoodPreference.veggie)) {
      mainPreference = FoodPreference.vegan;
    } else if ((participant1.getFoodPreference() == FoodPreference.veggie)
        && (participant2.getFoodPreference() == FoodPreference.vegan)) {
      mainPreference = FoodPreference.vegan;
    } else {
      return FoodPreference.none; // Default value if no match is found
    }
    return mainPreference;
  }

  public String getFoodPreference() {
    return calculateMainFoodPreference().name();
  }

  public boolean hasCooked() {
    return !cookedCourses.isEmpty();
  }

  /**
   * Returns a string representation of this Pair object.
   *
   * @return a string representation of this Pair object
   *
   */
  @Override
  public String toString() {
    return
        participant1.getName()
            + " &  "
            + participant2.getName();

    // return "Pair{"+participant1.getName() + " and " + participant2.getName() + " with food
    // preference " + getFoodPreference() + "kithchenSTATUS: " +participant1.getKitchenStatus()  ;

  }

  /**
   * Gibt die Liste von pairList und createPairs.
   *
   * @return pairList und createPairs in einer Liste.
   */
  public static PairList getAllPairs() throws IOException {
    PairList pairList = CsvReader.readData("teilnehmerliste.csv");
    List<Participant> participants = pairList.getSuccessorParticipants();
    List<Pair<Control, Control>> allPairs = new ArrayList<>();

    CreateGroupsClass createGroupsClass =
        new CreateGroupsClass(); // Erstelle eine Instanz der CreateGroupsClass-Klasse
    PairList pairsProcreatePairs =
        createGroupsClass.chooseCreatePairsMethod(
            createGroupsClass.getUserChoice(),
            participants); // Verwende die chooseCreatePairsMethod()

    allPairs.addAll(pairList.getPairedParticipants());
    allPairs.addAll(pairsProcreatePairs.getPairedParticipants());
    System.out.println(
        "paired participants from createPairs: "
            + pairsProcreatePairs.getPairedParticipants().size());
    System.out.println(
        "all paired participants from readDate (zusammen gemeldet): "
            + pairList.getPairedParticipants().size());

    return new PairList(allPairs, pairList.getSuccessorParticipants());
  }

  public int getAgeDiff() {
    return Math.abs(participant1.getAge() - participant2.getAge());
  }

  public String getName() {
    return participant1.getName() + " and " + participant2.getName();
  }

  public KitchenStatus getKitchenStatus() {
    return participant1.getKitchenStatus();
  }


}
