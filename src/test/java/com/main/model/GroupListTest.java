package com.main.model;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class GroupListTest {
  /*
  private GroupList groupList ;
  private  final DataModel dataModel = new DataModel();
  @Before
  public void setUp() throws IOException {

    groupList = new GroupList(dataModel.getGroups(), dataModel.getSuccessorPairs());
  }


   */
  @Test
  public void calculateGroupMetrics() {
/*
    // Aufruf der zu testenden Methode
      groupList.calculateGroupMetrics();

    // Assertion für die erwarteten Ergebnisse
    // Hier müssen Sie die erwarteten Werte entsprechend anpassen
    Assert.assertEquals(135, groupList.getNumberOfGroups());
    Assert.assertEquals(9, groupList.getNumberOfBackupPairs());
    Assert.assertEquals(0.005917159763313609 , groupList.getGenderDiversity(), 0.001);
    Assert.assertEquals(7.666666666666667, groupList.getAgeDifference(), 0.001);
    Assert.assertEquals(12.0, groupList.getPreferenceDeviation(), 0.001);
    Assert.assertEquals(0.0, groupList.calculatePathLength(), 0.001);


  }

  @Test
  public void getAgeDifference() {
/*
    Assert.assertEquals(7.666666666666667, groupList.getAgeDifference(), 0.001);

 */
    }

  @Test
  public void getPreferenceDeviation() {
/*
    Assert.assertEquals(12.0, groupList.getPreferenceDeviation(), 0.001);

 */
    }

  @Test
  public void getGenderDiversity() {
/*
    Assert.assertEquals(0.005917159763313609 , groupList.getGenderDiversity(), 0.001);

 */
    }

  @Test
  public void calculatePathLength() {
/*
    Assert.assertEquals(0.0, groupList.calculatePathLength(), 0.001);

 */
    }

  @Test
  public void getNumberOfGroups() {
/*
    Assert.assertEquals(135, groupList.getNumberOfGroups());

 */
    }

  @Test
  public void getNumberOfBackupPairs() {
/*
    Assert.assertEquals(9, groupList.getNumberOfBackupPairs());

 */
    }
}
