package problem1.controller.command;

import java.util.*;
import java.time.LocalDate;
import java.lang.String;
/**
 * This class parses up the command line that is input from the user.
 * Some options take arguments, they must immediately follow the command.
 * Some options require other options to also be present.
 * A user can request the program to perform all 3 tasks (add, complete, & display
 * in one run of the program.
 * They may only add one To-do at a time, but they may complete multiple
 * todos by repeating the --complete-to-do option for each To-do to complete
 * Accept the command line arguments in any order.
 */
public class CommandProcessor implements ICommandProcessor {

    // Accepted Command Line Arguments
    public static final String CSV_FILE = "--csv-file";
    public static final String ADD_TODO = "--add-todo";
    public static final String TODO_TEXT = "--todo-text";
    public static final String COMPLETED = "--completed";
    public static final String DUE = "--due";
    public static final String PRIORITY = "--priority";
    public static final String CATEGORY = "--category";
    public static final String COMPLETE_TODO = "--complete-todo";
    public static final String DISPLAY = "--display";
    public static final String SHOW_INCOMPLETE = "--show-incomplete";
    public static final String SHOW_CATEGORY = "--show-category";
    public static final String SORT_BY_DATE = "--sort-by-date";
    public static final String SORT_BY_PRIORITY = "--sort-by-priority";

    // A HashSet of Strings that holds the commands
    private HashSet<String> commands = new HashSet<>();
    // A HashMap of String Key and String Value that holds parameters
    private HashMap<String, String> parameters = new HashMap<>();

    /**
     * Creating A Command Processor with cmdline line input
     * @param cmdline the input from the command line from the user
     * @throws InvalidArgumentException is thrown if the process arguments catches an error
     */
    public CommandProcessor(String[] cmdline) throws InvalidArgumentException {
        this.commands = commands;
        this.parameters = parameters;

        this.processArgs(cmdline);
    }

    /**
     * Empty Constructor for the Command Processor
     */
    public CommandProcessor()  {
        this.commands = commands;
        this.parameters = parameters;
    }

    // The list of accepted commands for the command line
    String[] listOfCommands = new String[]{CSV_FILE, ADD_TODO, TODO_TEXT, COMPLETED, DUE, PRIORITY, CATEGORY,
        COMPLETE_TODO, DISPLAY, SHOW_INCOMPLETE, SHOW_CATEGORY, SORT_BY_DATE, SORT_BY_PRIORITY};
    List<String> acceptedCommands = new ArrayList<>(Arrays.asList(listOfCommands));

    /**
     * take in what was written in the command line and populate command and parameter
     * @param cmdline the string from main
     * @throws InvalidArgumentException is thrown when the input violates rules for arguments
     */
    @Override
    public void processArgs(String[] cmdline) throws InvalidArgumentException {
        for (int i = 0; i < (cmdline.length); i++) {
            try {
                switch (cmdline[i]) {
                    case CSV_FILE:
                        this.commands.add(cmdline[i]);
                        if (this.validateArgument(cmdline, i, cmdline.length)) {
                            this.parameters.put(CSV_FILE, cmdline[i + 1]);
                        } else {
                            throw new InvalidArgumentException("CSV File requires an argument!");
                        }
                        i++;
                        break;
                    case ADD_TODO:
                    case COMPLETED:
                    case DISPLAY:
                        this.commands.add(cmdline[i]);
                        break;
                    case TODO_TEXT:
                        this.commands.add(cmdline[i]);
                        if (this.validateArgument(cmdline, i, cmdline.length)) {
                            this.parameters.put(TODO_TEXT, cmdline[i + 1]);
                        } else {
                            throw new InvalidArgumentException("Todo Text requires a description!");
                        }
                        i++;
                        break;
                    case DUE:
                        // FORMAT = 01/02/2022
                        this.commands.add(cmdline[i]);
                        if (this.validateArgument(cmdline, i, cmdline.length)) {
                            if (this.DateValidate(cmdline[i + 1])) {
                                this.parameters.put(DUE, cmdline[i + 1]);
                            } else {
                                throw new InvalidArgumentException("Invalid Date! The format must be mm/dd/yyyy.");
                            }
                        } else {
                            throw new InvalidArgumentException("A date is needed!");
                        }
                        i++;
                        break;
                    case PRIORITY:
                        this.commands.add(cmdline[i]);
                        if (this.validateArgument(cmdline, i, cmdline.length) && (this.PriorityValidate(cmdline[i + 1]))) {
                            this.parameters.put(PRIORITY, cmdline[i + 1]);
                        } else {
                            throw new InvalidArgumentException("Priority needs an following value of 1, 2, or 3!");
                        }
                        i++;
                        break;
                    case CATEGORY:
                        this.commands.add(cmdline[i]);
                        if (this.validateArgument(cmdline, i, cmdline.length)) {
                            this.parameters.put(CATEGORY, cmdline[i + 1]);
                        } else {
                            throw new InvalidArgumentException("Please name the Category!");
                        }
                        i++;
                        break;
                    case COMPLETE_TODO:
                        this.commands.add(cmdline[i]);
                        if (this.validateArgument(cmdline, i, cmdline.length) && this.IDValidate(cmdline[i + 1])) {
                            this.parameters.put(COMPLETE_TODO, cmdline[i + 1]);
                        } else {
                            throw new InvalidArgumentException("Complete todo needs a TODO ID to follow!");
                        }
                        i++;
                        break;
                    case SHOW_CATEGORY:
                        this.commands.add(cmdline[i]);
                        if (commands.contains(DISPLAY)) {
                            if (this.validateArgument(cmdline, i, cmdline.length)) {
                                this.parameters.put(SHOW_CATEGORY, cmdline[i + 1]);
                            } else {
                                throw new InvalidArgumentException("A category name needs to be provided!");
                            }
                        } else {
                            throw new InvalidArgumentException("--display needs to be provided!");
                        }
                        i++;
                        break;
                    case SHOW_INCOMPLETE:
                    case SORT_BY_DATE:
                    case SORT_BY_PRIORITY:
                        this.commands.add(cmdline[i]);
                        if (!commands.contains(DISPLAY)){
                            throw new InvalidArgumentException("--display needs to be provided!");
                        }
                        break;
                    default:
                        throw new InvalidArgumentException("Unknown Argument!");
                }
            } catch (IndexOutOfBoundsException e) {
                throw new InvalidArgumentException("Argument doesn't follow a valid value.");
            }

        }
        // CSV File required
        if(!(this.commands.contains(CSV_FILE))){
            throw new InvalidArgumentException("A CSV File is needed!");
        }
        // ADD TODOs needs TODOs TEXT
        if (this.commands.contains(ADD_TODO)){
            if(!TODOValidate()){
                throw new InvalidArgumentException("TODO needs a description!");
            }
        }
        // Can't have SORT_BY_DATE & SORT_BY_PRIORITY
        if(this.commands.contains(SORT_BY_DATE) && this.commands.contains(SORT_BY_PRIORITY)){
            throw new InvalidArgumentException("You cannot sort by date and priority!");
        }
    }

