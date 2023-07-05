package com.main.view;

import com.main.model.DataModel;
import com.main.model.Pair;
import com.main.model.PairList;
import com.main.model.Participant;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/** This class is used to generate the pair table. */
public class PairTableGenerator {
  DataModel dataModel;

  public PairTableGenerator(DataModel dataModel) {
    this.dataModel = dataModel;
  }

  // Hilfsmethoden für die Erstellung der Tabelle und der Spalten
  private <T> TableColumn<Pair<Control, Control>, String> createColumn(
      String title, Function<Pair<Control, Control>, T> valueFunc, int prefWidth) {
    TableColumn<Pair<Control, Control>, String> column = new TableColumn<>(title);
    column.setCellValueFactory(
        cellData -> new SimpleStringProperty(valueFunc.apply(cellData.getValue()).toString()));
    column.setPrefWidth(prefWidth);
    column.setStyle("-fx-alignment: CENTER;");
    return column;
  }

  private <T> TableColumn<Pair<Control, Control>, String> createFirstRowColumn(
      String title,
      Function<List<Pair<Control, Control>>, T> valueFunc,
      TableView<Pair<Control, Control>> pairTable,
      int prefWidth) {
    TableColumn<Pair<Control, Control>, String> column = new TableColumn<>(title);
    column.setCellValueFactory(
        cellData -> {
          if (pairTable.getItems().indexOf(cellData.getValue()) == 0) {
            return new SimpleStringProperty(valueFunc.apply(pairTable.getItems()).toString());
          } else {
            return new SimpleStringProperty(""); // Leer für alle anderen Zeilen
          }
        });
    column.setPrefWidth(prefWidth);
    column.setStyle("-fx-alignment: CENTER;");
    return column;
  }

  TableView<Pair<Control, Control>> createPairTable() throws IOException {
    TableView<Pair<Control, Control>> pairTable = new TableView<>();

    PairList pairList = Pair.getAllPairs();
    ObservableList<Pair<Control, Control>> data =
        FXCollections.observableArrayList(pairList.getPairedParticipants());
    pairTable.setItems(data);
    pairTable.setStyle("-fx-background-color: white; -fx-font-size: 14px;");

    TableColumn<Pair<Control, Control>, String> participant1Column =
        createColumn("Teilnehmer 1", pair -> pair.getParticipant1().getName(), 200);
    TableColumn<Pair<Control, Control>, String> participant2Column =
        createColumn("Teilnehmer 2", pair -> pair.getParticipant2().getName(), 200);
    TableColumn<Pair<Control, Control>, String> foodPreferenceColumn =
        createColumn("Essensvorlieben", Pair::getFoodPreference, 200);
    TableColumn<Pair<Control, Control>, String> ageDiffColumn =
        createColumn("Altersunterschied", Pair::getAgeDiff, 150);

    TableColumn<Pair<Control, Control>, String> preferenceDeviationColumn =
        createFirstRowColumn(
            "Vorliebenabweichung",
            list -> pairList.calculatePreferenceDeviation(pairList.getPairedParticipants()),
            pairTable,
            200);
    TableColumn<Pair<Control, Control>, String> avgAgeDiffColumn =
        createFirstRowColumn(
            "Durchschnittsalterunterschied",
            list -> PairList.calculateAgeDiff(pairList.getPairedParticipants()),
            pairTable,
            250);
    TableColumn<Pair<Control, Control>, String> genderDiversityColumn =
        createFirstRowColumn(
            "Geschlechterdiversität", list -> pairList.getGenderDiversity(), pairTable, 200);
    TableColumn<Pair<Control, Control>, String> pairCountColumn =
        createFirstRowColumn("Paaranzahl", this::calculatePairCount, pairTable, 150);

    TableColumn<Pair<Control, Control>, String> successorCountColumn =
        createFirstRowColumn(
            "Nachrückeranzahl",
            list -> {
              try {
                return dataModel.getParticipantsObservableList().size();
              } catch (IOException e) {
                throw new RuntimeException(e);
              }
            },
            pairTable,
            150);

    TableColumn<Pair<Control, Control>, String> emptyColumn = new TableColumn<>("Kennzahlen");
    emptyColumn.setPrefWidth(90);

    pairTable
        .getColumns()
        .setAll(
            participant1Column,
            participant2Column,
            foodPreferenceColumn,
            ageDiffColumn,
            emptyColumn,
            preferenceDeviationColumn,
            avgAgeDiffColumn,
            genderDiversityColumn,
            pairCountColumn,
            successorCountColumn);
    pairTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    return pairTable;
  }

