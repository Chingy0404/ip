package mandy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

/**
 * Parses date/time strings into LocalDateTime objects.
 * Supports multiple date/time formats for flexible user input.
 */
public class DateTimeParser {
    private static final List<DateTimeFormatter> DATE_TIME_FORMATTERS = Arrays.asList(
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
        DateTimeFormatter.ofPattern("d-M-yyyy HH:mm"),
        DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")
    );
    
    private static final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
        DateTimeFormatter.ofPattern("yyyy-MM-dd"),
        DateTimeFormatter.ofPattern("d/M/yyyy"),
        DateTimeFormatter.ofPattern("d-M-yyyy"),
        DateTimeFormatter.ofPattern("MMM d yyyy")
    );

    /**
     * Parses a date/time string into a LocalDateTime object.
     * Tries multiple date/time formats, falling back to date-only formats (with midnight as default time).
     *
     * @param dateTimeString the string to parse
     * @return the parsed LocalDateTime
     * @throws DateTimeParseException if the string cannot be parsed with any supported format
     */
    public static LocalDateTime parse(String dateTimeString) throws DateTimeParseException {
        String trimmed = dateTimeString.trim();
        
        // First try date-time formatters
        for (DateTimeFormatter formatter : DATE_TIME_FORMATTERS) {
            try {
                return LocalDateTime.parse(trimmed, formatter);
            } catch (DateTimeParseException e) {
                // continue to next formatter
            }
        }
        
        // Then try date-only formatters
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                LocalDate date = LocalDate.parse(trimmed, formatter);
                return LocalDateTime.of(date, LocalTime.MIDNIGHT);
            } catch (DateTimeParseException e) {
                // continue to next formatter
            }
        }
        
        throw new DateTimeParseException("Unable to parse date/time: " + dateTimeString, dateTimeString, 0);
    }

    /**
     * Formats a LocalDateTime for display to the user.
     * Format: "MMM dd yyyy, HH:mm" (e.g., "Dec 31 2025, 23:59")
     *
     * @param dateTime the date/time to format
     * @return the formatted string
     */
    public static String formatForDisplay(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"));
    }

    /**
     * Formats a LocalDateTime for storage in a file.
     * Format: "yyyy-MM-dd HH:mm" (e.g., "2025-12-31 23:59")
     *
     * @param dateTime the date/time to format
     * @return the formatted string
     */
    public static String formatForStorage(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
