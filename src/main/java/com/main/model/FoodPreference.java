/** This enum represents the different types of food preferences that participants can have. */

package com.main.model;

/**
 * This enum represents the different types of food preferences that participants can have.
 * DEUTSCH: Diese Aufzählung stellt die verschiedenen Arten von Ernährungspräferenzen dar, die
 * Teilnehmer haben können.
 */
public enum FoodPreference {
  none(0),
  meat(0),
  veggie(1),
  vegan(2);

  private int value;

  FoodPreference(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  /**
   * Returns the FoodPreference enum constant with the specified name.
   *
   * @param name the name of the food preference
   * @return the FoodPreference enum constant with the specified name
   * @throws IllegalArgumentException if there is no constant with the specified name
   */
  public static FoodPreference fromName(String name) {
    if (name != null) {
      for (FoodPreference fp : values()) {
        if (fp.name().equalsIgnoreCase(name)) {
          return fp;
        }
      }
    }
    throw new IllegalArgumentException("No constant with name " + name);
  }
}