  private <T> TableColumn<Participant, String> createParticipantColumn(
      String title, Function<Participant, T> valueFunc, int prefWidth) {
    TableColumn<Participant, String> column = new TableColumn<>(title);
    column.setCellValueFactory(
        cellData -> new SimpleStringProperty(valueFunc.apply(cellData.getValue()).toString()));
    column.setPrefWidth(prefWidth);
    column.setStyle("-fx-alignment: CENTER;");
    return column;
  }

  TableView<Participant> createSuccessorTable() throws IOException {
    TableView<Participant> successorTable = new TableView<>();
    ObservableList<Participant> successorList = dataModel.getParticipantsObservableList();
    successorTable.setItems(successorList);
    successorTable.setStyle("-fx-background-color: white; -fx-font-size: 14px;");

    TableColumn<Participant, String> nameColumn =
        createParticipantColumn("Name", Participant::getName, 200);
    TableColumn<Participant, String> foodPreferenceColumn =
        createParticipantColumn(
            "Essensvorliebe", participant -> participant.getFoodPreference().name(), 200);
    TableColumn<Participant, String> ageColumn =
        createParticipantColumn("Alter", Participant::getAge, 150);

    successorTable.getColumns().setAll(nameColumn, foodPreferenceColumn, ageColumn);

    return successorTable;
  }

  /**
   * Calculates the count of pairs in the given list.
   *
   * @param pairs The list of pairs.
   * @return The count of pairs.
   */
  public int calculatePairCount(List<Pair<Control, Control>> pairs) {
    int sumPairCount = 0;
    for (Pair<Control, Control> pair : pairs) {
      sumPairCount += 1;
    }
    return sumPairCount;
  }

  TableView<Pair<Control, Control>> createEditablePairTable(
      TableView<Pair<Control, Control>> originalPairTable) throws IOException {
    TableView<Pair<Control, Control>> editablePairTable = new TableView<>();
    ObservableList<Pair<Control, Control>> data =
        FXCollections.observableArrayList(originalPairTable.getItems());
    editablePairTable.setItems(data);

    TableView<Participant> successorTable = createSuccessorTable();

    // Kopieren Sie die Spalten aus der ursprünglichen Tabelle
    for (TableColumn<Pair<Control, Control>, ?> column : originalPairTable.getColumns()) {
      // Skip unwanted columns
      if (column.getText().equals("Vorliebenabweichung")
          || column.getText().equals("Durchschnittsalterunterschied")
          || column.getText().equals("Geschlechterdiversität")
          || column.getText().equals("Kennzahlen")
          || column.getText().equals("Nachrückeranzahl")
          || column.getText().equals("Paaranzahl")) {
        continue;
      }

      if (column.getText().equals("Teilnehmer 1") || column.getText().equals("Teilnehmer 2")) {
        TableColumn<Pair<Control, Control>, String> newColumn = new TableColumn<>(column.getText());
        newColumn.setCellValueFactory(
            ((TableColumn<Pair<Control, Control>, String>) column).getCellValueFactory());
        newColumn.setCellFactory(col -> createEditableTableCell(successorTable));
        editablePairTable.getColumns().add(newColumn);
      } else {
        editablePairTable.getColumns().add(copyColumn(column));
      }
    }

    return editablePairTable;
  }

  private <T> TableColumn<Pair<Control, Control>, T> copyColumn(
      TableColumn<Pair<Control, Control>, T> original) {
    TableColumn<Pair<Control, Control>, T> copy = new TableColumn<>(original.getText());
    copy.setCellValueFactory(original.getCellValueFactory());
    return copy;
  }

