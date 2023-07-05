package com.main.view;

import static com.main.model.PairList.calculateAgeDiff;
import static com.main.model.PairList.calculatePreferenceDeviation;

import com.main.model.DataModel;
import com.main.model.Group;
import com.main.model.GroupList;
import com.main.model.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Control;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * The GroupTableGenerator class generates a table of groups.
 */

public class GroupTableGenerator {

  DataModel dataModel;
  PairTableGenerator pairTableGenerator;

  public GroupTableGenerator(DataModel dataModel) {
    this.dataModel = dataModel;
    pairTableGenerator = new PairTableGenerator(dataModel);
  }

  private TableColumn<Group, String> createColumn(String title, Callback<CellDataFeatures<Group,
      String>, ObservableValue<String>> cellValueFactory) {
    TableColumn<Group, String> column = new TableColumn<>(title);
    column.setCellValueFactory(cellValueFactory);
    return column;
  }

  private TableColumn<Group, String> createPairColumn(String title, int index) {
    return createColumn(
        title,
        cellData -> {
          Group group = cellData.getValue();
          if (group.getPairs().size() > index) {
            Pair<Control, Control> pair = group.getPairs().get(index);
            return new SimpleStringProperty(pair.toString());
          } else {
            return new SimpleStringProperty("-");
          }
        });
  }

  private TableColumn<Group, String> createFirstRowColumn(
      String title, Function<List<Group>, String> computation) {
    return createColumn(
        title,
        cellData -> {
          if (cellData.getTableView().getItems().indexOf(cellData.getValue()) == 0) {
            return new SimpleStringProperty(computation.apply(cellData.getTableView().getItems()));
          } else {
            return new SimpleStringProperty("");
          }
        });
  }

