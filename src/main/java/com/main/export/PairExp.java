package com.main.export;

import com.main.model.DataModel;
import com.main.model.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Control;

/** Die Klasse PairExp repräsentiert ein exportiertes Paar. */
public class PairExp {
  public boolean premade;
  public String foodPreference;
  public ParticipantExp firstParticipant;
  public ParticipantExp secondParticipant;

  /**
   * Konstruktor für die PairExp-Klasse basierend auf einem Pair.
   *
   * @param pair Das Pair, das exportiert werden soll.
   */
  public PairExp(Pair<Control, Control> pair) {

    this.premade = pair.getParticipant1().isPaired();

    if (pair.getFoodPreference() != null) {
      this.foodPreference = pair.getFoodPreference().toLowerCase();
    } else {
      this.foodPreference = "FALSCHE EINGABE";
    }
    this.firstParticipant = new ParticipantExp(pair.getParticipant1());
    this.secondParticipant = new ParticipantExp(pair.getParticipant2());
  }

  /**
   * Standard-Konstruktor für die PairExp-Klasse.
   */
  public PairExp() {
    this.premade = false;
    this.foodPreference = "Keine Angabe";
    this.firstParticipant = new ParticipantExp();
    this.secondParticipant = new ParticipantExp();
  }

  /**
   * Gibt eine Liste aller Paare zurück.
   *
   * @param dataModel Das DataModel, das die Paare enthält.
   * @return Eine Liste aller Paare.
   * @throws IOException Falls die Datei nicht gefunden werden kann.
   */
  public static List<PairExp> getPairsList(DataModel dataModel) throws IOException {
    List<Pair<Control, Control>> successorPairs = dataModel.getPairs();
    List<PairExp> pairs = new ArrayList<>();
    for (Pair<Control, Control>successorPair : successorPairs) {
      pairs.add(new PairExp(successorPair));
    }
    return pairs;
  }

  /**
   * Gibt eine Liste aller Nachfolger-Paare zurück.
   *
   * @param dataModel Das DataModel, das die Paare enthält.
   * @return Eine Liste aller Nachfolger-Paare.
   * @throws IOException Falls die Datei nicht gefunden werden kann.
   */
  public static List<PairExp> getSuccessorPairsList(DataModel dataModel) throws IOException {
    List<Pair<Control, Control>> successorPairs = dataModel.getSuccessorPairs();
    List<PairExp> pairs = new ArrayList<>();
    for (Pair<Control, Control> successorPair : successorPairs) {
      pairs.add(new PairExp(successorPair));
    }
    return pairs;
  }

}
