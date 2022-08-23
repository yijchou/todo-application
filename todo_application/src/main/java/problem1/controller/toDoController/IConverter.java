package problem1.controller.toDoController;

import problem1.model.ToDo;

/**
 * An Interface for the Converter class
 * */
public interface IConverter {

  /**
   * convert is a private helper method that will reformat a String[] as a ToDo object
   * @return current todo item, as a ToDo object
   * */
  ToDo convert();

}
