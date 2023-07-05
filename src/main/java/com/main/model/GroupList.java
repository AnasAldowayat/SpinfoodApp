package com.main.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;

/** A class representing a list of groups. */
public class GroupList {
  private final List<Group> groups;
  private List<Pair<Control, Control>> successorPairs;

  public GroupList() {
    groups = new ArrayList<>();
    successorPairs = new ArrayList<>();
  }

  public GroupList(List<Group> groupList, List<Pair<Control, Control>> remainingPairs) {
    groups = groupList;
    successorPairs = remainingPairs;
  }

  public List<Pair<Control, Control>> getSuccessorPairs() {
    return successorPairs;
  }

  public void setSuccessorPairs(List<Pair<Control, Control>> successorPairs) {
    this.successorPairs = successorPairs;
  }

  public int size() {
    return groups.size();
  }

  public void addAll(List<Group> otherGroups) {
    groups.addAll(otherGroups);
  }

  public List<Group> getGroups() {
    return groups;
  }

  /**
   * Calculates the average age difference of a group.
   *
   * @param groups the group
   * @return the ag e difference of a group
   */
  public static double calculateGroupGenderDiversity(List<Group> groups) {
    double sumDeviations = 0.0;
    int groupCount = groups.size();

    for (Group group : groups) {
      int femaleCount = 0;
      List<Pair<Control, Control>> pairs = group.getPairs();
      int totalCount = pairs.size() * 2; // da jedes Paar 2 Teilnehmer hat

      for (Pair<Control, Control> pair : pairs) {
        if (pair.getParticipant1().getGender() == Gender.FEMALE) {
          femaleCount++;
        }
        if (pair.getParticipant2().getGender() == Gender.FEMALE) {
          femaleCount++;
        }
      }

      double ratio = (double) femaleCount / totalCount;
      double deviation = Math.abs(0.5 - ratio);

      sumDeviations += deviation;
    }
    return groupCount == 0 ? 0.0 : sumDeviations / groupCount;
  }
}
