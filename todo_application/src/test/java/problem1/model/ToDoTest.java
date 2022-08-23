package problem1.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.ToDo.ToDoBuilder;

class ToDoTest {
  private Integer id;
  private Integer id2;
  private String text;
  private Boolean completed;
  private LocalDate due;
  private LocalDate due2;
  private LocalDate due3;
  private Integer priority;
  private String category;
  private ToDo todo;
  private ToDo todo2;
  private ToDoBuilder builder;
  private ToDoBuilder builder2;


  @BeforeEach
  void setUp() {
    id = 1;
    id2 = 2;
    text = "text";
    completed = true;
    due = LocalDate.of(2022,4,20);
    due2 = LocalDate.of(2020,3,20);
    due3 = LocalDate.of(2000,3,20);
    priority = 1;
    category = "work";

    builder = new ToDoBuilder(text);
    builder.addCompleted(completed);
    builder.addCategory(category);
    builder.addDue(due);
    builder.addPriority(priority);
    builder.addID(id);
    todo = builder.build();

    builder2 = new ToDoBuilder(text);
    todo2 = builder2.build();

  }

  @Test
  void getId() {
    builder.addID(id2);
    todo = builder.build();
    assertEquals(id2, todo.getId());
  }

  @Test
  void getText() {
    assertEquals(text, todo.getText());
  }

  @Test
  void getCompleted() {
    assertEquals(completed, todo.getCompleted());
  }

  @Test
  void getDue() {
    assertEquals(due, todo.getDue());
  }

  @Test
  void getPriority() {
    assertEquals(priority, todo.getPriority());
  }

  @Test
  void getCategory() {
    assertEquals(category, todo.getCategory());
  }

  @Test
  void testEquals() {
    assertTrue(todo.equals(todo));
    assertFalse(todo.equals(null));
    assertFalse(todo.equals(id));
    assertFalse(todo.equals(todo2));
  }

  @Test
  void testEquals2() {
    ToDo todo3;
    ToDo todo4;
    ToDoBuilder builder3;
    builder3 = new ToDoBuilder(text);
    todo3 = builder3.build();
    assertFalse(todo.equals(todo3));

    builder3.addPriority(2);
    builder3.addDue(due2);
    todo4 = builder3.build();
    assertFalse(todo3.equals(todo4));
  }

  @Test
  void testEquals3() {
    ToDo todo5;
    ToDoBuilder builder3;
    builder3 = new ToDoBuilder("todo5");
    builder3.addCompleted(completed);
    builder3.addDue(due3);
    builder3.addPriority(2);
    todo5 = builder3.build();

    ToDo todo6;
    todo6 = builder3.build();
    assertTrue(todo6.equals(todo5));
  }

  @Test
  void testHashCode() {
    int hashCode = Objects.hash(id,text,completed,due,priority,category);
    assertEquals(todo.hashCode(),hashCode);
  }

  @Test
  void testToString() {
    String expected = "ToDo{" +
        "id=" + todo.getId() +
        ", text='" + todo.getText() + '\'' +
        ", completed=" + todo.getCompleted() +
        ", due=" + todo.getDue() +
        ", priority=" + todo.getPriority() +
        ", category='" + todo.getCategory() + '\'' +
        '}';

    assertEquals(expected, todo.toString());
  }
}