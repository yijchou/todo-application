package problem1.controller.csv;

import java.util.Map;
/**
 * An Interface for the Update CSV class
 * */
public interface IUpdateCSV {

  /**
   * updateCSV method will delete and rewrite the current CSV file with actions done to the
   * new to do list
   * @param keys: columns found in the original csv file, as a Map of Integer to Strings items.
   * @throws GenericCSVException for file writing errors.
   * */
  void update(Map<Integer, String> keys) throws GenericCSVException;
}
