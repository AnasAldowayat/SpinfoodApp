package com.main.controller;

import com.main.model.FoodPreference;
import com.main.model.Kitchen;
import com.main.model.Pair;
import com.main.model.PairList;
import com.main.model.Participant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.scene.control.Control;

/**
 * The CreatePairsClass class contains the methods used to create pairs of participants.
 *
 * <p>It also contains the main method, which is used to test the algorithm.
 */
public class CreatePairsClass {

  /**
   * Creates pairs of participants based on their food preferences and kitchen status.
   *
   * @param participants The list of participants to be paired.
   * @return A PairList object containing the paired and unpaired participants.
   */
  public static PairList createPairs(List<Participant> participants) {
    participants.sort(Comparator.comparing(Participant::getFoodPreference));
    List<Pair<Control, Control>> pairs = new ArrayList<>();
    List<Participant> unpaired = new ArrayList<>();

    List<Boolean> pairedFlags = new ArrayList<>(Collections.nCopies(participants.size(), false));

    for (int i = 0; i < participants.size(); i++) {
      if (pairedFlags.get(i)) {
        continue; // Skip participants already paired
      }
      Participant p1 = participants.get(i);
      boolean isPaired = false;

      for (int j = i + 1; j < participants.size(); j++) {
        if (pairedFlags.get(j)) {
          continue; // Skip participants already paired
        }
        Participant p2 = participants.get(j);

        if (Kitchen.kitchenStatus(p1, p2)) {
          Pair<Control, Control> pair = new Pair<>(p1, p2);
          FoodPreference mainPreference = pair.calculateMainFoodPreference();

          if (mainPreference == FoodPreference.none) {
            unpaired.add(p1);
            unpaired.add(p2);
          } else {
            pairs.add(new Pair<>(p1, p2, mainPreference));
          }
          pairedFlags.set(i, true);
          pairedFlags.set(j, true);
          isPaired = true;
          break;
        }
      }
      if (!isPaired) {
        unpaired.add(p1);
      }
    }

    // If there is an odd number of participants, add the last one to the unpaired list
    if (participants.size() % 2 != 0) {
      unpaired.add(participants.get(participants.size() - 1));
    }

    return new PairList(pairs, unpaired);
  }

  /**
   * Creates pairs of participants based on age difference, food preferences, and kitchen status.
   *
   * @param participants The list of participants to be paired.
   * @return A PairList object containing the paired and unpaired participants.
   */
  public static PairList createPairs2(List<Participant> participants) {
    participants.sort(Comparator.comparing(Participant::getFoodPreference));
    List<Pair<Control, Control>> pairs = new ArrayList<>();
    List<Participant> unpaired = new ArrayList<>();

    List<Boolean> pairedFlags = new ArrayList<>(Collections.nCopies(participants.size(), false));

    for (int i = 0; i < participants.size(); i++) {
      if (pairedFlags.get(i)) {
        continue; // Skip participants already paired
      }
      Participant p1 = participants.get(i);
      boolean isPaired = false;
      int bestMatchIndex = -1;
      int minAgeDifference = Integer.MAX_VALUE;

      for (int j = i + 1; j < participants.size(); j++) {
        if (pairedFlags.get(j)) {
          continue; // Skip participants already paired
        }
        Participant p2 = participants.get(j);

        if (Kitchen.kitchenStatus(p1, p2)) {
          int ageDifference = Math.abs(p1.getAge() - p2.getAge());

          if (ageDifference < minAgeDifference) {
            minAgeDifference = ageDifference;
            bestMatchIndex = j;
          }
        }
      }

      if (bestMatchIndex != -1) {
        Participant p2 = participants.get(bestMatchIndex);
        Pair<Control, Control> pair = new Pair<>(p1, p2);
        FoodPreference mainPreference = pair.calculateMainFoodPreference();

        if (mainPreference == FoodPreference.none) {
          unpaired.add(p1);
          unpaired.add(p2);
        } else {
          pairs.add(new Pair<>(p1, p2, mainPreference));
        }

        pairedFlags.set(i, true);
        pairedFlags.set(bestMatchIndex, true);
        isPaired = true;
      }

      if (!isPaired) {
        unpaired.add(p1);
      }
    }

    // If there is an odd number of participants, add the last one to the unpaired list
    if (participants.size() % 2 != 0) {
      unpaired.add(participants.get(participants.size() - 1));
    }

    return new PairList(pairs, unpaired);
  }

  /**
   * Creates pairs of participants based on the number of successor participants.
   *
   * @param participants The list of participants to be paired.
   * @return A PairList object containing the paired and unpaired participants.
   */
  public static PairList createPairs3(List<Participant> participants) {
    participants.sort(Comparator.comparing(Participant::getFoodPreference));
    List<Pair<Control, Control>> pairs = new ArrayList<>();
    List<Participant> unpaired = new ArrayList<>();

    List<Boolean> pairedFlags = new ArrayList<>(Collections.nCopies(participants.size(), false));

    for (int i = 0; i < participants.size(); i++) {
      if (pairedFlags.get(i)) {
        continue; // Skip participants already paired
      }
      Participant p1 = participants.get(i);
      boolean isPaired = false;

      for (int j = i + 1; j < participants.size(); j++) {
        if (pairedFlags.get(j)) {
          continue; // Skip participants already paired
        }
        Participant p2 = participants.get(j);

        if (Kitchen.kitchenStatus2(p1, p2)) {
          Pair<Control, Control> pair = new Pair<>(p1, p2);
          FoodPreference mainPreference = pair.calculateMainFoodPreference();

          if (mainPreference == FoodPreference.none) {
            unpaired.add(p1);
            unpaired.add(p2);
          } else {
            pairs.add(new Pair<>(p1, p2, mainPreference));
          }
          pairedFlags.set(i, true);
          pairedFlags.set(j, true);
          isPaired = true;
          break;
        }
      }
      if (!isPaired) {
        unpaired.add(p1);
      }
    }

    // If there is an odd number of participants, add the last one to the unpaired list
    if (participants.size() % 2 != 0) {
      unpaired.add(participants.get(participants.size() - 1));
    }

    return new PairList(pairs, unpaired);
  }
}
