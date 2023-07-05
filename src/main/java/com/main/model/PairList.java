package com.main.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;

/** The PairList class represents a pairing of participants. */
public class PairList {

  private List<Pair<Control, Control>> pairedParticipants;
  private List<Participant> successorParticipants;

  /**
   * Constructor for the PairList class that takes in two parameters.
   *
   * @param pairedParticipants a list of pairs of participants
   * @param successorParticipants a list of participants that could not be paired
   */
  public PairList(List<Pair<Control, Control>> pairedParticipants,
      List<Participant> successorParticipants) {
    this.pairedParticipants = pairedParticipants;
    this.successorParticipants = successorParticipants;
  }


  public PairList(List<Pair<Control, Control>> pairedParticipants) {
    this.pairedParticipants = pairedParticipants;
  }

  public PairList() {
    this.pairedParticipants = new ArrayList<>();
    this.successorParticipants = new ArrayList<>();
  }

  /**
   * Getter method for the list of paired participants.
   *
   * @return the list of paired participants
   */
  public List<Pair<Control, Control>> getPairedParticipants() {
    return pairedParticipants;
  }

  /**
   * Getter method for the list of successor participants.
   *
   * @return the list of successor participants
   */
  public List<Participant> getSuccessorParticipants() {
    return successorParticipants;
  }

  /**
   * Adds a successor participant to the list of successor participants.
   *
   * @param participant the successor participant to add
   */
  public void addSuccessorParticipant(Participant participant) {
    successorParticipants.add(participant);
  }

  /**
   * Gets the Combined List of paired participants and successor participants.
   *
   * @return the combined list
   */
  public List<Object> getCombinedList() {
    List<Object> combinedList = new ArrayList<>();
    combinedList.addAll(pairedParticipants);
    combinedList.addAll(successorParticipants);
    return combinedList;
  }

  /** CALCULATES THE NUMBER OF PAIRS. */
  public void calculatePairMetrics() {
    int numPairs;
    numPairs = pairedParticipants.size();
    int numBackups;
    numBackups = getNumberOfStandAloneParticipants();
    double genderDiversity;
    genderDiversity = getGenderDiversity();
    double ageDifference;
    ageDifference = getAgeDifference();
    double preferenceDeviation;
    preferenceDeviation = getPreferenceDeviation(); //deutsch abweichung der präferenz

    System.out.println("Anzahl der Pärchen: " + numPairs);

    System.out.println("----- Paare -----");
    for (Pair<Control, Control> pair : pairedParticipants) {
      System.out.println(pair);
    }

    System.out.println("----- Nachrückende -----");
    for (Participant participant : successorParticipants) {
      System.out.println(participant);
    }

    System.out.println("----- Kombinierte Liste -----");
    List<Object> combinedList = getCombinedList();
    for (Object obj : combinedList) {
      if (obj instanceof Pair) {
        System.out.println("Paar: " + obj);
      } else if (obj instanceof Participant) {
        System.out.println("Teilnehmer: " + obj);
      }
    }

    System.out.println("Anzahl der Nachrückenden Teilnehmer: " + numBackups);
    System.out.println("Geschlechterdiversität: " + genderDiversity);
    System.out.println("Altersdifferenz: " + ageDifference);
    System.out.println("Vorliebenabweichung: " + preferenceDeviation);
  }

  /**
   * CALCULATES THE NUMBER OF STAND ALONE PARTICIPANTS.
   *
   * @return the number of stand alone participants
   */
  public int getNumberOfStandAloneParticipants() {
    int count = 0;
    for (Participant participant : successorParticipants) {
      if (!isParticipantInPair(participant)) {
        count++;
      }
    }
    return count;
  }

  /**
   * GET THE GENDER DIVERSITY.
   *
   * @return THE GENDER DIVERSITY
   */
  public double getGenderDiversity() {
    double sumDeviations = 0.0;
    int pairCount = pairedParticipants.size();

    for (Pair<Control, Control> pair : pairedParticipants) {
      int femaleCount = 0;
      int totalCount = 2;  // da es sich um Paare handelt, ist die Gesamtanzahl immer 2

      if (pair.getParticipant1().getGender() == Gender.FEMALE) {
        femaleCount++;
      }
      if (pair.getParticipant2().getGender() == Gender.FEMALE) {
        femaleCount++;
      }

      double ratio = (double) femaleCount / totalCount;
      double deviation = Math.abs(0.5 - ratio);

      sumDeviations += deviation;
    }

    return pairCount == 0 ? 0.0 : sumDeviations / pairCount;
  }


