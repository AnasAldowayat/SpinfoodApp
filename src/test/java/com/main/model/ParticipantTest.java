package com.main.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.*;

public class ParticipantTest {

    @Test
    public void isPaired() {
        Participant participant = new Participant("1345", "anas ", FoodPreference.none, 25, Gender.MALE, KitchenStatus.no, new Kitchen(1.5, 10.0, 20.0 ));
        participant.setPaired(true);
        assertTrue(participant.isPaired());
        participant.setPaired(false);
        assertFalse(participant.isPaired());
    }

    @Test
    public void calculateAgeRange() {
     /*   Participant participant_1 = new Participant("1222", "Kati", FoodPreference.none, 26, Gender.FEMALE, KitchenStatus.yes, null);
        Participant participant_2 = new Participant("1222", "Ania", FoodPreference.none, 40, Gender.FEMALE, KitchenStatus.yes, null);


        // Calculate the age range
        AgeRange ageRangeP1 = participant_1.calculateAgeRange();
        AgeRange ageRangeP2 = participant_2.calculateAgeRange();

        // Verify that the calculated age range matches the expected age range
        Assertions.assertEquals(AgeRange.TWENTY_FOUR_TO_TWENTY_SEVEN, ageRangeP1);
        System.out.println("Age Range of Participant_1 is:  " + ageRangeP1 );
        Assertions.assertEquals(AgeRange.THIRTY_SIX_TO_FORTY_ONE, ageRangeP2);
        System.out.println("Age Range of Participant_1 is:  " + ageRangeP2 );

      */
    }

    @Test

    public void calculateAgeDifference() {

        Participant participant1 = new Participant("1", "David", FoodPreference.none, 25, Gender.MALE, KitchenStatus.yes, null);
        Participant participant2 = new Participant("2", "Jana", FoodPreference.none, 27, Gender.FEMALE, KitchenStatus.yes, null);

        //   Test case 1: Participant 1 is older than Participant 2

        Participant participant3 = new Participant("1", "David", FoodPreference.none, 47, Gender.MALE, KitchenStatus.yes, null);
        Participant participant4 = new Participant("2", "Nebal", FoodPreference.none, 27, Gender.FEMALE, KitchenStatus.yes, null);

        int ageDifferenceP1P2 = participant1.calculateAgeDifference(participant2);
        int ageDifferenceP3P4 = participant3.calculateAgeDifference(participant4);

        // Verify that the calculated age difference matches the expected age difference

        //    case 1: Participant 2 is older than Participant 1 and the difference is optimal.
        Assertions.assertEquals(0, ageDifferenceP1P2);
        System.out.println("Age difference between Participant 1 and Participant 2 is:  " + ageDifferenceP1P2 + "   It is an otimal Age difference   ");
        //    case 2: Participant 3 is older than Participant 4 and the difference is huge.
        Assertions.assertEquals(5, ageDifferenceP3P4);
        System.out.println("Age difference between Participant 3 and Participant 4 is:  " + ageDifferenceP3P4 + "   It is a huge Age difference   ");
    }
}
