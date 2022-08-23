package problem1.controller.csv;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.ToDo;
import problem1.model.ToDoList;

class ReadCSVTest {
  ReadCSV testRead;
  String expectedFile = "todos.csv";
  Map<Integer, String> expectedKeys;


  @BeforeEach
  void setUp() {
    testRead = new ReadCSV(expectedFile);
    expectedKeys = new HashMap<>();
  }

  @Test
  void getKeys_Empty() {
    assertEquals(new HashMap<>(), testRead.getKeys());
  }

  @Test
  void getKeys_Filled() {
    String keyString = "id, text, completed, due, priority, category";
    String[] keyList = keyString.split(", ");
    for(int i = 0; i<keyList.length; ++i){
      expectedKeys.put(i, keyList[i]);
    }
    try {
      testRead.read();
    } catch (GenericCSVException e) {
      System.out.println("A CSV exception should not have been thrown here!" + e.getMessage());
    }
    assertEquals(expectedKeys, testRead.getKeys());
  }

  @Test
  void read_empty() {
    String emptyFile = "emptyCSV.csv";
    ReadCSV readEmpty = new ReadCSV(emptyFile);
    Exception exception = assertThrows(GenericCSVException.class, ()
        -> readEmpty.read());
    String expectedMessage = "Can not read an empty CSV File.";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void read_fileNotFound() {
    String fakeFile = "random-csv-name.csv";
    ReadCSV readInvalid = new ReadCSV(fakeFile);
    Exception exception = assertThrows(GenericCSVException.class, ()
        -> readInvalid.read());
    String expectedMessage = "Could not locate file: " + fakeFile + " in this directory.";
    assertEquals(expectedMessage, exception.getMessage());
  }

  @Test
  void read_valid() {
    ToDo todo1 = new ToDo.ToDoBuilder("Finish HW9").addCompleted(Boolean.FALSE).addDue(
        LocalDate.of(2021, 8, 2)).addPriority(1).addCategory("school").build();
    todo1.setID(1);
    try {
      assertEquals(todo1, testRead.read().getToDoList().get(0));
    } catch (GenericCSVException e) {
      System.out.println("A Generic CSV Exception should not have been thrown here!" +
          e.getMessage());
    }
  }

  @Test
  void testEquals_itself() {
    assertTrue(testRead.equals(testRead));
  }

  @Test
  void testEquals_differentObject() {
    UpdateCSV testUpdate = new UpdateCSV(expectedFile, new ToDoList());
    assertFalse(testRead.equals(testUpdate));
  }

  @Test
  void testEquals_differentParameters() {
    ReadCSV differentFileReader = new ReadCSV("emptyCSV.csv");
    assertFalse(testRead.equals(differentFileReader));
  }

  @Test
  void testHashCode_same() {
    ReadCSV sameFileReader = new ReadCSV(expectedFile);
    assertEquals(sameFileReader.hashCode(), testRead.hashCode());
  }

  @Test
  void testHashCode_different() {
    ReadCSV differentFileReader = new ReadCSV("emptyCSV.csv");
    assertNotEquals(differentFileReader.hashCode(), testRead.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "ReadCSV{" +
        "fileName='" + expectedFile + '\'' +
        ", keys=" + new HashMap<>() +
        '}';
    assertEquals(expectedString, testRead.toString());
  }
}