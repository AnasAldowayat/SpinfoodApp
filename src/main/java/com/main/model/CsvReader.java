package com.main.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;

/** The CsvReader class reads the participants' data from a CSV file. */
public class CsvReader {

  /**
   * Reads the participants' data from a CSV file.
   *
   * @param filename The name of the CSV file.
   * @return A PairList object containing the paired and unpaired participants.
   * @throws IOException if there is an error reading the file.
   */
  public static PairList readData(String filename) throws IOException {
    List<Pair<Control, Control>> pairedParticipants = new ArrayList<>();
    List<Participant> unpairedParticipants = new ArrayList<>();

    File file =
        new File(
            "C:/Users/anasf/OneDrive/Desktop/praktikum UNI/"
                + "SP23_Gruppe2AldowayaFahammMokadamBadawi/teilnehmerliste.csv");

    if (file.exists()) {
      // Datei existiert, FileReader verwenden
      BufferedReader br = new BufferedReader(new FileReader(file));
      // Weitere Verarbeitung der Datei
    } else {
      // Datei existiert nicht
      System.out.println("Die Datei wurde nicht gefunden.");
    }
    BufferedReader br = new BufferedReader(new FileReader(file));
    String line = br.readLine();

    while ((line = br.readLine()) != null) {
      String[] parts = line.split(",");
      String id = parts[1];
      String name = parts[2];
      FoodPreference foodPreference = FoodPreference.fromName(parts[3]);
      int age = Integer.parseInt(parts[4]);
      Gender gender = Gender.valueOf(parts[5].toUpperCase());

      KitchenStatus kitchenStatus;
      switch (parts[6]) {
        case "yes":
          kitchenStatus = KitchenStatus.yes;
          break;
        case "no":
          kitchenStatus = KitchenStatus.no;
          break;
        case "maybe":
          kitchenStatus = KitchenStatus.maybe;
          break;
        default:
          throw new IllegalArgumentException("Kitchen " + parts[6] + " not found");
      }

      Kitchen kitchen;
      if (kitchenStatus != KitchenStatus.no) {
        kitchen = new Kitchen(parts[7], parts[8], parts[9]);
      } else {
        kitchen = null;
      }
      Participant participant =
          new Participant(id, name, foodPreference, age, gender, kitchenStatus, kitchen);
      if (parts.length == 14) {
        String partnerId = parts[10];
        String partnerName = parts[11];
        double partnerAge = Double.parseDouble(parts[12]);
        Gender partnerGender = Gender.valueOf(parts[13].toUpperCase());
        Participant partner =
            new Participant(
                partnerId,
                partnerName,
                foodPreference,
                (int) partnerAge,
                partnerGender,
                KitchenStatus.no,
                kitchen);
        participant.setPartner(participant);
        participant.setPaired(true);
        partner.setPaired(true);
        pairedParticipants.add(new Pair(participant, partner));
      } else {
        unpairedParticipants.add(participant);
      }
    }
    br.close();
    return new PairList(pairedParticipants, unpairedParticipants);
  }

  /**
   * Reads the location of the party from a CSV file.
   *
   * @param filename The name of the CSV file.
   * @return An array containing the coordinates of the party.
   * @throws IOException if there is an error reading the file.
   */
  public static double[] readPartyLocation(String filename) throws IOException {

    File file = new File(filename);
    BufferedReader br = new BufferedReader(new FileReader(file));
    String line;

    br.readLine(); // skip first line

    line = br.readLine();

    String[] parts = line.split(",");
    double[] coordinates = new double[2];

    if (parts.length >= 2) {
      try {
        coordinates[0] = Double.parseDouble(parts[0]);
        coordinates[1] = Double.parseDouble(parts[1]);
      } catch (NumberFormatException e) {
        System.out.println("Error reading party location: " + e.getMessage());
      }
    }

    br.close();
    return coordinates;
  }
}
