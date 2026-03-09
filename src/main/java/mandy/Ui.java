package mandy;

import java.util.Scanner;

/**
 * Handles user interface interactions.
 * This class is responsible for reading user input and displaying messages to the console.
 */
public class Ui {
    private Scanner scanner;
    private final String separator = "____________________________________________________________";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hiiii I'm Mandy :D try typing \"?\" for a list of commands!");
        showSeparator();
    }

    public void showSeparator() {
        System.out.println(separator);
    }

    public void showLine() {
        showSeparator();
    }

    public void showError(String message) {
        System.out.println(" OOPS!!! " + message);
    }

    public void showGoodbye() {
        System.out.println("Byeeeeee, see u soon ;)");
    }

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

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskMarked(Task task, boolean isDone) {
        if (isDone) {
            System.out.println(" Nice! I've marked this task as done:");
        } else {
            System.out.println(" OK, I've marked this task as not done yet:");
        }
        System.out.println("   " + task);
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + totalTasks + " tasks in the list.");
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void close() {
        scanner.close();
    }
}