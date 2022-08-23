package problem1.controller.toDoController;

/**
 * The Interface for the TodoHandler that contains the main functions for the handler to use
 */
public interface ITodoHandler {

    /**
     * This function can add todos, complete todos and display todos
     */
    void todoAction();

    /**
     * This function checks if the command is in the original commandline input
     * @param command the command that is being looked for
     * @return boolean true or false
     */
    boolean checkCommand(String command);

    /**
     * This function gets the parameters of a command if it exists
     * @param key the command that is the key to the parameter
     * @return the parameter
     */
    String getCommandParameter(String key);
}
