package com.main.view;

import com.main.export.ExportCreator;
import com.main.model.DataModel;
import java.io.IOException;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/** Diese Klasse repräsentiert eine Anwendung zur Festlegung der Wichtigkeit von Kriterien. */
public class HelloApplication extends Application {
  private Stage primaryStage;
  private final DataModel dataModel = new DataModel();
  private final HelloApplication helloApplication = this;

  public Stage getPrimaryStage() {
    return primaryStage;
  }

  @Override
  public void start(Stage stage) {
    this.primaryStage = stage;
    PairTableGenerator pairTableGenerator = new PairTableGenerator(dataModel);
    GroupTableGenerator groupTableGenerator = new GroupTableGenerator(dataModel);
    UIUtils uiUtils = new UIUtils(helloApplication);
    UIHandler uiHandler = new UIHandler(stage, uiUtils, pairTableGenerator, groupTableGenerator);

    Button selectCriterionButton = new Button("Persönliche Präferenzen");
    selectCriterionButton.setStyle(
        "-fx-font-size: 16px; -fx-background-color: #4CAF50; -fx-text-fill: white;"
            + " -fx-pref-width: 200px; "
            + "-fx-pref-height: 40px; -fx-border-radius: 20px; -fx-background-radius: 20px; "
            + "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");
    selectCriterionButton.setOnAction(e -> uiHandler.switchToCriteriaSelectionUI());

    Image logoImage =
        new Image(
            "C:/Users/anasf/OneDrive/Desktop/praktikum UNI/"
                + "SP23_Gruppe2AldowayaFahammMokadamBadawi/OIP.jpg");

    ImageView logoImageView = new ImageView(logoImage);
    logoImageView.setPreserveRatio(true);
    StackPane logoPane = new StackPane(logoImageView);
    logoPane.setAlignment(Pos.CENTER);
    logoPane.setStyle(
        "-fx-background-color: white; -fx-padding: 20px; -fx-border-radius: 10px; -fx-effect:"
            + " dropshadow(three-pass-box, rgba(0,0,0,0.3), 10, 0, 0, 0);");

    // Erstellen Sie das Haupt-Layout
    GridPane root = new GridPane();
    root.setAlignment(Pos.CENTER);
    root.setHgap(10);
    root.setVgap(10);
    root.setPadding(new Insets(40));
    root.setStyle("-fx-background-color: #F5F5F5;");

    // Binden Sie die Größe des Logo-ImageViews an die Größe des Fensters
    logoImageView.fitWidthProperty().bind(stage.widthProperty().multiply(0.5));

    // Positionieren Sie das Logo und den Button im Haupt-Layout
    root.add(logoPane, 0, 0);
    root.add(selectCriterionButton, 0, 1);

    // Zentrieren Sie das GridPane im Fenster
    ColumnConstraints columnConstraints = new ColumnConstraints();
    columnConstraints.setHalignment(HPos.CENTER);
    root.getColumnConstraints().add(columnConstraints);

    Scene scene = new Scene(root, 800, 600); // Ändern Sie die Fenstergröße nach Bedarf
    stage.setTitle("Willkommen in der Spinfood-Anwendung");
    stage.setScene(scene);
    stage.show();
  }
  /** Startet die Anwendung. */
  public static void main(String[] args) throws IOException {
    launch(args);

    DataModel dataModel = new DataModel();

    try {
      ExportCreator.exportdatamodeltojson(dataModel);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
