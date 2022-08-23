package problem1.model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * ToDoList class represent the collection of ToDos.
 */
public class ToDoList implements IToDoList{

  private ArrayList<ToDo> toDoList;

  /**
   * Constructor for ToDoList class.
   */
  public ToDoList(ArrayList<ToDo> toDoList) {
    this.toDoList = toDoList;
  }

  /**
   * Constructor for ToDoList class.
   */
  public ToDoList() {
    this.toDoList = new ArrayList<ToDo>();
  }

  /**
   * Add a new ToDo into the collection.
   *
   * @param toDo - ToDo to be added.
   */
  @Override
  public void addToDo(ToDo toDo) {
    this.toDoList.add(toDo);
  }

  /**
   * get ToDoList
   * @return ToDoList, as ArrayList of ToDo
   */
  @Override
  public ArrayList<ToDo> getToDoList() {
    return this.toDoList;
  }

  /**
   * Generate id for toDo
   * @param toDo - toDo to be generated id
   * @return the id, as Integer
   */
  @Override
  public Integer generateId(ToDo toDo) {
    return this.toDoList.indexOf(toDo) + 1;
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
    ToDoList toDoList1 = (ToDoList) o;
    return Objects.equals(toDoList, toDoList1.toDoList);
  }

  /**
   * hashCode method returns the hash code value of the ToDoList class
   * @return hash code value of the ToDoList class, as Integer
   */
  @Override
  public int hashCode() {
    return Objects.hash(toDoList);
  }

  /**
   * toString method for the ToDoList class
   * @return a String representation of the toDoList
   */
  @Override
  public String toString() {
    return "ToDoList{" +
        "toDoList=" + toDoList +
        '}';
  }
}