package com.main.view;


import com.main.model.Group;
import com.main.model.Pair;
import com.main.model.Participant;
import java.io.IOException;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** A class that handles the UI. */
public class UIHandler {
  private final PairTableGenerator pairTableGenerator;
  private final GroupTableGenerator groupTableGenerator;
  private final Stage primaryStage;
  private final UIUtils uiUtils;

  /**
   * Constructor for the UIHandler class that takes in three parameters.
   *
   * @param primaryStage the primary stage
   * @param uiUtils the UIUtils object
   * @param pairTableGenerator the PairTableGenerator object
   * @param groupTableGenerator the GroupTableGenerator object
   */
  public UIHandler(Stage primaryStage, UIUtils uiUtils, PairTableGenerator pairTableGenerator,
      GroupTableGenerator groupTableGenerator) {
    this.primaryStage = primaryStage;
    this.uiUtils = uiUtils;
    this.pairTableGenerator = pairTableGenerator;
    this.groupTableGenerator = groupTableGenerator;
  }

  /**
   * Switches to the criteria selection UI.
   */
  public void switchToCriteriaSelectionUI() {
    if (!uiUtils.criteriaSelectionCompleted) {
      uiUtils.criteriaSelectionCompleted = true;
      VBox criteriaSelectionRoot = uiUtils.createCriteriaSelectionUI(primaryStage);
      Scene criteriaSelectionScene = new Scene(criteriaSelectionRoot);
      primaryStage.setScene(criteriaSelectionScene);
      primaryStage.setMaximized(true);
    }
  }

  /**
   * Switches to the participant selection UI.
   */
  public void showPairTable() {
    try {
      TableView<Pair<Control, Control>> pairTable = pairTableGenerator.createPairTable();
      TableView<Participant> successorTable = pairTableGenerator.createSuccessorTable();

      VBox layout = new VBox(10);
      layout.getChildren().addAll(pairTable, successorTable);

      Scene pairScene = new Scene(layout);

      Stage pairStage = new Stage();
      pairStage.setTitle("Paarvermittlung");
      pairStage.setScene(pairScene);

      pairStage.setMaximized(true); // Fenster maximieren

      pairStage.show();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Switches to the group selection UI.
   */
  public void showGroupTable() {
    try {
      TableView<Group> groupTable = groupTableGenerator.createGroupTable();
      TableView<Pair<Control, Control>> successorPairsTable =
          groupTableGenerator.createSuccessorPairTable();

      VBox layout = new VBox(10);
      layout.getChildren().addAll(groupTable, successorPairsTable);

      Scene groupScene = new Scene(layout);

      Stage groupStage = new Stage();
      groupStage.setTitle("Gruppeneinteilung");
      groupStage.setScene(groupScene);

      groupStage.setMaximized(true); // Fenster maximieren

      groupStage.show();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  /**
   * Switches to the group selection UI.
   */
  public void showEditablePairTable() {
    try {
      TableView<Pair<Control, Control>> editablePairTable =
          pairTableGenerator.createEditablePairTable(pairTableGenerator.createPairTable());
      TableView<Participant> successorTable = pairTableGenerator.createSuccessorTable();

      VBox layout = new VBox(10);
      layout.getChildren().addAll(editablePairTable, successorTable);

      Scene secondScene = new Scene(layout);

      Stage newWindow = new Stage();
      newWindow.setTitle("Individuelle Paarkonfiguration");
      newWindow.setScene(secondScene);

      newWindow.setMaximized(true); // Fenster maximieren

      newWindow.show();

    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }



  /**
   * Switches to the group selection UI.
   */
  public void showEditableGroupTable() {
    try {
      TableView<Group> originalGroupTable = groupTableGenerator.createGroupTable();
      TableView<Group> copiedGroupTable =
          groupTableGenerator.createEditableGroupTable(originalGroupTable);
      TableView<Pair<Control, Control>> pairTable = pairTableGenerator.createPairTable();
      // Entfernen Sie die Spalten, die Sie nicht anzeigen möchten
      pairTable.getColumns().removeIf(column ->
          column.getText().equals("Vorliebenabweichung") ||
              column.getText().equals("Durchschnittsalterunterschied") ||
              column.getText().equals("Geschlechterdiversität") ||
              column.getText().equals("Paaranzahl") ||
              column.getText().equals("Nachrückeranzahl")||
              column.getText().equals("Kennzahlen")
      );


      VBox layout = new VBox(10);
      layout.getChildren().addAll(copiedGroupTable, pairTable);

      Scene copiedTableScene = new Scene(layout);

      Stage copiedTableStage = new Stage();
      copiedTableStage.setTitle("Individuelle Gruppeneinteilung");
      copiedTableStage.setScene(copiedTableScene);

      copiedTableStage.setMaximized(true);

      copiedTableStage.show();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
