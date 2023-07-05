package com.main.export;

import com.main.model.Kitchen;
import com.main.model.KitchenStatus;
import com.main.model.Participant;



/**
 * The KitchenExp class represents a kitchen experience.
 * It stores information about the kitchen's emergency status, story, longitude, and latitude.
 */
public class KitchenExp {
  public boolean emergencyKitchen; // Represents if the kitchen is in an emergency situation
  public Integer story; // Represents the number of stories in the kitchen
  public Double longitude; // Represents the longitude coordinate of the kitchen
  public Double latitude; // Represents the latitude coordinate of the kitchen

  /**
   * Default constructor for the KitchenExp class.
   * Sets the emergencyKitchen, story, longitude, and latitude to their default values.
   */
  public KitchenExp() {
    this.emergencyKitchen = false; // set to some default value
    this.story = 0; // set to some default value
    this.longitude = 0.0; // set to some default value
    this.latitude = 0.0; // set to some default value
  }

  /**
   * Constructor for the KitchenExp class that takes a Participant object.
   * Initializes the kitchen experience based on the participant's kitchen.
   *
   * @param participant The participant object containing the kitchen information.
   * @throws IllegalArgumentException if the participant or kitchen is null.
   */
  public KitchenExp(Participant participant) throws IllegalArgumentException {
    if (participant == null || participant.getKitchen() == null) {
      throw new IllegalArgumentException("Participant or kitchen cannot be null");
    }

    Kitchen kitchen = participant.getKitchen();

    this.emergencyKitchen = (participant.getKitchenStatus() == KitchenStatus.maybe);
    this.story = (int) kitchen.getKitchenStory();
    this.longitude = kitchen.getLongitude();
    this.latitude = kitchen.getLatitude();
  }

  /**
   * Constructor for the KitchenExp class that takes a Kitchen object and a KitchenStatus.
   * Initializes the kitchen experience based on the provided kitchen and status.
   *
   * @param kitchen       The kitchen object containing the kitchen information.
   * @param kitchenStatus The status of the kitchen.
   * @throws IllegalArgumentException if the kitchen is null.
   */
  public KitchenExp(Kitchen kitchen, KitchenStatus kitchenStatus) throws IllegalArgumentException {
    if (kitchen == null) {
      throw new IllegalArgumentException("Kitchen cannot be null");
    }

    this.emergencyKitchen = (kitchenStatus == KitchenStatus.maybe);
    this.story = (int) kitchen.getKitchenStory();
    this.longitude = kitchen.getLongitude();
    this.latitude = kitchen.getLatitude();
  }
}