  private TableCell<Pair<Control, Control>, String> createEditableTableCell(
      TableView<Participant> successorTable) {
    TableCell<Pair<Control, Control>, String> cell =
        new TableCell<>() {
          @Override
          protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
              setText(null);
            } else {
              setText(item);
            }
          }
        };

    ContextMenu contextMenu = new ContextMenu();
    MenuItem abmeldenMenuItem = new MenuItem("Abmelden");

    abmeldenMenuItem.setOnAction(e -> {
      Pair<Control, Control> selectedPair = cell.getTableView().getItems().get(cell.getIndex());
      Participant toRemove = selectedPair.getParticipant1().getName().equals(cell.getItem())
          ? selectedPair.getParticipant1() : selectedPair.getParticipant2();
      Participant toAdd = selectedPair.getParticipant1().getName().equals(cell.getItem())
          ? selectedPair.getParticipant2() : selectedPair.getParticipant1();

      ObservableList<Pair<Control, Control>> pairData = cell.getTableView().getItems();
      pairData.remove(selectedPair);
      cell.getTableView().setItems(pairData);
      cell.getTableView().refresh();

      // Füge den Teilnehmer zur zugrundeliegenden Datenstruktur hinzu
      dataModel.addSuccessorParticipant(toAdd);

    });
    MenuItem partnerAendernMenuItem = new MenuItem("Meinen Partner ändern");

    partnerAendernMenuItem.setOnAction(e -> {
      Pair<Control, Control> selectedPair = cell.getTableView().getItems().get(cell.getIndex());
      Participant participant1 = selectedPair.getParticipant1();
      Participant participant2 = selectedPair.getParticipant2();

      Dialog<Participant> partnerDialog = createPartnerSelectionDialog(successorTable);

      // Zeigen  den Dialog an und warten Sie auf das Ergebnis
      Optional<Participant> result = partnerDialog.showAndWait();

      // Überprüfen, welcher Teilnehmer geklickt hat
      if (cell.getItem().equals(participant1.getName())) {
        // Teilnehmer 1 möchte den Partner ändern
        result.ifPresent(selectedNewPartner1 -> {
          ObservableList<Pair<Control, Control>> pairData = cell.getTableView().getItems();

          Pair<Control, Control> newPair = new Pair<>(selectedNewPartner1, participant2);
          int pairIndex = pairData.indexOf(selectedPair);
          pairData.set(pairIndex, newPair);
          cell.getTableView().setItems(pairData);
          cell.getTableView().refresh();

          // Entfernen  den ausgewählten neuen Partner aus der Nachrückenden-Liste
          dataModel.removeSuccessorParticipant(selectedNewPartner1);

          // Fügen  den alten Partner zur Nachrückenden-Liste hinzu
          dataModel.addSuccessorParticipant(participant1);

          // Aktualisiere die Nachrückenden-Tabelle
          successorTable.setItems(dataModel.getSuccessorParticipantsObservableList());
          successorTable.refresh();
        });
      } else if (cell.getItem().equals(participant2.getName())) {
        // Teilnehmer 2 möchte den Partner ändern
        result.ifPresent(selectedNewPartner2 -> {
          ObservableList<Pair<Control, Control>> pairData = cell.getTableView().getItems();

          Pair<Control, Control> newPair = new Pair<>(participant1, selectedNewPartner2);
          int pairIndex = pairData.indexOf(selectedPair);
          pairData.set(pairIndex, newPair);
          cell.getTableView().setItems(pairData);
          cell.getTableView().refresh();

          // Entfernen  den ausgewählten neuen Partner aus der Nachrückenden-Liste
          dataModel.removeSuccessorParticipant(selectedNewPartner2);

          // Fügen  den alten Partner zur Nachrückenden-Liste hinzu
          dataModel.addSuccessorParticipant(participant2);

          // Aktualisieren  die Nachrückenden-Tabelle
          successorTable.setItems(dataModel.getSuccessorParticipantsObservableList());
          successorTable.refresh();
        });
      }
    });

    contextMenu.getItems().addAll(abmeldenMenuItem, partnerAendernMenuItem);
    cell.setContextMenu(contextMenu);
    return cell;
  }

  private Dialog<Participant> createPartnerSelectionDialog(TableView<Participant> successorTable) {
    Dialog<Participant> partnerDialog = new Dialog<>();
    partnerDialog.setTitle("Neuen Partner auswählen");
    partnerDialog.setHeaderText("Wählen Sie einen neuen Partner aus:");

    ListView<Participant> successorListView = new ListView<>();
    ObservableList<Participant> successorData =
        FXCollections.observableArrayList(successorTable.getItems());
    successorListView.setItems(successorData);

    partnerDialog.getDialogPane().setContent(successorListView);
    partnerDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

    final Button okButton = (Button) partnerDialog.getDialogPane().lookupButton(ButtonType.OK);
    okButton.addEventFilter(
        ActionEvent.ACTION,
        event -> {
          Participant selectedPartner = successorListView.getSelectionModel().getSelectedItem();
          if (selectedPartner == null) {
            event.consume(); // Stop the event if no partner is selected
          } else {

            dataModel.removeSuccessorParticipant(selectedPartner);
            successorTable.setItems(dataModel.getSuccessorParticipantsObservableList());
            successorTable.refresh();
          }
        });

    partnerDialog.setResultConverter(dialogButton -> {
      if (dialogButton == ButtonType.OK) {
        Participant selectedPartner = successorListView.getSelectionModel().getSelectedItem();
        return selectedPartner;
      }
      return null;
    });
    return partnerDialog;
  }
}
