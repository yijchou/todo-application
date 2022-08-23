package problem1.view;

import java.util.Map;
import problem1.controller.command.CommandProcessor;
import problem1.controller.command.InvalidArgumentException;
import problem1.controller.csv.GenericCSVException;
import problem1.controller.csv.ReadCSV;
import problem1.controller.csv.UpdateCSV;
import problem1.controller.toDoController.Display;
import problem1.controller.toDoController.TodoHandler;

/**
 * A Main Class for our Problem 1 Package
 * */
public class Main {
  /**
   * main method
   * @param args input from the users, stored in a String array
   * @throws InvalidArgumentException for handling Command Line errors
   * @throws GenericCSVException for handling CSV file handling errors
   * */
  public static void main(String[] args) throws InvalidArgumentException, GenericCSVException {
    // get commands from user input:
    CommandProcessor cmd = new CommandProcessor(args);

    // process csv file using command line arguments:
    ReadCSV reader = new ReadCSV(cmd.getParameters().get("--csv-file"));  // todo list
    Map<Integer, String> keys = reader.getKeys();

    // complete user input requests in the handler:
    Display displayOptions = new Display();
    TodoHandler handler = new TodoHandler(cmd, reader.read(), displayOptions);

    // update csv with the newly updated ToDoList:
    UpdateCSV updater = new UpdateCSV(cmd.getParameters().get("--csv-file"), handler.getListOfTodos());
    updater.update(keys);
  }
}