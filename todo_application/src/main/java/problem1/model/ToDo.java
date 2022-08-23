package problem1.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * ToDo class represents ToDo object
 */
public class ToDo {

  private static final Integer DEFAULT_PRIORITY = 3;
  private static final Boolean DEFAULT_COMPLETED = false;
  private Integer id;
  private String text;
  private Boolean completed;

  private LocalDate due;
  private Integer priority;
  private String category;

  /**
   * A ToDo builder method
   * @param builder - the builder, as ToDoBuilder
   */
  public ToDo(ToDoBuilder builder) {
    this.id = builder.id;
    this.text = builder.text;
    this.completed = builder.completed;
    this.due = builder.due;
    this.priority = builder.priority;
    this.category = builder.category;
  }

  /**
   * get Id
   * @return the id, as Integer
   */
  public Integer getId() {
    return id;
  }

  /**
   * get text
   * @return the text, as String
   */
  public String getText() {
    return text;
  }

  /**
   * get completed or not
   * @return completed or not, as Boolean
   */
  public Boolean getCompleted() {
    return completed;
  }

  /**
   * get due date
   * @return the due date, as LocalDate
   */
  public LocalDate getDue() {
    return due;
  }

  /**
   * get priority
   * @return the priority, as Integer
   */
  public Integer getPriority() {
    return priority;
  }

  /**
   * get category
   * @return the category, as String
   */
  public String getCategory() {
    return category;
  }

  /**
   * set the id
   * @param id - the id, as Integer
   */
  public void setID(Integer id) {
    this.id = id;
  }

  /**
   * set completed
   * @param completed - completed or not, as Boolean
   */
  public void setCompleted(Boolean completed) {
    this.completed = completed;
  }

  /**
   * equals method checks if two objects are the same
   * @param o as another object
   * @return if objects are the same, as a Boolean.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ToDo toDo = (ToDo) o;
    return Objects.equals(id, toDo.id) && Objects.equals(text, toDo.text)
        && Objects.equals(completed, toDo.completed) && Objects.equals(due,
        toDo.due) && Objects.equals(priority, toDo.priority) && Objects.equals(
        category, toDo.category);
  }

  /**
   * hashCode method returns the hash code value of the ToDo class
   * @return the hash code value of the ToDo class, as Integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(getId(), getText(), getCompleted(), getDue(), getPriority(), getCategory());
  }

  /**
   * toString method for the ToDo class
   * @return a String representation of the toDo
   */
  @Override
  public String toString() {
    return "ToDo{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", completed=" + completed +
        ", due=" + due +
        ", priority=" + priority +
        ", category='" + category + '\'' +
        '}';
  }

  /**
   * A class representing the fields and methods of the ToDoBuilder
   */
  public static class ToDoBuilder {

    private Integer id;
    private String text;
    private Boolean completed;
    private LocalDate due;
    private Integer priority;
    private String category;

    /**
     * Constructor of the ToDoBuilder
     * @param text - the text, as String
     */
    public ToDoBuilder(String text) {
      this.text = text;
      this.completed = DEFAULT_COMPLETED;
      this.priority = DEFAULT_PRIORITY;
    }

    /**
     * add id for ToDo
     * @param id - id, as Integer
     * @return ToDoBuilder
     */
    public ToDoBuilder addID (Integer id) {
      this.id = id;
      return this;
    }

    /**
     * add completed or not for ToDo
     * @param completed - completed or not, as Boolean
     * @return ToDoBuilder
     */
    public ToDoBuilder addCompleted (Boolean completed) {
      this.completed = completed;
      return this;
    }

    /**
     * add due for ToDo
     * @param due - the due, as LocalDate
     * @return ToDoBuilder
     */
    public ToDoBuilder addDue (LocalDate due) {
      this.due = due;
      return this;
    }

    /**
     * add priority for ToDo
     * @param priority - the priority, as Integer
     * @return ToDoBuilder
     */
    public ToDoBuilder addPriority (Integer priority) {
      this.priority = priority;
      return this;
    }

    /**
     * add category for ToDo
     * @param category - the category, as String
     * @return ToDoBuilder
     */
    public ToDoBuilder addCategory (String category) {
      this.category = category;
      return this;
    }

    /**
     * A method to generate a ToDo in the ToDoBuilder class.
     * @return a ToDo generated inside the ToDoBuilder class.
     */
    public ToDo build() {
      return new ToDo(this);
    }
  }
}