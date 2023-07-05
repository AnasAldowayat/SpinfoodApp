package com.main.model;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;

/** A class representing a group of participants. */
public class Group {

  public void setPair1(Pair<Control, Control> pair1) {
    this.pair1 = pair1;
  }

  private Pair<Control, Control> pair1;
  private Pair<Control, Control> pair2;
  private Pair<Control, Control> pair3;
  private List<Pair<Control, Control>> pairsinGroup;
  private String name;
  private Course course;

  public Group(String name) {
    this.name = name;
    this.pairsinGroup = new ArrayList<>();
  }

  public Group() {
    this.pairsinGroup = new ArrayList<>();
  }

  public Group(Pair<Control, Control> selectedPairs) {
    this.pairsinGroup = new ArrayList<>();
    this.pairsinGroup.add(selectedPairs);
  }

  /**
   * Fügt ein course.
   *
   * @param s [description]
   */
  public void setCourse(String s) {
    if (s.equals("first")) {
      this.course = Course.first;
    } else if (s.equals("main")) {
      this.course = Course.main;
    } else if (s.equals("dessert")) {
      this.course = Course.dessert;
    }
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  public String getName() {
    return name;
  }

  public Course getCourse() {
    return course;
  }

  /**
   * Gets the first pair in the group.
   *
   * @return The first pair in the group.
   */
  public Pair<Control, Control> getCookingPair() {
    return pair1;
  }

  /**
   * Sets the first pair in the group.
   *
   * @param cookingPair The first pair in the group.
   */
  public void setCookingPair(Pair<Control, Control> cookingPair) {
    this.pair1 = cookingPair;
  }

  /**
   * Gets the second pair in the group.
   *
   * @return The second pair in the group.
   */
  public Pair<Control, Control> getPair2() {
    return pair2;
  }

  public void setPair2(Pair pair2) {
    this.pair2 = pair2;
  }

  public void setPair3(Pair pair3) {
    this.pair3 = pair3;
  }

  /**
   * Gets the third pair in the group.
   *
   * @return The third pair in the group.
   */
  public Pair<Control, Control> getPair3() {
    return pair3;
  }

  /**
   * Überprüft, ob das Paar bereits in der Gruppe vorkommt.
   *
   * @param pair Paar
   * @return true, falls das Paar bereits in der Gruppe vorkommt, sonst false
   */
  public boolean containsPair(Pair<Control, Control> pair) {
    CourseManager courseManager = new CourseManager();

    // Überprüfen, ob das Paar bereits in andere Gruppe einer anderen Gang vorkommt
    for (Course course : Course.values()) {
      if (course != this.course && courseManager.containsPairInCourse(pair, course)) {
        return true;
      }
    }

    // Überprüfen, ob das Paar bereits in dieser Gruppe vorkommt
    return pairsinGroup.contains(pair);
  }

  public void addPair(Pair<Control, Control> pair) {
    pairsinGroup.add(pair);
  }

  public List<Pair<Control, Control>> getPairs() {
    return this.pairsinGroup;
  }

  public Pair<Control, Control> getPair1() {
    return pair1;
  }


}
