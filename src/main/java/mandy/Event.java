package mandy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end date/time.
 * An event has a description, a "from" date/time, and a "to" date/time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");
    private static final DateTimeFormatter STORAGE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructs an Event with the given description, start date/time, and end date/time.
     *
     * @param description the description of the event
     * @param from the start date and time
     * @param to the end date and time
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an Event with the given description and string representations of the start and end date/times.
     * The strings will be parsed using {@link DateTimeParser#parse(String)}.
     *
     * @param description the description of the event
     * @param fromString the start date/time as a string (supports multiple formats)
     * @param toString the end date/time as a string (supports multiple formats)
     * @throws DateTimeParseException if either string cannot be parsed as a date/time
     */
    public Event(String description, String fromString, String toString) throws DateTimeParseException {
        super(description);
        this.from = DateTimeParser.parse(fromString);
        this.to = DateTimeParser.parse(toString);
    }

    /**
     * Returns a string representation of this event for display.
     * Format: "[E][status] description (from: MMM dd yyyy, HH:mm to: MMM dd yyyy, HH:mm)"
     *
     * @return the display string
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DISPLAY_FORMAT) + " to: " + to.format(DISPLAY_FORMAT) + ")";
    }

    /**
     * Returns a string representation of this event for storage in a file.
     * Format: "E | isDone | description | yyyy-MM-dd HH:mm | yyyy-MM-dd HH:mm"
     * isDone is 1 if done, 0 otherwise.
     *
     * @return the file storage string
     */
    @Override
    public String toFileString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from.format(STORAGE_FORMAT) + " | " + to.format(STORAGE_FORMAT);
    }

    /**
     * Returns the start date and time of this event.
     *
     * @return the start date/time
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date and time of this event.
     *
     * @return the end date/time
     */
    public LocalDateTime getTo() {
        return to;
    }
}
