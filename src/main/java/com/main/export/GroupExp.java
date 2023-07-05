package com.main.export;

import com.main.model.DataModel;
import com.main.model.FoodPreference;
import com.main.model.Group;
import com.main.model.Pair;
import com.main.model.Participant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;

/** Die Klasse GroupExp repräsentiert eine exportierte Gruppe. */
public class GroupExp {
  public String course;
  public String foodType;
  public KitchenExp kitchen;
  public PairExp cookingPair;
  public PairExp secondPair;
  public PairExp thirdPair;

  /**
   * Konstruktor für die GroupExp-Klasse basierend auf einer Group.
   *
   * @param group Die Group, die exportiert werden soll.
   */
  public GroupExp(Group group) {

    Pair<Control, Control> firstPair;
    Pair<Control, Control> secondPair;
    Pair<Control, Control> thirdPair;
    firstPair = group.getPair1();
    secondPair = group.getPair2();
    thirdPair = group.getPair3();
    this.course = group.getCourse().name();

    if (firstPair != null) {
      if (firstPair.getParticipant1() != null && firstPair.getParticipant1().getKitchen() != null) {
        Participant participant = firstPair.getParticipant1();

        this.kitchen = new KitchenExp(participant);
        this.cookingPair = new PairExp(firstPair);
      } else if (firstPair.getParticipant2() != null
          && firstPair.getParticipant2().getKitchen() != null) {
        Participant participant = firstPair.getParticipant2();

        this.kitchen = new KitchenExp(participant);
        this.cookingPair = new PairExp(firstPair);
      }

    } else {
      // Standard KitchenExp und PairExp erstellen
      this.kitchen = new KitchenExp();
      this.cookingPair = new PairExp();
    }

    if (secondPair != null) {
      this.secondPair = new PairExp(secondPair);
    } else {
      this.secondPair = new PairExp();
    }

    if (thirdPair != null) {
      this.thirdPair = new PairExp(thirdPair);
    } else {
      this.thirdPair = new PairExp();
    }

    // Get min food preference
    FoodPreference minPreference = null;
    if (firstPair != null) {
      minPreference = FoodPreference.valueOf(firstPair.getFoodPreference());
    }
    if (secondPair != null
        && (minPreference == null
            || secondPair.getFoodPreference().compareTo(String.valueOf(minPreference)) < 0)) {
      minPreference = FoodPreference.valueOf(secondPair.getFoodPreference());
    }
    if (thirdPair != null
        && (minPreference == null
            || thirdPair.getFoodPreference().compareTo(String.valueOf(minPreference)) < 0)) {
      minPreference = FoodPreference.valueOf(thirdPair.getFoodPreference());
    }

    // Set foodType based on minPreference
    if (minPreference != null) {
      switch (minPreference) {
        case vegan:
          this.foodType = "vegan";
          break;
        case veggie:
          this.foodType = "veggie";
          break;
        case meat:
          this.foodType = "meat";
          break;
        default:
          this.foodType = "none";
          break;
      }
    } else {
      this.foodType = "ist null";
    }
  }

  /**
   * Gibt eine Liste von GroupExp-Objekten zurück, basierend auf dem übergebenen DataModel.
   *
   * @param dataModel Das DataModel, das die Gruppen enthält.
   * @return Eine Liste von exportierten GroupExp-Objekten.
   * @throws IOException Wenn ein Fehler beim Zugriff auf das DataModel auftritt.
   */
  public static List<GroupExp> getGroupsList(DataModel dataModel) throws IOException {
    List<Group> groups = dataModel.getGroups();
    List<GroupExp> groupsExp = new ArrayList<>();
    for (Group group : groups) {
      groupsExp.add(new GroupExp(group));
    }
    return groupsExp;
  }
}
