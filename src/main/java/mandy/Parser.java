package mandy;

/**
 * Parses user input strings into corresponding Command objects.
 * This class handles the recognition of command keywords and their arguments.
 */
public class Parser {
    /**
     * Parses the full user input and returns the appropriate Command.
     * The input is trimmed and matched against known command patterns.
     *
     * @param fullCommand the raw user input string
     * @return a Command object corresponding to the user's intent
     * @throws MandyException if the input is empty, unrecognized, or malformed
     */
    public static Command parse(String fullCommand) throws MandyException {
        String trimmed = fullCommand.trim();
        if (trimmed.isEmpty()) {
            throw new MandyException("I'm sorry, but I don't know what that means :-(");
        }

        if (trimmed.equals("bye")) {
            return new ExitCommand();
        } else if (trimmed.equals("list")) {
            return new ListCommand();
        } else if (trimmed.equals("?")) {
            return new HelpCommand();
        } else if (trimmed.startsWith("mark ")) {
            return parseMark(trimmed, true);
        } else if (trimmed.startsWith("unmark ")) {
            return parseMark(trimmed, false);
        } else if (trimmed.startsWith("delete ")) {
            return parseDelete(trimmed);
        } else if (trimmed.startsWith("todo")) {
            return parseTodo(trimmed);
        } else if (trimmed.startsWith("deadline")) {
            return parseDeadline(trimmed);
        } else if (trimmed.startsWith("event")) {
            return parseEvent(trimmed);
        } else if (trimmed.startsWith("schedule ")) {
            return parseSchedule(trimmed);
        } else if (trimmed.startsWith("find ")) {
            return parseFind(trimmed);
        } else {
            throw new MandyException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command parseMark(String input, boolean isDone) throws MandyException {
        try {
            String indexStr = input.substring(isDone ? 5 : 7).trim();
            int index = Integer.parseInt(indexStr) - 1;
            return new MarkCommand(index, isDone);
        } catch (NumberFormatException e) {
            throw new MandyException("Please provide a valid task number.");
        }
    }

    private static Command parseDelete(String input) throws MandyException {
        try {
            String indexStr = input.substring(7).trim();
            int index = Integer.parseInt(indexStr) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new MandyException("Please provide a valid task number.");
        }
    }

    private static Command parseTodo(String input) throws MandyException {
        String rest = input.substring(4).trim();
        if (rest.isEmpty()) {
            throw new MandyException("The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(rest);
        return new AddCommand(todo);
    }

    private static Command parseDeadline(String input) throws MandyException {
        String rest = input.substring(8).trim();
        int byIndex = rest.indexOf("/by ");
        if (byIndex == -1) {
            throw new MandyException("Invalid deadline format. Use: deadline <description> /by <time>");
        }
        String description = rest.substring(0, byIndex).trim();
        String by = rest.substring(byIndex + 4).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new MandyException("The description and by time cannot be empty.");
        }
        try {
            Deadline deadline = new Deadline(description, by);
            return new AddCommand(deadline);
        } catch (java.time.format.DateTimeParseException e) {
            throw new MandyException("Invalid date/time format. Please use formats like yyyy-mm-dd or d/m/yyyy HHmm");
        }
    }

    private static Command parseEvent(String input) throws MandyException {
        String rest = input.substring(5).trim();
        int fromIndex = rest.indexOf("/from ");
        int toIndex = rest.indexOf("/to ");
        if (fromIndex == -1 || toIndex == -1 || toIndex <= fromIndex) {
            throw new MandyException("Invalid event format. Use: event <description> /from <time> /to <time>");
        }
        String description = rest.substring(0, fromIndex).trim();
        String from = rest.substring(fromIndex + 6, toIndex).trim();
        String to = rest.substring(toIndex + 4).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new MandyException("The description, from, and to cannot be empty.");
        }
        try {
            Event event = new Event(description, from, to);
            return new AddCommand(event);
        } catch (java.time.format.DateTimeParseException e) {
            throw new MandyException("Invalid date/time format. Please use formats like yyyy-mm-dd or d/m/yyyy HHmm");
        }
    }

    private static Command parseSchedule(String input) throws MandyException {
        String rest = input.substring(9).trim();
        if (rest.isEmpty()) {
            throw new MandyException("Please provide a date for schedule command.");
        }
        try {
            // Parse as LocalDateTime first, then extract date
            java.time.LocalDateTime dateTime = DateTimeParser.parse(rest);
            java.time.LocalDate date = dateTime.toLocalDate();
            return new ScheduleCommand(date);
        } catch (java.time.format.DateTimeParseException e) {
            throw new MandyException("Invalid date format. Please use formats like yyyy-mm-dd or d/m/yyyy");
        }
    }

    private static Command parseFind(String input) throws MandyException {
        String rest = input.substring(5).trim();
        if (rest.isEmpty()) {
            throw new MandyException("Please provide a keyword to search for.");
        }
        return new FindCommand(rest);
    }
}
