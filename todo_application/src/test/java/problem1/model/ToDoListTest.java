package problem1.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ToDoListTest {

  private ToDoList testToDoList;

  @BeforeEach
  void setUp() {
    testToDoList = new ToDoList();
  }

  @Test
  void testEquals() {
    assertTrue(testToDoList.equals(testToDoList));
    assertFalse(testToDoList.equals(null));
  }

}