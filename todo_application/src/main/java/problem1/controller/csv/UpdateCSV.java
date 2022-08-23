package problem1.controller.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import problem1.model.IToDoList;
import problem1.model.ToDo;

/**
 * The Update CSV Class is responsible for making updates and changes to the current CSV. This
 * class stores the file name of the CSV to be updated, and an updated list of To Do items, that
 * we will change in the current CSV. The CSV Updater class implements the IUpdateCSV Interface.
 * */
public class UpdateCSV implements IUpdateCSV{
  private String fileName;
  private IToDoList changes;
  /**
   * A constructor for the CSV Updater
   * @param fileName of the CSV to be updated, as a String.
   * @param changes to be made to the current CSV, as an IToDoList ADT.
   * */
  public UpdateCSV(String fileName, IToDoList changes) {
    this.fileName = fileName;
    this.changes = changes;
  }

  /**
   * getter for the fileName
   * @return fileName as a String
   * */
  private String getFileName() {
    return fileName;
  }

  /**
   * getter for the changes
   * @return changes as a ToDoList
   * */
  private IToDoList getChanges() {
    return changes;
  }

  /**
   * updateCSV method will delete and rewrite the current CSV file with actions done to the
   * new to do list
   * @param keys: columns found in the original csv file, as a Map of Integer to Strings items.
   * @throws GenericCSVException for file writing errors.
   * */
  @Override
  public void update(Map<Integer, String> keys) throws GenericCSVException {
    Integer counter = 0;
    this.deleteCurrentCSV();
    try {
      BufferedWriter out = new BufferedWriter(new FileWriter(this.fileName));
      // use keys to write first line:
      out.write(this.reformatKeys(keys));
      // write every data entry:
      for(ToDo item: this.changes.getToDoList()){
        counter ++;
        out.write(this.reformatToDo(counter, item));
      }
      out.close();
    } catch (IOException e) {
      throw new GenericCSVException("A File Input/Output Error has occurred! " + e.getMessage());
    }
  }

  /**
   * reformatKeys is a private helper method that will generate a standard CSV representation of
   * the csv columns
   * @param keys to be reformatted, stored as a Map of Integer to String objects
   * @return String representation of the keys
   * */
  private String reformatKeys(Map<Integer, String> keys){
    String reformattedKeys = "";
    for(int i = 0; i < keys.size(); ++i){
      reformattedKeys = reformattedKeys + "\"\"\"" + keys.get(i) + "\"\"\"";
      // use commas to separate each key
      if(i < keys.size() - 1){
        reformattedKeys = reformattedKeys + ",";
      }
      // except for the last key, insert a new line character:
      else reformattedKeys =  reformattedKeys + "\n";
    }
    return reformattedKeys;
  }

  /**
   * reformatToDo is a private helper method that will generate a standard CSV representation of
   * a ToDo object
   * @param counter to keep track of the To Do item, stored as an Integer
   * @param item to be reformatted, stored as a ToDo object
   * @return String representation of the To Do object
   * */
  private String reformatToDo(Integer counter, ToDo item){
    final String standardFormat = "\"\"\"";
    String line = counter + "," + standardFormat + item.getText() + standardFormat + "," +
        standardFormat + item.getCompleted() + standardFormat + ",";
    // handle optional due date:
    if(item.getDue() != null){
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/YYYY");
      line = line + standardFormat + formatter.format(item.getDue()) + standardFormat + ",";
    }
    else {
      line = line + standardFormat + "?" + standardFormat + ",";
    }
    // add priority
    line = line + standardFormat + item.getPriority() + standardFormat + ",";
    // handle optional category
    line = line + standardFormat + item.getCategory() + standardFormat + "\n";
    return line.replace("null", "?");
  }

  /**
   * deleteCurrentCSV will delete the input file, if found.
   * @throws GenericCSVException for handling file deletion errors.
   * */
  private void deleteCurrentCSV() throws GenericCSVException{
    try {
      Files.deleteIfExists(Paths.get(this.fileName));
    } catch (IOException e) {
      throw new GenericCSVException("A File Input/Output Error has occurred! " + e.getMessage());
    }
  }

  /**
   * Equals method for the CSV updater class
   * @param o as another object
   * @return if objects match, as a Boolean
   * */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof UpdateCSV)) {
      return false;
    }
    UpdateCSV updateCSV = (UpdateCSV) o;
    return Objects.equals(getFileName(), updateCSV.getFileName())
        && Objects.equals(getChanges(), updateCSV.getChanges());
  }

  /**
   * hashCode method for the CSV updater class
   * @return hash value of the class parameters, as an Integer
   * */
  @Override
  public int hashCode() {
    return Objects.hash(getFileName(), getChanges());
  }

  /**
   * toString method for the CSV updater class
   * @return String representation of the class, as a String
   * */
  @Override
  public String toString() {
    return "UpdateCSV{" +
        "fileName='" + fileName + '\'' +
        ", changes=" + changes +
        '}';
  }
}