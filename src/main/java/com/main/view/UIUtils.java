package com.main.view;

import com.main.controller.CreateGroupsClass;
import com.main.model.DataModel;
import com.main.model.Participant;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * This class provides methods for creating the UI.
 *
 * <p>DEUTSCH: Diese Klasse stellt Methoden zum Erstellen der Benutzeroberfläche bereit.
 */
public class UIUtils {

  private static final ListProperty<Integer> selectedCriteria = new SimpleListProperty<>(
      FXCollections.observableArrayList());
  private static Stage primaryStage;
  static boolean criteriaSelectionCompleted = false;
  private static final IntegerProperty selectedCriterion = new SimpleIntegerProperty();
  private  final HelloApplication helloApplication;
  UIHandler uiHandler;
  static String userChoice = null;
  DataModel dataModel = new DataModel();

  CreateGroupsClass createGroupsClass;

  public UIUtils(HelloApplication helloApplication) {
    this.helloApplication = helloApplication;
    this.createGroupsClass = new CreateGroupsClass();
    this.uiHandler =
        new UIHandler(
            primaryStage,
            this,
            new PairTableGenerator(dataModel),
            new GroupTableGenerator(dataModel));
  }

  VBox createUI() {
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20));

    PairTableGenerator pairTableGenerator = new PairTableGenerator(dataModel);
    GroupTableGenerator groupTableGenerator = new GroupTableGenerator(dataModel);

    UIHandler uiHandler =
        new UIHandler(primaryStage, this, pairTableGenerator, groupTableGenerator);

    ImageView logo =
        new ImageView(
            new Image(
                "C:/Users/anasf/OneDrive/Desktop/praktikum UNI/SP23_Gruppe2AldowayaFahammMokadamBadawi/bild.jpg")); // Passe den Pfad zu deinem Logo an
    logo.setFitHeight(330);
    logo.setPreserveRatio(true);

    VBox buttonContainer = new VBox(40);
    buttonContainer.setAlignment(Pos.CENTER);

    Button backButton = createStyledButton("Zurück");
    backButton.setOnAction(
        e -> {
          criteriaSelectionCompleted = false;
          uiHandler.switchToCriteriaSelectionUI();

          primaryStage.setMaximized(true);
          primaryStage.setWidth(1800);
          primaryStage.setHeight(1200);
        });

    Button pairCompositionButton = createStyledButton("Paarvermittlung");
    pairCompositionButton.setOnAction(e -> uiHandler.showPairTable());

    Button groupCompositionButton = createStyledButton("Gruppeneinteilung");
    groupCompositionButton.setOnAction(e -> uiHandler.showGroupTable());
    if (criteriaSelectionCompleted) {
      Button editPairsButton = createStyledButton("Individuelle Paarkonfiguration");
      editPairsButton.setOnAction(e -> uiHandler.showEditablePairTable());

      Button showCopiedGroupTableButton = createStyledButton("Gruppenindividualisierung");
      showCopiedGroupTableButton.setOnAction(e -> uiHandler.showEditableGroupTable());

      buttonContainer.getChildren().addAll(
          pairCompositionButton,
          groupCompositionButton,
          editPairsButton,
          showCopiedGroupTableButton,
          backButton
      );
    }

    root.setLeft(logo);
    root.setCenter(buttonContainer);

    return new VBox(root);
  }

  private Button createStyledButton(String text) {
    Button button = new Button(text);
    button.setPrefWidth(350);
    button.setPrefHeight(60);
    setButtonStyle(button, "-fx-background-color: #CCCCCC;");

    ImageView logo =
        new ImageView(
            new Image(
                "C:/Users/anasf/OneDrive/Desktop/praktikum UNI/SP23_Gruppe2AldowayaFahammMokadamBadawi/OIP.jpg"));
    logo.setFitHeight(40);
    logo.setPreserveRatio(true);
    button.setGraphic(logo);

    button.setOnMouseEntered(e -> setButtonStyle(button, "-fx-background-color: #45a049;"));
    button.setOnMouseExited(e -> setButtonStyle(button, "-fx-background-color: #CCCCCC;"));
    button.setOnMousePressed(e -> setButtonStyle(button, "-fx-background-color: #3c9039;"));

    button.setTextFill(Color.BLACK);

    return button;
  }
  private void setButtonStyle(Button button, String backgroundColor) {
    button.setStyle(
        "-fx-font-size: 16px; "
            + backgroundColor
            + " -fx-text-fill: white; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");
  }

  VBox createCriteriaSelectionUI(Stage stage) {
    VBox criteriaSelectionRoot = new VBox();
    criteriaSelectionRoot.setSpacing(20);
    criteriaSelectionRoot.setPadding(new Insets(20));
    criteriaSelectionRoot.setAlignment(Pos.CENTER);

    Label titleLabel = new Label("Gestalten Sie Ihre individuellen Präferenzen");
    titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

    ToggleGroup toggleGroup = new ToggleGroup();

    ToggleButton kriterium5Button = createToggleButton(5, "a)Wunschgerechte Pärchen- und Gruppenbildung mit Fokus auf Essenspräferenzen ");
    kriterium5Button.setToggleGroup(toggleGroup);
    kriterium5Button.setStyle("-fx-font-size: 16px; -fx-pref-width: 700px; -fx-pref-height: 50px; -fx-border-radius: 20px; -fx-background-radius: 20px;");


    ToggleButton kriterium6Button = createToggleButton(6, "b)Minimierung der Altersunterschiede bei Partnerzuordnungen ");
    kriterium6Button.setToggleGroup(toggleGroup);
    kriterium6Button.setStyle("-fx-font-size: 16px; -fx-pref-width: 700px; -fx-pref-height: 40px; -fx-border-radius: 20px; -fx-background-radius: 20px;");

    ToggleButton kriterium7Button = createToggleButton(7, "c) Minimierung der Kochpartner-Lücken und effektive Nachrückenden-Zuordnung");
    kriterium7Button.setToggleGroup(toggleGroup);
    kriterium7Button.setStyle("-fx-font-size: 16px; -fx-pref-width: 700px; -fx-pref-height: 40px; -fx-border-radius: 20px; -fx-background-radius: 20px;");

    toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
      if (newToggle != null) {
        ((ToggleButton) newToggle).setStyle("-fx-font-size: 16px; -fx-pref-width: 700px; -fx-pref-height: 40px; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
      }
      if (oldToggle != null && oldToggle != newToggle) {
        ((ToggleButton) oldToggle).setStyle("-fx-font-size: 16px; -fx-pref-width: 700px; -fx-pref-height: 40px; -fx-border-radius: 20px; -fx-background-radius: 20px;");
      }
    });
    // Markiere die zuvor ausgewählten Kriterien
    kriterium5Button.setSelected(selectedCriteria.contains(5));
    kriterium6Button.setSelected(selectedCriteria.contains(6));
    kriterium7Button.setSelected(selectedCriteria.contains(7));

    Button confirmButton = new Button("Bestätigen");
    confirmButton.setStyle("-fx-font-size: 16px; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-border-radius: 20px; -fx-background-radius: 20px;");

    confirmButton.setOnMouseEntered(
        e -> {
          if (!confirmButton.isDisabled()) {
            confirmButton.setStyle(
                "-fx-font-size: 16px; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-border-radius: 20px; -fx-background-radius: 20px; -fx-background-color: #4CAF50; -fx-text-fill: white; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");
          }
        });
    confirmButton.setOnMouseExited(
        e -> {
          if (!confirmButton.isDisabled()) {
            confirmButton.setStyle(
                "-fx-font-size: 16px; -fx-pref-width: 200px; -fx-pref-height: 40px; -fx-border-radius: 20px; -fx-background-radius: 20px;");
          }
        });

    confirmButton.setOnAction(
        e -> {
          if (validateCriteriaSelection()) {
            // Speichere die ausgewählten Kriterien
            selectedCriteria.clear();

            if (kriterium5Button.isSelected()) {
              selectedCriteria.add(5);
              userChoice = "a";
            }
            if (kriterium6Button.isSelected()) {
              selectedCriteria.add(6);
              userChoice = "b";
            }
            if (kriterium7Button.isSelected()) {
              selectedCriteria.add(7);
              userChoice = "c";
            }
            setUserChoice(userChoice);
            // Verwenden Sie die Benutzerauswahl, um die geeignete 'createPairs'-Methode zu wählen
            List<Participant> participants =
                new ArrayList<>(); // Sie müssen diese Liste initialisieren und mit den Teilnehmern
            // füllen
            createGroupsClass.chooseCreatePairsMethod(userChoice, participants);
            criteriaSelectionCompleted = true;

            goToMainPage(); // Aufruf der Methode zur Rückkehr zur Hauptseite
          }
        });