    /**
     * Validate that the add todos has text that follows
     * @return boolean value
     */
    private boolean TODOValidate () {
        return ((this.commands.contains(ADD_TODO) && (this.commands.contains(TODO_TEXT))));
    }

    /**
     * Validate the priority value, needs to be 1, 2, or 3
     * @param priority the input
     * @return boolean true or false
     */
    private boolean PriorityValidate(String priority){
        return(priority.equals("1") || priority.equals("2") || priority.equals("3"));
    }

    /**
     * Test that the id inputed is an integer
     * @param todo_id the ID from user
     * @return boolean value true or false
     */
    private boolean IDValidate(String todo_id){
        try {
            Integer d = Integer.parseInt(todo_id);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Validates the date being within month, day & year parameters
     * @param input the string date input
     * @return boolean value true or false
     */
    private boolean DateValidate (String input) {
        // correct format: 01/02/2022
        Integer dateArray[] = convertMonthStringToInteger(input);

        if (dateArray[0] < 1 || dateArray[0] > 13) {
            return Boolean.FALSE;
        }
        if (dateArray[1] < 1 || dateArray[1] > 31) {
            return Boolean.FALSE;
        }
        if (dateArray[2] < 2020 || dateArray[2] > 3000) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }

    }

    /**
     * Convert the date string input into an integer array
     * @param dateInput the string date input
     * @return the integer array with same data
     */
    public Integer[] convertMonthStringToInteger(String dateInput){
        // correct format: 01/02/2022

        Integer[] dateArray = new Integer[10];

        char[] full_date = dateInput.toCharArray();
        StringBuilder month = new StringBuilder();
        StringBuilder day = new StringBuilder();
        StringBuilder year = new StringBuilder();

        month.append(full_date[0]);
        month.append(full_date[1]);
        int month_int = Integer.parseInt(String.valueOf(month));
        dateArray[0] = month_int;


        day.append(full_date[3]);
        day.append(full_date[4]);
        int day_int = Integer.parseInt(String.valueOf(day));
        dateArray[1] = day_int;

        year.append(full_date[6]);
        year.append(full_date[7]);
        year.append(full_date[8]);
        year.append(full_date[9]);
        int year_int = Integer.parseInt(String.valueOf(year));
        dateArray[2] = year_int;

        return dateArray;
    }


    /**
     * Checks that the arguments following the commands that require them exist
     * @param arg the argument following the command
     * @return True if the argument is valid and False if it is not
     */
    private boolean validateArgument(String[] arg, Integer index, Integer length) {
        // can't be nothing
        Integer endOfCmd = index + 1;
        if (endOfCmd.equals(length)){
            return Boolean.FALSE;
        }
        // can't be another command
        if (acceptedCommands.contains(arg[index + 1])){
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }

    /**
     * Get the commands that we built in the process args method
     * @return the HasSet of commands
     */
    public HashSet<String> getCommands() {
        return this.commands;
    }

    /**
     * Get the parameters that we built in the process args method
     * @return the HashMap pf parameters with command keys
     */
    public HashMap<String, String> getParameters() {
        return this.parameters;
    }

    /**
     * equals method checks if two objects are the same
     * @param o as another object
     * @return if objects are the same, as a Boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommandProcessor)) return false;
        CommandProcessor that = (CommandProcessor) o;
        return Objects.equals(commands, that.commands) && Objects.equals(parameters, that.parameters);
    }

    /**
     * hashCode method returns the hash code value of the CommandProcessor class
     * @return hash code value of the CommandProcessor class, as Integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(commands, parameters);
    }

    /**
     * toString method for the CommandProcessor class
     * @return a String representation of the CommandProcessor
     */
    @Override
    public String toString() {
        return "CommandProcessor{" +
            "commands=" + commands +
            ", parameters=" + parameters +
            '}';
    }
}
