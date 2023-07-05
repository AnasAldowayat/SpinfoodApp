package com.main.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a kitchen.
 * DEUTSCH: Diese Klasse stellt eine Küche dar.
 */
public class Kitchen {

  private double kitchenStory;
  double longitude;
  double latitude;
  private  final List<Participant> participants = new ArrayList<>();

  /**
   * Constructor for a kitchen with a specified kitchen story.
   *
   * @param kitchenStory The kitchen story to set.
   */
  public Kitchen(double kitchenStory) {
    this.kitchenStory = kitchenStory;
  }

  /**
   * Constructor for a kitchen with a specified kitchen story, longitude and latitude.
   *
   * @param kitchenStory The kitchen story to set.
   * @param longitude The longitude to set.
   * @param latitude The latitude to set.
   */
  public Kitchen(double kitchenStory, double longitude, double latitude) {
    this.kitchenStory = kitchenStory;
    this.longitude = longitude;
    this.latitude = latitude;
  }

  /**
   * Constructor for a kitchen with a specified kitchen story, longitude and latitude.
   *
   * @param kichenStory The kitchen story to set.
   * @param longitude The longitude to set.
   * @param latitude The latitude to set.
   */
  public Kitchen(String kichenStory, String longitude, String latitude) {

    if (kichenStory != null && !kichenStory.isEmpty()) {
      this.kitchenStory = Double.parseDouble(kichenStory);
    }
    if (longitude != null && !longitude.isEmpty()) {
      this.longitude = Double.parseDouble(longitude);
    }
    if (latitude != null && !latitude.isEmpty()) {
      this.latitude = Double.parseDouble(latitude);
    }
  }

  /**
   * Returns the kitchen story of the kitchen.
   *
   * @return The kitchen story of the kitchen.
   */
  public double getKitchenStory() {
    return kitchenStory;
  }

  /**
   * Returns the longitude of the kitchen.
   *
   * @return The longitude of the kitchen.
   */
  public double getLongitude() {
    return longitude;
  }


  /**
   * Returns the latitude of the kitchen.
   *
   * @return The latitude of the kitchen.
   */
  public double getLatitude() {
    return latitude;
  }


  /**
   * Returns the list of participants in the kitchen.
   *
   * @return The list of participants in the kitchen.
   */
  public List<Participant> getParticipants() {
    return participants;
  }

  /**
   * KitchenStatus gibt an, ob zwei Teilnehmer in der gleichen Küche sein können.
   *
   * @param p1 Teilnehmer 1
   * @param p2 Teilnehmer 2
   * @return true, wenn die beiden Teilnehmer in der gleichen Küche sein können, sonst false.
   */
  public static boolean kitchenStatus(Participant p1, Participant p2) {
    return p1.getKitchenStatus() == KitchenStatus.no && p2.getKitchenStatus() == KitchenStatus.yes
        || p1.getKitchenStatus() == KitchenStatus.yes && p2.getKitchenStatus() == KitchenStatus.no
        || p1.getKitchenStatus() == KitchenStatus.no && p2.getKitchenStatus() == KitchenStatus.maybe
        || p1.getKitchenStatus() == KitchenStatus.maybe && p2.getKitchenStatus() == KitchenStatus.no
        || p1.getKitchenStatus() == KitchenStatus.yes
            && p2.getKitchenStatus() == KitchenStatus.maybe
        || p1.getKitchenStatus() == KitchenStatus.maybe
            && p2.getKitchenStatus() == KitchenStatus.yes;
  }

  /**
   * KitchenStatus gibt an, ob zwei Teilnehmer in der gleichen Küche sein können.
   *
   * @param p1 Teilnehmer 1
   * @param p2 Teilnehmer 2
   * @return true, wenn die beiden Teilnehmer in der gleichen Küche sein können, sonst false.
   */
  public static boolean kitchenStatus2(Participant p1, Participant p2) {
    return p1.getKitchenStatus() == KitchenStatus.no && p2.getKitchenStatus() == KitchenStatus.yes
        || p1.getKitchenStatus() == KitchenStatus.yes && p2.getKitchenStatus() == KitchenStatus.no
        || p1.getKitchenStatus() == KitchenStatus.no && p2.getKitchenStatus() == KitchenStatus.maybe
        || p1.getKitchenStatus() == KitchenStatus.maybe
            && p2.getKitchenStatus() == KitchenStatus.no;
  }
}
