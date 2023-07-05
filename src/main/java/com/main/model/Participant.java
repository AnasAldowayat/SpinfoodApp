package com.main.model;

/** The Participant class represents a single participant. */
public class Participant {

  // Fields for participant information
  private final String id;
  private final String name;
  private final FoodPreference foodPreference;
  private final int age;
  private final Gender gender;
  private final KitchenStatus kitchenStatus;
  private final Kitchen kitchen;
  private boolean isPaired;

  /**
   * Constructor for the Participant class that takes in several parameters.
   *
   * @param id the participant's ID
   * @param name the participant's name
   * @param foodPreference the participant's food preference
   * @param age the participant's age
   * @param gender the participant's gender
   * @param kitchen the participant's kitchen status
   */
  public Participant(
      String id,
      String name,
      FoodPreference foodPreference,
      int age,
      Gender gender,
      KitchenStatus kitchenStatus,
      Kitchen kitchen) {
    this.id = id;
    this.name = name;
    this.foodPreference = foodPreference;
    this.age = age;
    this.gender = gender;
    this.kitchenStatus = kitchenStatus;
    this.kitchen = kitchen;
  }

  public Kitchen getKitchen() {
    return kitchen;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }


  public FoodPreference getFoodPreference() {
    return foodPreference;
  }

  public int getAge() {
    return age;
  }


  public Gender getGender() {
    return gender;
  }


  public KitchenStatus getKitchenStatus() {
    return kitchenStatus;
  }


  public void setPartner(com.main.model.Participant partner) {
  }

  /**
   * Getter method for the participant's paired status.
   *
   * @return true if the participant is paired, false otherwise
   */
  public boolean isPaired() {

    return isPaired;
  }

  public void setPaired(boolean isPaired) {
    this.isPaired = isPaired;
  }


  /**
   * Method that calculates the participant's age range based on their age.
   *
   * @return the participant's age range
   */
  public AgeRange calculateAgeRange() {
    if (this.age >= 0 && this.age <= 17) {
      return AgeRange.ZERO_TO_SEVENTEEN;
    } else if (this.age >= 18 && this.age <= 23) {
      return AgeRange.EIGHTEEN_TO_TWENTY_THREE;
    } else if (this.age >= 24 && this.age <= 27) {
      return AgeRange.TWENTY_FOUR_TO_TWENTY_SEVEN;
    } else if (this.age >= 28 && this.age <= 30) {
      return AgeRange.TWENTY_EIGHT_TO_THIRTY;
    } else if (this.age >= 31 && this.age <= 35) {
      return AgeRange.THIRTY_ONE_TO_THIRTY_FIVE;
    } else if (this.age >= 36 && this.age <= 41) {
      return AgeRange.THIRTY_SIX_TO_FORTY_ONE;
    } else if (this.age >= 42 && this.age <= 46) {
      return AgeRange.FORTY_TWO_TO_FORTY_SIX;
    } else {
      return AgeRange.FORTY_SEVEN_TO_FIFTY_SIX;
    }
  }

  /**
   * Calculates the age difference between the current participant and another participant.
   *
   * @param otherParticipant The other participant to compare the age difference with.
   * @return The age difference between the participants.
   */
  public int calculateAgeDifference(com.main.model.Participant otherParticipant) {
    AgeRange ageRange1 = this.calculateAgeRange();
    AgeRange ageRange2 = otherParticipant.calculateAgeRange();

    int ageDifference = Math.abs(ageRange1.getValue() - ageRange2.getValue());

    return ageDifference;
  }

  /** The Participant class represents a single participant. */
  @Override
  public String toString() {
    return "Participant{"
        + getName() + " (" + getId() + "), " + getAge() + ", " + getGender()
        + ", " + getFoodPreference() + ", " + getKitchenStatus() + "}";
  }
}
