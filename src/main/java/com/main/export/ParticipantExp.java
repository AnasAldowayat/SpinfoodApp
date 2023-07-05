package com.main.export;

import com.main.model.DataModel;
import com.main.model.Participant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** The GroupExp class represents an exported group. */
public class ParticipantExp {

  public String id;
  public String name;
  public String foodPreference;
  public Integer age;
  public String gender;
  public KitchenExp kitchen;

  /**
   * Constructor for the ParticipantExp class based on a Participant.
   *
   * @param participant The Participant that should be exported.
   */
  public ParticipantExp(Participant participant) {
    this.id = participant.getId();
    this.name = participant.getName();
    switch (participant.getFoodPreference()) {
      case vegan:
        this.foodPreference = "vegan";
        break;
      case veggie:
        this.foodPreference = "veggie";
        break;
      case meat:
        this.foodPreference = "meat";
        break;
      default:
        this.foodPreference = "none";
        break;
    }
    this.age = participant.getAge();
    switch (participant.getGender()) {
      case FEMALE:
        this.gender = "female";
        break;
      case MALE:
        this.gender = "male";
        break;
      case OTHER:
        this.gender = "other";
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + participant.getGender());
    }
    if (participant.getKitchen() != null && participant.getKitchenStatus() != null) {
      this.kitchen = new KitchenExp(participant.getKitchen(), participant.getKitchenStatus());
    } else {
      this.kitchen = null;
    }
  }

  /**
   * Constructor for the ParticipantExp class.
   *
   * <p>Creates a default ParticipantExp.
   */
  public ParticipantExp() {

    // Creating a default KitchenExp
    this.kitchen = new KitchenExp();
  }

  /**
   * Getter method for the participant's id.
   *
   * @return the participant's id
   */
  public static List<ParticipantExp> getParticipantExp(DataModel dataModel) throws IOException {
    List<ParticipantExp> participantExpList = new ArrayList<>();
    List<Participant> participantList = dataModel.getSuccessorParticipants();
    for (Participant participant : participantList) {
      participantExpList.add(new ParticipantExp(participant));
    }
    return participantExpList;
  }
}
