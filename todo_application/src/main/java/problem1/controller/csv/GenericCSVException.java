package problem1.controller.csv;

public class GenericCSVException extends Exception {
  /**
   * A Generic CSV Exception for handling exception cases in the CSV Processor class
   * @param message to be displayed to the user, stored as a String
   * */
  public GenericCSVException(String message) {
    super(message);
  }
}
