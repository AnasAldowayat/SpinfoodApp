package com.main.export;

import com.main.model.DataModel;
import java.io.IOException;
import java.util.List;

/** This class is used to export the data model to a JSON file. */
public class DataModelExp {

  public List<GroupExp> groups;
  public List<PairExp> pairs;
  public List<PairExp> successorPairs;
  public List<ParticipantExp> successorParticipants;

  /**
   * This constructor creates a DataModelExp object from a DataModel object.
   *
   * @param dataModel The DataModel object to be exported.
   */
  public DataModelExp(DataModel dataModel) throws IOException {
    this.groups = GroupExp.getGroupsList(dataModel);
    this.pairs = PairExp.getPairsList(dataModel);
    this.successorPairs = PairExp.getSuccessorPairsList(dataModel);
    this.successorParticipants = ParticipantExp.getParticipantExp(dataModel);
  }
}
