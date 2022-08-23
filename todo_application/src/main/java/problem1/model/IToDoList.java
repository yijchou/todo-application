package problem1.model;


import java.util.ArrayList;

/**
 * An interface for the ToDoList class
 */
public interface IToDoList {

  /**
   * Add a new ToDo into the collection.
   * @param toDo - ToDo to be added.
   */
  void addToDo(ToDo toDo);

  /**
   * Generate id for toDo
   * @param toDo - toDo to be generated id
   * @return the id, as Integer
   */
  Integer generateId(ToDo toDo);

  /**
   * get ToDoList
   * @return ToDoList, as ArrayList of ToDo
   */
  ArrayList<ToDo> getToDoList();
}