// Disable den Bestätigungsbutton, wenn kein Kriterium ausgewählt ist
    toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) ->
        confirmButton.setDisable(newToggle == null));


    // Setze die Hintergrundfarbe und den Abstand des Bestätigen-Buttons
    confirmButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
    VBox.setMargin(confirmButton, new Insets(20, 0, 0, 0));

    criteriaSelectionRoot.getChildren().addAll(titleLabel, kriterium5Button, kriterium6Button, kriterium7Button, confirmButton);

    // Setze den Hintergrund und den Rand des Kriterienauswahl-Containers
    criteriaSelectionRoot.setStyle("-fx-background-color: #F5F5F5; -fx-border-color: #CCCCCC; -fx-border-radius: 10px; -fx-padding: 20px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");

    // Setze die Ausrichtung des Kriterienauswahl-Containers
    criteriaSelectionRoot.setAlignment(Pos.CENTER);


    return criteriaSelectionRoot;
  }

  private ToggleButton createToggleButton(int criterion, String text) {
    ToggleButton toggleButton = new ToggleButton(text);
    toggleButton.setPrefWidth(200);
    toggleButton.setPrefHeight(40);
    toggleButton.setUserData(criterion);
    toggleButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue) {
        selectedCriterion.set((int) toggleButton.getUserData());
      } else {
        selectedCriterion.set(0);
      }
    });
    return toggleButton;
  }

  private boolean validateCriteriaSelection() {
    if (selectedCriterion.get() != 0) {
      return true;
    } else {
      showErrorMessage("Ungültige Auswahl", "Bitte wählen Sie mindestens ein Kriterium aus.");
      return false;
    }
  }

  private void showErrorMessage(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
  public void goToMainPage() {
    primaryStage = helloApplication.getPrimaryStage();
    primaryStage.setTitle("WILLKOMMEN BEI SPINFOOD"); // Titel der Hauptseite
    primaryStage.setScene(new Scene(createUI()));
    primaryStage.setMaximized(true); // Fenster maximieren
    primaryStage.show();
  }

  public static String getUserChoice() {
    return userChoice;
  }
  public void setUserChoice(String userChoice) {
    this.userChoice = userChoice;
  }
}
