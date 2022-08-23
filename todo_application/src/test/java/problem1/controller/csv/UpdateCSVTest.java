package problem1.controller.csv;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.IToDoList;
import problem1.model.ToDo;
import problem1.model.ToDoList;

class UpdateCSVTest {
  UpdateCSV testUpdate;
  String expectedFile = "sampleUpdateCsvFile.csv";
  ToDo todo1;
  ToDo todo2;
  ToDo todo3;
  ToDo todo4;
  ToDo todo5;
  IToDoList expectedList = new ToDoList();
  Map<Integer, String> expectedKeys;

  @BeforeEach
  void setUp() {
    todo1 = new ToDo.ToDoBuilder("Finish HW9").addCompleted(Boolean.FALSE).addDue(
        LocalDate.of(2021, 8, 2)).addPriority(1).addCategory("school").build();
    todo2 = new ToDo.ToDoBuilder("Mail passport").addCompleted(Boolean.TRUE).addDue(
        LocalDate.of(2021, 8, 28)).build();
    todo3 = new ToDo.ToDoBuilder("Study for finals").addCompleted(
        Boolean.FALSE).addPriority(2).addCategory("school").build();
    todo4 = new ToDo.ToDoBuilder("Clean the house").addCompleted(Boolean.FALSE).addDue(
        LocalDate.of(2021, 8, 15)).addPriority(3).addCategory("home").build();
    todo5 = new ToDo.ToDoBuilder("Buy yarn for blanket, stuffed toy").addCompleted(
        Boolean.TRUE).addCategory("home").build();
    expectedList.addToDo(todo1);
    expectedList.addToDo(todo2);
    expectedList.addToDo(todo3);
    expectedList.addToDo(todo4);
    expectedList.addToDo(todo5);

    testUpdate = new UpdateCSV(expectedFile, expectedList);
    expectedKeys = new HashMap<>();
  }

  @Test
  void update() {
    String keyString = "id, text, completed, due, priority, category";
    String[] keyList = keyString.split(", ");
    for(int i = 0; i<keyList.length; ++i){expectedKeys.put(i, keyList[i]);}
    String expectedFirstEntry = "1,\"\"\"Finish HW9\"\"\",\"\"\"false\"\"\",\"\"\"8/2/2021\"\"\","
        + "\"\"\"1\"" + "\"\",\"\"\"school\"\"\"";
    try {
      testUpdate.update(expectedKeys);
      BufferedReader r = new BufferedReader(new FileReader(expectedFile));
      String actualFirstEntry = r.readLine();
      actualFirstEntry = r.readLine();
      r.close();
      assertEquals(expectedFirstEntry, actualFirstEntry);
    } catch (GenericCSVException | IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void testEquals_itself() {
    assertTrue(testUpdate.equals(testUpdate));
  }

  @Test
  void testEquals_differentObject() {
    ReadCSV testRead = new ReadCSV(expectedFile);
    assertFalse(testUpdate.equals(testRead));
  }

  @Test
  void testEquals_differentFile() {
    UpdateCSV differentFile = new UpdateCSV("emptyCSV.csv", expectedList);
    assertFalse(testUpdate.equals(differentFile));
  }

  @Test
  void testEquals_differentList() {
    UpdateCSV differentList = new UpdateCSV(expectedFile, new ToDoList());
    assertFalse(testUpdate.equals(differentList));
  }

  @Test
  void testHashCode_same() {
    UpdateCSV sameUpdate = new UpdateCSV(expectedFile, expectedList);
    assertEquals(sameUpdate.hashCode(), testUpdate.hashCode());
  }

  @Test
  void testHashCode_different() {
    UpdateCSV differentUpdate = new UpdateCSV("emptyCSV.csv", expectedList);
    assertNotEquals(differentUpdate.hashCode(), testUpdate.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "UpdateCSV{" +
        "fileName='" + expectedFile + '\'' +
        ", changes=" + expectedList +
        '}';
    assertEquals(expectedString, testUpdate.toString());
  }
}