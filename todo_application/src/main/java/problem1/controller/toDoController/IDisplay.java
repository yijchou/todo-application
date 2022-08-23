package problem1.controller.toDoController;

import problem1.model.IToDoList;

/**
 * An Interface for ToDo display options in the Controller Package
 * */
public interface IDisplay {
  /**
   * filterByIncomplete will filter the list and only include incomplete ToDo's
   * @param listOfToDos to be filtered, as an IToDoList ADT
   * @return filtered list of ToDo items, as an IToDoList object
   * */
  IToDoList filterByIncomplete(IToDoList listOfToDos);

  /**
   * filterByCategory will filter the list and only include ToDo with a particular input category
   * @param listOfToDos to be filtered, as an IToDoList ADT
   * @param category, as a String
   * @return filtered list of ToDo items, as an IToDoList object
   * */
  IToDoList filterByCategory(IToDoList listOfToDos, String category);

  /**
   * sortByDate will sort the ToDo's by ascending date
   * @param listOfToDos to be sorted, as an IToDoList ADT
   * @return sorted list of ToDo items, as an IToDoList object
   * */
  IToDoList sortByDate(IToDoList listOfToDos);

  /**
   * sortByPriority will sort the ToDo's by ascending priority
   * @param listOfToDos to be sorted, as an IToDoList ADT
   * @return sorted list of ToDo items, as an IToDoList object
   * */
  IToDoList sortByPriority(IToDoList listOfToDos);
}
