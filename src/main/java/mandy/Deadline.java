package mandy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task with a due date and time.
 * A deadline has a description and a "by" date/time.
 */
public class Deadline extends Task {
    /** The due date and time of this deadline. */
    protected LocalDateTime by;
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs a Deadline with the given description and due date/time.
     *
     * @param description the description of the deadline
     * @param by the due date and time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline with the given description and a string representation of the due date/time.
     * The string will be parsed using {@link DateTimeParser#parse(String)}.
     *
     * @param description the description of the deadline
     * @param byString the due date/time as a string (supports multiple formats)
     * @throws DateTimeParseException if the string cannot be parsed as a date/time
     */
    public Deadline(String description, String byString) throws DateTimeParseException {
        super(description);
        this.by = DateTimeParser.parse(byString);
    }

    /**
     * Returns a string representation of this deadline for display.
     * Format: "[D][status] description (by: MMM dd yyyy, HH:mm)"
     *
     * @return the display string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DISPLAY_FORMAT) + ")";
    }

    /**
     * Returns a string representation of this deadline for storage in a file.
     * Format: "D | isDone | description | yyyy-MM-dd HH:mm"
     * isDone is 1 if done, 0 otherwise.
     *
     * @return the file storage string
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by.format(STORAGE_FORMAT);
    }

    /**
     * Returns the due date and time of this deadline.
     *
     * @return the due date/time
     */
    public LocalDateTime getBy() {
        return by;
    }
}
