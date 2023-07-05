package com.main.model;

import javafx.scene.control.Control;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CsvReaderTest {


    @Test
     public void readData(){

        String filename = "teilnehmerliste.csv";
        try {
            // Lese die Daten aus der CSV-Datei
            PairList pairList = CsvReader.readData(filename);

            // Überprüfe, dass die PairList-Objekte nicht null sind und die erwartete Anzahl von Teilnehmern enthalten
            assertNotNull(pairList);
            assertNotNull(pairList.getPairedParticipants());
            assertNotNull(pairList.getSuccessorParticipants());

            assertEquals(73, pairList.getPairedParticipants().size());
            assertEquals(164, pairList.getSuccessorParticipants().size());

            // Gebe die Namen und E-Mail-Adressen der gepaarten und ungepaarten Teilnehmer aus
            System.out.println("Paired Participants:");
            for (Pair<Control,Control> p : pairList.getPairedParticipants()) {
                System.out.println(p);
            }
            System.out.println("Unpaired Participants:");
            for (Participant p : pairList.getSuccessorParticipants()) {
                System.out.println(p);
            }
        } catch (IOException e) {
            // Wenn ein Fehler auftritt, ist der Test fehlgeschlagen
            fail("IOException was thrown");
        }
        // Gebe eine Erfolgsmeldung aus
        System.out.println("readData() test passed");
        System.out.println("--------------------------------------------");
    }

    @Test
     public void readPartyLocation() {
      /*
    String filename =
        "C:/Users/anasf/OneDrive/Desktop/praktikum UNI/SP23_Gruppe2AldowayaFahammMokadamBadawi/partylocation.csv";
        try {
            // Lese die Koordinaten aus der CSV-Datei
            double[] coordinates = CsvReader.readPartyLocation(filename);

            // Überprüfe, dass das Koordinaten-Array nicht null ist und die erwartete Länge hat
            assertNotNull(coordinates);
            assertEquals(2, coordinates.length);

            // Gebe die Koordinaten in der Konsole aus
            System.out.println("Longitude: " + coordinates[0]);
            System.out.println("Latitude: " + coordinates[1]);
        } catch (IOException e) {
            // Wenn ein Fehler auftritt, ist der Test fehlgeschlagen
            fail("IOException was thrown");
        }

        // Gebe eine Erfolgsmeldung aus
        System.out.println("readPartyLocation() test passed");
        System.out.println("--------------------------------------------");

       */
    }
}