  /**
   * GET THE AGE DIFFERENCE.
   *
   * @return THE AGE DIFFERENCE
   */
  public int getAgeDifference() {
    int sumAgeDifference = 0;
    for (Pair<Control, Control> pair : pairedParticipants) {
      int ageDifference = Math.abs(pair.getParticipant1().getAge() - pair.getParticipant2().getAge());
      sumAgeDifference += ageDifference;
    }
    return sumAgeDifference / pairedParticipants.size();
  }


  /**
   * GET THE PREFERENCE DEVIATION.
   *
   * @return THE PREFERENCE DEVIATION
   */
  public double getPreferenceDeviation() {
    double sumPreferenceDeviation = 0.0;
    for (Pair<Control, Control> pair : pairedParticipants) {
      int preferenceDeviation =
          Math.abs(
              pair.getParticipant1().getFoodPreference().getValue()
                  - pair.getParticipant2().getFoodPreference().getValue());
      sumPreferenceDeviation += preferenceDeviation;
    }
    int numPairs = pairedParticipants.size();

    return sumPreferenceDeviation / numPairs;
  }

  /**
   * CHECKS IF A PARTICIPANT IS IN A PAIR.
   *
   * @param participant the participant to check
   * @return true if the participant is in a pair, false otherwise
   */
  private boolean isParticipantInPair(Participant participant) {
    for (Pair<Control, Control>pair : pairedParticipants) {
      if (pair.containsParticipant(participant)) {
        return true;
      }
    }
    return false;
  }

  public int size() {
    return pairedParticipants.size();
  }

  /**
   * GETS THE CURRENT COOKING PAIR.
   *
   * @return the current cooking pair
   */
  public Pair<Control, Control> getCurrentCookingPair() {
    for (Pair<Control, Control> pair : pairedParticipants) {
      if (pair.hasCooked()) {
        return pair;
      }
    }
    return null;
  }

  public void addPairedParticipants(Pair pair1) {
    pairedParticipants.add(pair1);
  }

  //essenvorlieb ABweichung für alle Paare berechnen
  public void calculatePreferenceDeviation() {
    double sumPreferenceDeviation = 0.0;
    for (Pair<Control, Control> pair : pairedParticipants) {
      int preferenceDeviation =
          Math.abs(
              pair.getParticipant1().getFoodPreference().getValue()
                  - pair.getParticipant2().getFoodPreference().getValue());
      sumPreferenceDeviation += preferenceDeviation;
    }
    int numPairs = pairedParticipants.size();

    System.out.println("Vorliebenabweichung: " + sumPreferenceDeviation / numPairs);
  }


  /**
   * Calculates the average preference deviation for a list of pairs.
   *
   * @param pairs The list of pairs.
   * @return The average preference deviation.
   */
  public static double calculatePreferenceDeviation(List<Pair<Control, Control>> pairs) {
    double sumPreferenceDeviation = 0.0;
    for (Pair<Control, Control> pair : pairs) {
      int preferenceDeviation = Math.abs(pair.getParticipant1().getFoodPreference().getValue()
          - pair.getParticipant2().getFoodPreference().getValue());
      sumPreferenceDeviation += preferenceDeviation;
    }
    return sumPreferenceDeviation / pairs.size();
  }

  /**
   * Calculates the average age difference for a list of pairs.
   *
   * @param pairs The list of pairs.
   * @return The average age difference.
   */
  public static double calculateAgeDiff(List<Pair<Control, Control>> pairs) {
    double sumAgeDiff = 0.0;
    for (Pair<Control, Control> pair : pairs) {
      int ageDiff = Math.abs(pair.getParticipant1().getAge() - pair.getParticipant2().getAge());
      sumAgeDiff += ageDiff;
    }
    return sumAgeDiff / pairs.size();
  }


}

