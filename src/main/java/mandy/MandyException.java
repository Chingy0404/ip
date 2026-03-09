package mandy;

/**
 * Represents an exception specific to the Mandy chatbot.
 * This exception is thrown when an error occurs during command parsing or execution.
 */
public class MandyException extends Exception {
    /**
     * Constructs a MandyException with the specified detail message.
     *
     * @param message the detail message
     */
    public MandyException(String message) {
        super(message);
    }
}
