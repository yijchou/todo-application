package problem1.controller.toDoController;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.IToDoList;
import problem1.model.ToDo;
import problem1.model.ToDoList;

class DisplayTest {
  IToDoList expectedList = new ToDoList();
  ToDo todo1;
  ToDo todo2;
  ToDo todo3;
  ToDo todo4;
  ToDo todo5;
  IDisplay testDisplay;

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
    testDisplay = new Display();
  }

  @Test
  void filterByIncomplete() {
    IToDoList expectedFiltered = new ToDoList();
    expectedFiltered.addToDo(todo1);
    expectedFiltered.addToDo(todo3);
    expectedFiltered.addToDo(todo4);
    IToDoList actualFiltered = testDisplay.filterByIncomplete(expectedList);
    assertArrayEquals(expectedFiltered.getToDoList().toArray(new ToDo[0]),
        actualFiltered.getToDoList().toArray(new ToDo[0]));
  }

  @Test
  void filterByCategory_valid() {
    IToDoList expectedFiltered = new ToDoList();
    expectedFiltered.addToDo(todo4);
    expectedFiltered.addToDo(todo5);
    IToDoList actualFiltered = testDisplay.filterByCategory(expectedList,"home");
    assertArrayEquals(expectedFiltered.getToDoList().toArray(new ToDo[0]),
        actualFiltered.getToDoList().toArray(new ToDo[0]));
  }

  @Test
  void filterByCategory_invalid() {
    IToDoList expectedFiltered = new ToDoList();
    IToDoList actualFiltered = testDisplay.filterByCategory(expectedList,"work");
    assertArrayEquals(expectedFiltered.getToDoList().toArray(new ToDo[0]),
        actualFiltered.getToDoList().toArray(new ToDo[0]));
  }
  @Test
  void sortByDate() {
    IToDoList expectedSort = new ToDoList();
    expectedSort.addToDo(todo1);
    expectedSort.addToDo(todo4);
    expectedSort.addToDo(todo2);
    expectedSort.addToDo(todo3);
    expectedSort.addToDo(todo5);
    IToDoList actualSort = testDisplay.sortByDate(expectedList);
    assertArrayEquals(expectedSort.getToDoList().toArray(new ToDo[0]),
        actualSort.getToDoList().toArray(new ToDo[0]));
  }

  @Test
  void sortByPriority() {
    IToDoList expectedSort = new ToDoList();
    expectedSort.addToDo(todo1);
    expectedSort.addToDo(todo3);
    expectedSort.addToDo(todo2);
    expectedSort.addToDo(todo4);
    expectedSort.addToDo(todo5);
    IToDoList actualSort = testDisplay.sortByPriority(expectedList);
    assertArrayEquals(expectedSort.getToDoList().toArray(new ToDo[0]),
        actualSort.getToDoList().toArray(new ToDo[0]));
  }

  @Test
  void testHashCode() {
    assertEquals(31, testDisplay.hashCode());
  }

  @Test
  void testEquals_valid() {
    IDisplay emptyDisplay = new Display();
    assertTrue(testDisplay.equals(emptyDisplay));
  }

  @Test
  void testEquals_equalsItself() {
    assertTrue(testDisplay.equals(testDisplay));
  }

  @Test
  void testEquals_differentObjects() {
    IToDoList toDoList = new ToDoList();
    assertFalse(testDisplay.equals(toDoList));
  }

  @Test
  void testToString() {
    String expectedString = "Display{}";
    assertEquals(expectedString, testDisplay.toString());
  }
}