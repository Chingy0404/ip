package mandy;

/**
 * Represents a todo task without any date/time.
 * A todo has only a description.
 */
public class Todo extends Task {
    /**
     * Constructs a Todo with the given description.
     *
     * @param description the description of the todo
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of this todo for display.
     * Format: "[T][status] description"
     *
     * @return the display string
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of this todo for storage in a file.
     * Format: "T | isDone | description"
     * isDone is 1 if done, 0 otherwise.
     *
     * @return the file storage string
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
