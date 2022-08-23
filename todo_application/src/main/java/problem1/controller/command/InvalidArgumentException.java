package problem1.controller.command;

/**
 * When a user provides an illegal combination of inputs, the program should exit with a helpful error message,
 * and a short explanation of how to use the program along with examples.
 */
public class InvalidArgumentException extends Exception{
    public InvalidArgumentException(String message) {
        super(message);
    }
}
