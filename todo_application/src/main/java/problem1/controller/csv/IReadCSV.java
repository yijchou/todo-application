package problem1.controller.csv;
import problem1.model.IToDoList;

/**
 * An Interface for the Read CSV class
 * */
public interface IReadCSV {
  /**
   * read method will extract all the data entries from a CSV file, reformat and re-organize
   * the data, and then return it as a ToDoList
   * @return reformatted and re-organized data entries, as a TodoList ADT
   * @throws GenericCSVException for file handling errors.
   * */
  IToDoList read() throws GenericCSVException;
}
