package problem1.controller.toDoController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import problem1.model.IToDoList;
import problem1.model.ToDo;
import problem1.model.ToDoList;
/**
 * The Display Class is a collection of methods that can be used to filter or sort a pre-existing
 * ToDoList. The Display class implements the IDisplay Interface, and has four display options for
 * the ToDoList.
 * */
public class Display implements IDisplay{
  private static final Integer DEFAULT_HASH_CODE = 31;

  /**
   * An Empty Constructor for the Display class:
   * */
  public Display() {
  }

  /**
   * filterByIncomplete will filter the list and only include incomplete ToDo's
   * @param listOfToDos to be filtered, as an IToDoList ADT
   * @return filtered list of ToDo items, as an IToDoList object
   * */
  @Override
  public IToDoList filterByIncomplete(IToDoList listOfToDos) {
    IToDoList filtered = new ToDoList();
    for(ToDo item: listOfToDos.getToDoList()){
      if(! item.getCompleted()){
        filtered.addToDo(item);
      }
    }
    return filtered;
  }

  /**
   * filterByCategory will filter the list and only include ToDo with a particular input category
   * @param listOfToDos to be filtered, as an IToDoList ADT
   * @param category, as a String
   * @return filtered list of ToDo items, as an IToDoList object
   * */
  @Override
  public IToDoList filterByCategory(IToDoList listOfToDos, String category) {
    IToDoList filtered = new ToDoList();
    for(ToDo item: listOfToDos.getToDoList()){
      if(item.getCategory() == category){
        filtered.addToDo(item);
      }
    }
    return filtered;
  }

  /**
   * sortByDate will sort the ToDo's by ascending date
   * @param listOfToDos to be sorted, as an IToDoList ADT
   * @return sorted list of ToDo items, as an IToDoList object
   * */
  @Override
  public IToDoList sortByDate(IToDoList listOfToDos) {
    ArrayList<ToDo> sorted = listOfToDos.getToDoList();
    Collections.sort(sorted, Comparator.nullsLast(Comparator.comparing(ToDo::getDue,
        Comparator.nullsLast(Comparator.naturalOrder()))));
    return new ToDoList(sorted);
  }

  /**
   * sortByPriority will sort the ToDo's by ascending priority
   * @param listOfToDos to be sorted, as an IToDoList ADT
   * @return sorted list of ToDo items, as an IToDoList object
   * */
  @Override
  public IToDoList sortByPriority(IToDoList listOfToDos) {
    ArrayList<ToDo> sorted = listOfToDos.getToDoList();
    Collections.sort(sorted, Comparator.nullsLast(Comparator.comparing(ToDo::getPriority,
        Comparator.nullsLast(Comparator.naturalOrder()))));
    return new ToDoList(sorted);
  }

  /**
   * hashCode method returns the hash code value of the Display class
   * @return the hash code value of the Display class, as Integer
   * */
  @Override
  public int hashCode() {
    // no fields to hash:
    return DEFAULT_HASH_CODE;
  }

  /**
   * equals method checks if two objects are the same
   * @param o as another object
   * @return if objects are the same, as a Boolean.
   * */
  @Override
  public boolean equals(Object o) {
    if(this == o){
      return true;
    }
    if(o == null || this.getClass() != o.getClass()) {
      return false;
    }
    else return true;
  }

  /**
   * toString method for the Display class
   * @return a String representation of the default empty constructor
   * */
  @Override
  public String toString() {
    return "Display{}";
  }
}
