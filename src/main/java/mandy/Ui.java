package mandy;

import java.util.Scanner;

/**
 * Handles user interface interactions.
 * This class is responsible for reading user input and displaying messages to the console.
 */
public class Ui {
    /** Scanner for reading user input from the console. */
    private Scanner scanner;
    /** Separator line used for visual formatting. */
    private final String separator = "____________________________________________________________";

    /**
     * Constructs a Ui object and initializes the scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads a command from the user.
     *
     * @return the user's input as a string
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message and separator.
     */
    public void showWelcome() {
        System.out.println("Hiiii I'm Mandy :D try typing \"?\" for a list of commands!");
        showSeparator();
    }

    /**
     * Displays the separator line.
     */
    public void showSeparator() {
        System.out.println(separator);
    }

    /**
     * Displays a line (same as separator).
     */
    public void showLine() {
        showSeparator();
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
    }

    /**
     * Displays the goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Byeeeeee, see u soon ;)");
    }

    /**
     * Displays the help message listing all available commands.
     */
    public void showHelp() {
        System.out.println(" Here are the commands you can use:");
        System.out.println("   bye - Exit the program");
        System.out.println("   list - List all tasks");
        System.out.println("   mark <number> - Mark a task as done");
        System.out.println("   unmark <number> - Mark a task as not done");
        System.out.println("   delete <number> - Delete a task");
        System.out.println("   todo <description> - Add a todo task");
        System.out.println("   deadline <description> /by <time> - Add a deadline task");
        System.out.println("   event <description> /from <time> /to <time> - Add an event task");
        System.out.println("   schedule <date> - Show tasks scheduled on a specific date");
        System.out.println("   find <keyword> - Find tasks by keyword in description");
        System.out.println("   ? - Show this help message");
        System.out.println(" Date formats: yyyy-mm-dd, d/m/yyyy, d/m/yyyy HHmm, etc.");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks the task list to display
     * @throws MandyException if the task list is empty
     */
    public void showTaskList(TaskList tasks) throws MandyException {
        if (tasks.isEmpty()) {
            throw new MandyException("No tasks stored.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    /**
     * Displays a confirmation that a task has been added.
     *
     * @param task the added task
     * @param totalTasks the new total number of tasks
     */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a confirmation that a task has been marked or unmarked.
     *
     * @param task the task that was marked/unmarked
     * @param isDone true if marked as done, false if marked as not done
     */
    public void showTaskMarked(Task task, boolean isDone) {
        if (isDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task);
    }

    /**
     * Displays a confirmation that a task has been deleted.
     *
     * @param task the deleted task
     * @param totalTasks the new total number of tasks
     */
    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a generic message.
     *
     * @param message the message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}