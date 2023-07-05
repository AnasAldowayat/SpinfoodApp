package com.main.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.main.model.DataModel;
import com.main.view.UIUtils;
import java.io.File;
import java.io.IOException;

/** This class is used to export the data model to a JSON file. */
public class ExportCreator {
  /**
   * This method exports the data model to a JSON file.
   *
   * @param dataModel The data model to be exported.
   */
  public static void exportdatamodeltojson(DataModel dataModel) throws IOException {
    ObjectMapper mapper;
    mapper = new ObjectMapper();
    mapper.enable(SerializationFeature.INDENT_OUTPUT);
    DataModelExp dataModelExp = new DataModelExp(dataModel);
    switch (UIUtils.getUserChoice()) {
      case "a":
        try {
          mapper.writeValue(new File("result1.json"), dataModelExp);
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case "b":
        try {
          mapper.writeValue(new File("result2.json"), dataModelExp);
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      case "c":
        try {
          mapper.writeValue(new File("result3.json"), dataModelExp);
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
      default:
        try {
          mapper.writeValue(new File("resultDefault.json"), dataModelExp);
        } catch (IOException e) {
          e.printStackTrace();
        }
        break;
    }
  }
}
