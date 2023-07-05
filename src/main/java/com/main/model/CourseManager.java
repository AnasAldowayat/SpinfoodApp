package com.main.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.scene.control.Control;

/** CourseManager verwaltet die Gruppen und die Kurse. */
public class CourseManager {
  private final Map<Course, List<Group>> courseGroups;

  /** Konstruktor. Erzeugt eine Map, die die Kurse und die Gruppen enthält. */
  public CourseManager() {
    courseGroups = new HashMap<>();
    for (Course course : Course.values()) {
      courseGroups.put(course, new ArrayList<>());
    }
  }

  /**
   * Fügt eine Gruppe zu einem Kurs hinzu.
   *
   * @param course Kurs
   * @param group Gruppe
   */
  public void addGroupToCourse(Course course, Group group) {
    List<Group> groups = courseGroups.get(course);
    groups.add(group);
  }

  /**
   * Gibt alle Gruppen zurück.
   *
   * @return Liste aller Gruppen
   */
  public List<Group> getAllGroups() {
    List<Group> allGroups = new ArrayList<>();
    for (List<Group> groups : courseGroups.values()) {
      allGroups.addAll(groups);
    }
    return allGroups;
  }

  /**
   * Gibt alle Gruppen eines Kurses zurück.
   *
   * @param course Kurs
   * @return Liste aller Gruppen eines Kurses
   */
  public List<Group> getGroupsInCourse(Course course) {
    return courseGroups.get(course);
  }


  /**
   * Gibt die Gruppe zurück, die das übergebene Kochpaar enthält.
   *
   * @param pair Kochpaar
   * @return Gruppe, die das Kochpaar enthält
   */
  public boolean containsPairInCourse(Pair<Control, Control> pair, Course course) {
    for (Group group : getGroupsInCourse(course)) {
      if (group.containsPair(pair)) {
        return true;
      }
    }
    return false;
  }

  /**
   * ERSTELLT DIE GRUPPEN UND FÜGT SIE DEM KURS HINZU.
   *
   * @param courseManager Kursmanager
   * @param pairs Paare
   * @param groupName Gruppenname
   * @param course Kurs
   * @param pairIndex1 Index des ersten Paares
   * @param pairIndex2 Index des zweiten Paares
   * @param pairIndex3 Index des dritten Paares
   * @param cookingPairIndex Index des Kochpaares
   */
  public static void createAndAddGroup(
      CourseManager courseManager,
      List<Pair<Control, Control>> pairs,
      String groupName,
      Course course,
      int pairIndex1,
      int pairIndex2,
      int pairIndex3,
      int cookingPairIndex) {
    Group group = new Group(groupName);

    Pair<Control, Control> cookingPair = pairs.get(cookingPairIndex);
    Pair<Control, Control> pair1;
    Pair<Control, Control> pair2;
    Pair<Control, Control> pair3;

    if (pairIndex1 == cookingPairIndex) {
      pair1 = cookingPair;
      pair2 = pairs.get(pairIndex2);
      pair3 = pairs.get(pairIndex3);
    } else if (pairIndex2 == cookingPairIndex) {
      pair1 = cookingPair;
      pair2 = pairs.get(pairIndex1);
      pair3 = pairs.get(pairIndex3);
    } else {
      pair1 = cookingPair;
      pair2 = pairs.get(pairIndex2);
      pair3 = pairs.get(pairIndex1);
    }

    group.addPair(pair1);
    group.addPair(pair2);
    group.addPair(pair3);
    group.setCookingPair(pair1);
    group.setPair1(pair1);
    group.setPair2(pair2);
    group.setPair3(pair3);
    group.setCourse(course);

    courseManager.addGroupToCourse(course, group);
  }
}