  TableView<Group> createGroupTable() {
    TableView<Group> groupTable;
    groupTable = new TableView<>();
    ObservableList<Group> data;
    try {
      data = FXCollections.observableArrayList(dataModel.getGroups());
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    groupTable.setItems(data);

    final TableColumn<Group, String> groupNameColumn = createColumn("Gruppenname", cellData ->
        new SimpleStringProperty(cellData.getValue().getName()));

    final TableColumn<Group, String> pair1Column = createPairColumn("Paar 1", 0);
    final TableColumn<Group, String> pair2Column = createPairColumn("Paar 2", 1);
    final TableColumn<Group, String> pair3Column = createPairColumn("Paar 3", 2);

    final TableColumn<Group, String> cookingPairColumn = createColumn("Kochpaar", cellData ->
        new SimpleStringProperty(cellData.getValue().getCookingPair().getName()));

    final TableColumn<Group, String> courseColumn = createColumn("Gang", cellData ->
        new SimpleStringProperty(cellData.getValue().getCourse().toString()));

    final TableColumn<Group, String> numberOfGroupsColumn = createFirstRowColumn(
        "Anzahl der Gruppen", groups -> Integer.toString(getNumberOfGroups()));

    final TableColumn<Group, String> numberOfSuccessorPairsColumn = createFirstRowColumn(
        "Anzahl der Nachrücker-Paare", groups -> Integer.toString(getNumberOfSuccessorPairs()
        ));

    TableColumn<Group, String> preferenceDeviationColumn = createFirstRowColumn(
        "Vorliebenabweichung", groups -> Double.toString(
            calculateGroupPreferenceDeviation(groups)));
    preferenceDeviationColumn.setPrefWidth(200);
    preferenceDeviationColumn.setStyle("-fx-alignment: CENTER;");

    TableColumn<Group, String> avgAgeDiffColumn = createFirstRowColumn(
        "Durchschnittsalterunterschied", groups -> Double.toString(
            calculateGroupAgeDiff(groups)));
    avgAgeDiffColumn.setPrefWidth(200);
    avgAgeDiffColumn.setStyle("-fx-alignment: CENTER;");

    TableColumn<Group, String> genderDiversityColumn = createFirstRowColumn(
        "Geschlechterdiversität", groups -> Double.toString(
            GroupList.calculateGroupGenderDiversity(groups)));
    genderDiversityColumn.setPrefWidth(200);
    genderDiversityColumn.setStyle("-fx-alignment: CENTER;");

    TableColumn<Group, String> emptyColumn = createColumn("Kennzahlen", cellData ->
        new SimpleStringProperty(" "));
    emptyColumn.setPrefWidth(90);

    groupTable.getColumns().setAll(groupNameColumn, pair1Column, pair2Column, pair3Column,
        cookingPairColumn, courseColumn, emptyColumn, numberOfGroupsColumn,
        numberOfSuccessorPairsColumn, preferenceDeviationColumn, avgAgeDiffColumn,
        genderDiversityColumn);

    groupTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    return groupTable;
  }



    TableView<Pair<Control, Control>> createSuccessorPairTable() throws IOException {
    List<Pair<Control, Control>> successorPairs = dataModel.getSuccessorPairs();

      TableView<Pair<Control, Control>> successorPairTable = new TableView<>();
      ObservableList<Pair<Control, Control>> data = FXCollections.observableArrayList(
          successorPairs);
      successorPairTable.setItems(data);


      TableColumn<Pair<Control, Control>, String> pair1Column = new TableColumn<>(
          "Nachrückende Paare");
      pair1Column.setCellValueFactory(cellData -> new SimpleStringProperty(
          cellData.getValue().getName()));
      TableColumn<Pair<Control, Control>, String> pair2Column = new TableColumn<>(
          "Essensvorliebe");
      pair2Column.setCellValueFactory(cellData -> new SimpleStringProperty(
          cellData.getValue().getFoodPreference()));
      TableColumn<Pair<Control, Control>, String> pair3Column = new TableColumn<>(
          "Küchenstatus");
      pair3Column.setCellValueFactory(cellData -> new SimpleStringProperty(
          cellData.getValue().getKitchenStatus().name()));

      successorPairTable.getColumns().addAll(pair1Column, pair2Column, pair3Column);
      successorPairTable.setEditable(true);
      successorPairTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

      return successorPairTable;
  }

  /**
   * Returns the number of groups.
   *
   * @return The number of groups.
   */
  public int getNumberOfGroups() {
    int numberOfGroups;
    try {
      numberOfGroups = dataModel.getGroups().size();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    return numberOfGroups;
  }

  /**
   * Calculates the number of successor pairs .
   *
   * @return The number of successor pairs.
   */
  public int getNumberOfSuccessorPairs() {
    int numberOfSuccessorPairs;
    try {
      numberOfSuccessorPairs = dataModel.getSuccessorPairs().size();
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
    return numberOfSuccessorPairs;
  }

  /**
   * Calculates the preference deviation of the  groups.
   *
   * @param groups The list of groups.
   * @return The average group preference deviation.
   */
  public double calculateGroupPreferenceDeviation(List<Group> groups) {
    double sumGroupPreferenceDeviation = 0.0;
    int totalPairs = 0;

    for (Group group : groups) {
      List<Pair<Control, Control>> pairs = group.getPairs();
      sumGroupPreferenceDeviation += calculatePreferenceDeviation(pairs);
      totalPairs += pairs.size();
    }

    return sumGroupPreferenceDeviation / totalPairs;
  }

  /**
   * Calculates the average age difference of the  groups.
   *
   * @param groups The list of groups.
   * @return The average group age difference.
   */
  public double calculateGroupAgeDiff(List<Group> groups) {
    double sumGroupAgeDiff = 0.0;
    int totalPairs = 0;

    for (Group group : groups) {
      List<Pair<Control, Control>> pairs = group.getPairs();
      sumGroupAgeDiff += calculateAgeDiff(pairs);
      totalPairs += pairs.size();
    }

    return sumGroupAgeDiff / totalPairs;
  }


  TableView<Group> createEditableGroupTable(TableView<Group> originalTable) {
    TableView<Group> copiedTable = new TableView<>();

    // Daten kopieren
    ObservableList<Group> copiedData = FXCollections.observableArrayList(originalTable.getItems());
    copiedTable.setItems(copiedData);

    // Spalten kopieren
    for (TableColumn<Group, ?> column : originalTable.getColumns()) {
      // Spalten, die nicht kopiert werden sollen, überspringen
      if (column.getText().equals("Anzahl der Gruppen")
          || column.getText().equals("Anzahl der Nachrücker-Paare")
          || column.getText().equals("Vorliebenabweichung")
          || column.getText().equals("Durchschnittsalterunterschied")
          || column.getText().equals("Geschlechterdiversität")
          || column.getText().equals("Kennzahlen")) {
        continue;
      }
      TableColumn<Group, String> copiedColumn = new TableColumn<>(column.getText());
      copiedColumn.setCellValueFactory(cellData -> {
        Group group = cellData.getValue();
        ObservableValue<?> observableValue = column.getCellObservableValue(group);
        Object originalValue = observableValue == null ? null : observableValue.getValue();
        return new SimpleStringProperty(originalValue == null ? "-" : originalValue.toString());
      });
      copiedTable.getColumns().add(copiedColumn);
    }

    // Spalte für Löschen-Schaltfläche hinzufügen
    TableColumn<Group, Group> deleteColumn = new TableColumn<>("Gruppe löschen");
    deleteColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    deleteColumn.setCellFactory(param -> new TableCell<>() {
      private final Button deleteButton = new Button("Löschen");
      {
        setGraphic(deleteButton);
        deleteButton.setOnAction(event -> copiedData.remove(getItem()));
      }

      @Override
      protected void updateItem(Group group, boolean empty) {
        super.updateItem(group, empty);
        if (group == null) {
          setGraphic(null);
        } else {
          setGraphic(deleteButton);
        }
      }
    });
    copiedTable.getColumns().add(deleteColumn);

    // Spalte für Hinzufügen-Schaltfläche
    TableColumn<Group, Group> addColumn = new TableColumn<>("Neue Gruppe bilden");
    addColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
    addColumn.setCellFactory(param -> new TableCell<>() {
      private final Button addButton = new Button("Hinzufügen");
      {
        setGraphic(addButton);
        addButton.setOnAction(event -> {
          List<Pair<Control, Control>> availablePairs;
          try {
            TableView<Pair<Control, Control>> pairTableView = pairTableGenerator.createPairTable();
            availablePairs = new ArrayList<>(pairTableView.getItems());
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
          if (availablePairs.size() < 3) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Fehler beim Erstellen der Gruppe");
            alert.setContentText("Es müssen genau 3 Paare ausgewählt werden.");
            alert.showAndWait();
            return;
          }
          ChoiceDialog<List<Pair<Control, Control>>> dialog = new ChoiceDialog<>();
          dialog.setTitle("Wählen Sie ein Paar");
          dialog.setHeaderText("Wählen Sie ein Paar für die neue Gruppe");
          dialog.setContentText("Wählen Sie ein Paar:");
          ListView<Pair<Control, Control>> listView = new ListView<>();
          listView.setItems(FXCollections.observableArrayList(availablePairs));
          listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
          dialog.getDialogPane().setContent(listView);
          dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
              return new ArrayList<>(listView.getSelectionModel().getSelectedItems());
            }
            return null;
          });
          Optional<List<Pair<Control, Control>>> result = dialog.showAndWait();
          List<Pair<Control, Control>> finalAvailablePairs = availablePairs;
          result.ifPresent(selectedPairs -> {
            if (selectedPairs.size() != 3) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setTitle("Fehler");
              alert.setHeaderText("Fehler beim Erstellen der Gruppe");
              alert.setContentText("Es müssen genau 3 Paare ausgewählt werden.");
              alert.showAndWait();
              return;
            }
            Group newGroup = new Group();
            selectedPairs.forEach(newGroup::addPair);
            ChoiceDialog<Pair<Control, Control>> cookingPairDialog = new ChoiceDialog<>(
                null, selectedPairs);
            cookingPairDialog.setTitle("Wählen Sie das Kochpaar");
            cookingPairDialog.setHeaderText("Wählen Sie das Kochpaar für die neue Gruppe");
            cookingPairDialog.setContentText("Wählen Sie das Kochpaar:");
            Optional<Pair<Control, Control>> cookingPairResult = cookingPairDialog.showAndWait();
            cookingPairResult.ifPresent(newGroup::setCookingPair);
            ChoiceDialog<String> courseDialog = new ChoiceDialog<>("first",
                Arrays.asList("first", "main", "dessert"));
            courseDialog.setTitle("Wählen Sie den Gang");
            courseDialog.setHeaderText("Wählen Sie den Gang für die neue Gruppe");
            courseDialog.setContentText("Wählen Sie den Gang:");
            Optional<String> courseResult = courseDialog.showAndWait();
            courseResult.ifPresent(newGroup::setCourse);
            copiedData.add(newGroup);
            finalAvailablePairs.removeAll(selectedPairs);
          });
        });
      }

      @Override
      protected void updateItem(Group group, boolean empty) {
        super.updateItem(group, empty);
        if (group == null) {
          setGraphic(null);
        } else {
          setGraphic(addButton);
        }
      }
    });
    copiedTable.getColumns().add(addColumn);
    return copiedTable;
  }
}
