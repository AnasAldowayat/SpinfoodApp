package com.main.model;

/** This enum represents different age ranges. */
public enum AgeRange {
  ZERO_TO_SEVENTEEN(0),
  EIGHTEEN_TO_TWENTY_THREE(1),
  TWENTY_FOUR_TO_TWENTY_SEVEN(2),
  TWENTY_EIGHT_TO_THIRTY(3),
  THIRTY_ONE_TO_THIRTY_FIVE(4),
  THIRTY_SIX_TO_FORTY_ONE(5),
  FORTY_TWO_TO_FORTY_SIX(6),
  FORTY_SEVEN_TO_FIFTY_SIX(7);

  private final int value;

  // Constructor for the AgeRange enum.
  AgeRange(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
