package problem1.controller.toDoController;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.controller.csv.IReadCSV;
import problem1.controller.csv.ReadCSV;
import problem1.controller.toDoController.Converter;
import problem1.controller.toDoController.IConverter;
import problem1.model.ToDo;

class ConverterTest {
  IConverter testConverter;
  String[] expectedAttributes = new String[5];

  @BeforeEach
  void setUp() {
    expectedAttributes[0] = "Finish HW9";
    expectedAttributes[1] = "true";
    expectedAttributes[2] = "8/2/2021";
    expectedAttributes[3] = "1";
    expectedAttributes[4] = "school";
    testConverter = new Converter(expectedAttributes);
  }

  @Test
  void convert_valid(){
    ToDo todo1 = new ToDo.ToDoBuilder("Finish HW9").addCompleted(Boolean.TRUE).addDue(
        LocalDate.of(2021, 8, 2)).addPriority(1).addCategory("school").build();
    assertEquals(todo1, testConverter.convert());
  }

  @Test
  void convert_UnknownDate() {
    expectedAttributes[2] = "NOT INTEGER";
    IConverter unknownDateConverter = new Converter(expectedAttributes);
    ToDo unknownDateToDo = new ToDo.ToDoBuilder("Finish HW9").addCompleted(
        Boolean.TRUE).addPriority(1).addCategory("school").build();
    assertEquals(unknownDateToDo, unknownDateConverter.convert());
  }

  @Test
  void convert_UnknownPriority() {
    expectedAttributes[3] = "NOT INTEGER";
    IConverter unknownPriorityConverter = new Converter(expectedAttributes);
    ToDo unknownPriorityToDo = new ToDo.ToDoBuilder("Finish HW9").addCompleted(Boolean.TRUE).addDue(
        LocalDate.of(2021, 8, 2)).addPriority(3).addCategory("school").build();
    assertEquals(unknownPriorityToDo, unknownPriorityConverter.convert());
  }

  @Test
  void convert_UnknownCompleted() {
    expectedAttributes[1] = "NOT BOOLEAN";
    IConverter unknownPriorityConverter = new Converter(expectedAttributes);
    ToDo unknownPriorityToDo = new ToDo.ToDoBuilder("Finish HW9").addCompleted(Boolean.FALSE).addDue(
        LocalDate.of(2021, 8, 2)).addPriority(1).addCategory("school").build();
    assertEquals(unknownPriorityToDo, unknownPriorityConverter.convert());
  }

  @Test
  void testHashCode_same() {
    String[] newTestAttributes = new String[5];
    newTestAttributes[0] = "Finish HW9";
    newTestAttributes[1] = "true";
    newTestAttributes[2] = "8/2/2021";
    newTestAttributes[3] = "1";
    newTestAttributes[4] = "school";
    IConverter newTestConverter = new Converter(newTestAttributes);
    assertEquals(newTestConverter.hashCode(),testConverter.hashCode());
  }

  @Test
  void testHashCode_differentInput() {
    String[] newTestAttributes = new String[5];
    newTestAttributes[0] = "DO NOT Finish HW9";
    newTestAttributes[1] = "false";
    newTestAttributes[2] = "8/2/1990";
    newTestAttributes[3] = "1";
    newTestAttributes[4] = "school";
    IConverter newTestConverter = new Converter(newTestAttributes);
    assertNotEquals(newTestConverter.hashCode(),testConverter.hashCode());
  }

  @Test
  void testEquals_equalsItself() {
    assertTrue(testConverter.equals(testConverter));
  }

  @Test
  void testEquals_differentObject() {
    IReadCSV reader = new ReadCSV("random.txt");
    assertFalse(testConverter.equals(reader));
  }

  @Test
  void testEquals_same() {
    String[] newTestAttributes = new String[5];
    newTestAttributes[0] = "Finish HW9";
    newTestAttributes[1] = "true";
    newTestAttributes[2] = "8/2/2021";
    newTestAttributes[3] = "1";
    newTestAttributes[4] = "school";
    IConverter newTestConverter = new Converter(newTestAttributes);
    assertTrue(testConverter.equals(newTestConverter));
  }

  @Test
  void testEquals_differentInput() {
    String[] newTestAttributes = new String[5];
    newTestAttributes[0] = "DO NOT Finish HW9";
    newTestAttributes[1] = "false";
    newTestAttributes[2] = "8/2/1990";
    newTestAttributes[3] = "1";
    newTestAttributes[4] = "school";
    IConverter newTestConverter = new Converter(newTestAttributes);
    assertFalse(testConverter.equals(newTestConverter));
  }

  @Test
  void testToString() {
    String expectedString = "Converter{" +
        "attributes=" + Arrays.toString(expectedAttributes) +
        '}';
    assertEquals(expectedString, testConverter.toString());
  }
}