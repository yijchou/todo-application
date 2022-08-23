package problem1.controller.toDoController;

import java.time.LocalDate;
import java.util.Arrays;
import problem1.model.ToDo;

/**
 * The Converter Class is responsible for reformatting an array of String data entries into an
 * individual ToDo object. The Converter implements the I-Converter Interface.
 * */

public class Converter implements IConverter {
  private String[] attributes;
  private static final Integer TEXT_INDEX = 0;
  private static final Integer COMPLETED_INDEX = 1;
  private static final Integer DUE_INDEX = 2;
  private static final Integer PRIORITY_INDEX = 3;
  private static final Integer CATEGORY_INDEX = 4;
  private static final Integer MONTH_INDEX = 0;
  private static final Integer DATE_INDEX = 1;
  private static final Integer YEAR_INDEX = 2;
  private static final Integer DEFAULT_PRIORITY = 3;

  /**
   * The constructor for the Converter class
   * @param attributes stored in an array of String entries
   * */
  public Converter(String[] attributes) {
    this.attributes = attributes;
  }

  /**
   * convert is a private helper method that will reformat a String[] input as a ToDo object
   * @return current todo item, as a ToDo object
   * */
  @Override
  public ToDo convert(){
    Integer priority;
    Boolean completed;
    LocalDate due;
    priority = this.extractPriority(this.attributes[PRIORITY_INDEX]);
    completed = Boolean.parseBoolean(this.attributes[COMPLETED_INDEX]);
    due = this.extractDueDate(this.attributes[DUE_INDEX]);
    ToDo current = new ToDo.ToDoBuilder(this.attributes[TEXT_INDEX]).addCategory(this.attributes
            [CATEGORY_INDEX]).addDue(due).addPriority(priority).addCompleted(completed).build();
    return current;
  }

  /**
   * extractPriority is a private helper method that will extract the Integer priority from a string
   * input
   * @param s as a String input
   * @return the Integer equivalent of the String, or the Default priority value
   * @Throws NumberFormatException for NonDigit/NonInteger characters
   * */
  private Integer extractPriority(String s){
    try{
      return Integer.parseInt(s);
    }
    catch (NumberFormatException priority){
      return DEFAULT_PRIORITY;
    }
  }

  /**
   * extractDueDate is a private helper method that will extract the LocalDate due date from a
   * string input
   * @param s as a String input
   * @return the LocalDate representation of the String input
   * @Throws NumberFormatException for Non-Integer characters
   * */
  private LocalDate extractDueDate(String s){
    Integer month, day, year;
    try{
      String[] date = s.split("/");
      month = Integer.parseInt(date[MONTH_INDEX]);
      day = Integer.parseInt(date[DATE_INDEX]);
      year = Integer.parseInt(date[YEAR_INDEX]);
      return LocalDate.of(year, month, day);
    }
    catch (NumberFormatException due){
      return null;
    }
  }

  /**
   * hashCode method returns the hash code value of the Converter class
   * @return the hash code value of the Converter class, as Integer
   * */
  @Override
  public int hashCode() {
    return Arrays.hashCode(attributes);
  }

  /**
   * equals method checks if two objects are the same
   * @param o as another object
   * @return if objects are the same, as a Boolean.
   * */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Converter)) {
      return false;
    }
    Converter converter = (Converter) o;
    return Arrays.equals(attributes, converter.attributes);
  }

  /**
   * toString method for the Converter class
   * @return a String representation of the default empty constructor
   * */
  @Override
  public String toString() {
    return "Converter{" +
            "attributes=" + Arrays.toString(attributes) +
            '}';
  }
}
