package problem1.controller.csv;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import problem1.controller.toDoController.Converter;
import problem1.model.IToDoList;
import problem1.model.ToDo;
import problem1.model.ToDoList;

/**
 * The Read CSV Class is responsible for accessing the information in the input CSV. This
 * class stores the file name of the CSV, and a Map of attribute keys, which is used in creating
 * the columns for the CSV data entries. The CSV Reader class implements the Read CSV Interface.
 * */

public class ReadCSV implements IReadCSV{
  private String fileName;
  private Map<Integer, String> keys;

  /**
   * A constructor for the CSV Reader
   * @param fileName of the CSV to be read, as a String.
   * */
  public ReadCSV(String fileName){
    this.fileName = fileName;
    this.keys = new HashMap<>();
  }

  /**
   * getter for the fileName
   * @return fileName as a String
   * */
  private String getFileName() {
    return fileName;
  }

  /**
   * getter for the keys
   * @return keys as a Map of Integer to String items
   * */
  public Map<Integer, String> getKeys() {
    return keys;
  }

  /**
   * createKeys is a private helper method that will create keys using a standard column format.
   * @param s should be the first data entry in the file, stored as a String
   * */
  private void createKeys(String s){
    // turn input string into an array of attribute keys:
    String[] k = this.reformat(s);
    // use for loop to assign index values to each key:
    for (int i = 0; i < k.length; i++) {
      this.keys.put(i, k[i]);
    }
  }

  /**
   * reformat is a private helper method that will re-organize a String data entry into an Array
   * of String data elements.
   * @param s is a single data entry stored in a string
   * @return reformatted data, as a String array
   * */
  private String[] reformat(String s){
    Integer IGNORE_CHAR = 2;
    return s.substring(IGNORE_CHAR).replace("\",\"", "-split here-").replace(
        "\"", "").replace("?", "null").split(
        "-split here-");
  }

  /**
   * setIDForList is a private helper method that will generate unique IDs for every ToDo item
   * in the ToDo list
   * @param list as an IToDoList ADT
   * */
  private void setIDForList(IToDoList list){
    for(ToDo item: list.getToDoList()){
      item.setID(list.generateId(item));
    }
  }

  /**
   * read method will extract all the data entries from a CSV file, reformat and re-organize
   * the data, and then return it as a ToDoList
   * @return reformatted and re-organized data entries, as a TodoList ADT
   * @throws GenericCSVException for file handling errors.
   * */
  @Override
  public IToDoList read() throws GenericCSVException{
    IToDoList list = new ToDoList();
    String s;
    try{                                      // start processing file
      BufferedReader r = new BufferedReader(new FileReader(this.fileName));
      s = r.readLine();
      if (s == null){
        // if first line = null, display message to user:
        throw new GenericCSVException("Can not read an empty CSV File.");
      }
      // else, use first line of the file to create keys:
      this.createKeys(s);
      s = r.readLine();     // continue reading
      while (s != null) {
        Converter current = new Converter(this.reformat(s));
        list.addToDo(current.convert());
        s = r.readLine();
      }
      r.close();
      // generate unique id's using list index and todo hashcode
      this.setIDForList(list);
      // return data as a todo list
      return list;
    } catch (FileNotFoundException e) { // handling file not found exceptions:
      throw new GenericCSVException("Could not locate file: " + this.fileName +
          " in this directory.");
    } catch (IOException ioie) {           // handling file input output exceptions:
      throw new GenericCSVException("*** OOPS! An I/O error has occurred: " + ioie.getMessage());
    }
  }

  /**
   * Equals method for the CSV Reader class
   * @param o as another object
   * @return if objects match, as a Boolean.
   * */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ReadCSV)) {
      return false;
    }
    ReadCSV readCSV = (ReadCSV) o;
    return Objects.equals(getFileName(), readCSV.getFileName()) && Objects.equals(
        getKeys(), readCSV.getKeys());
  }

  /**
   * hashCode method for the CSV Reader class
   * @return hash code value of the parameters, as an Integer
   * */
  @Override
  public int hashCode() {
    return Objects.hash(getFileName(), getKeys());
  }

  /**
   * toString method for the CSV Reader class
   * @return String representation of the reader class, as an Integer
   * */
  @Override
  public String toString() {
    return "ReadCSV{" +
        "fileName='" + fileName + '\'' +
        ", keys=" + keys +
        '}';
  }
}