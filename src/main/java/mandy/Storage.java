package mandy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles loading and saving tasks to a file.
 * This class manages persistence of tasks across sessions.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath the path to the file where tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     * If the file or directory does not exist, returns an empty list.
     * If the file is corrupted (invalid format), skips the corrupted line.
     */
    public ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        // Ensure directory exists
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (IllegalArgumentException e) {
                    System.err.println("Warning: Skipping corrupted line: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Warning: File not found: " + filePath);
        }
        return tasks;
    }

    /**
     * Parses a single line from the storage file into a Task object.
     * Format:
     *   T | isDone | description
     *   D | isDone | description | by
     *   E | isDone | description | from | to
     * isDone is 1 for done, 0 for not done.
     */
    private Task parseTask(String line) throws IllegalArgumentException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid number of fields");
        }

        String type = parts[0].trim();
        int isDone = Integer.parseInt(parts[1].trim());
        String description = parts[2].trim();

        switch (type) {
        case "T":
            if (parts.length != 3) {
                throw new IllegalArgumentException("Todo must have exactly 3 fields");
            }
            Todo todo = new Todo(description);
            if (isDone == 1) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            if (parts.length != 4) {
                throw new IllegalArgumentException("Deadline must have exactly 4 fields");
            }
            String by = parts[3].trim();
            try {
                Deadline deadline = new Deadline(description, by);
                if (isDone == 1) {
                    deadline.markAsDone();
                }
                return deadline;
            } catch (java.time.format.DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format in deadline: " + by);
            }
        case "E":
            if (parts.length != 5) {
                throw new IllegalArgumentException("Event must have exactly 5 fields");
            }
            String from = parts[3].trim();
            String to = parts[4].trim();
            try {
                Event event = new Event(description, from, to);
                if (isDone == 1) {
                    event.markAsDone();
                }
                return event;
            } catch (java.time.format.DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid date format in event: " + from + " or " + to);
            }
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }

    /**
     * Saves the given list of tasks to the file.
     * Creates the directory if it doesn't exist.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        File file = new File(filePath);
        File parentDir = file.getParentFile();

        // Ensure directory exists
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }
}