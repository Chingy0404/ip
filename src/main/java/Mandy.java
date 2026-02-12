import java.util.Scanner;
import java.util.ArrayList;

public class Mandy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String separator = "____________________________________________________________";
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello! I'm Mandy");
        System.out.println("What can I do for you?");
        System.out.println(separator);

        while (true) {
            String input = scanner.nextLine();
            System.out.println(separator);
            try {
                if (input.equals("bye")) {
                    System.out.println("Byeeeeee, see u soon ;)");
                    System.out.println(separator);
                    break;
                } else if (input.equals("list")) {
                    handleList(tasks);
                    System.out.println(separator);
                } else if (input.startsWith("mark ")) {
                    handleMark(input, tasks);
                    System.out.println(separator);
                } else if (input.startsWith("unmark ")) {
                    handleUnmark(input, tasks);
                    System.out.println(separator);
                } else if (input.startsWith("todo")) {
                    handleTodo(input, tasks);
                    System.out.println(separator);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input, tasks);
                    System.out.println(separator);
                } else if (input.startsWith("event")) {
                    handleEvent(input, tasks);
                    System.out.println(separator);
                } else {
                    throw new MandyException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (MandyException e) {
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println(separator);
            }
        }

        scanner.close();
    }

    private static void handleList(ArrayList<Task> tasks) throws MandyException {
        if (tasks.isEmpty()) {
            throw new MandyException("No tasks stored.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    private static void handleMark(String input, ArrayList<Task> tasks) throws MandyException {
        try {
            int index = Integer.parseInt(input.substring(5).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new MandyException("Invalid task number.");
            }
            tasks.get(index).markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + tasks.get(index));
        } catch (NumberFormatException e) {
            throw new MandyException("Please provide a valid task number.");
        }
    }

    private static void handleUnmark(String input, ArrayList<Task> tasks) throws MandyException {
        try {
            int index = Integer.parseInt(input.substring(7).trim()) - 1;
            if (index < 0 || index >= tasks.size()) {
                throw new MandyException("Invalid task number.");
            }
            tasks.get(index).markAsNotDone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + tasks.get(index));
        } catch (NumberFormatException e) {
            throw new MandyException("Please provide a valid task number.");
        }
    }

    private static void handleTodo(String input, ArrayList<Task> tasks) throws MandyException {
        String rest = input.substring(4).trim(); // "todo" length = 4
        if (rest.isEmpty()) {
            throw new MandyException("The description of a todo cannot be empty.");
        }
        Todo todo = new Todo(rest);
        tasks.add(todo);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + todo);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleDeadline(String input, ArrayList<Task> tasks) throws MandyException {
        String rest = input.substring(8).trim(); // "deadline" length = 8
        int byIndex = rest.indexOf("/by ");
        if (byIndex == -1) {
            throw new MandyException("Invalid deadline format. Use: deadline <description> /by <time>");
        }
        String description = rest.substring(0, byIndex).trim();
        String by = rest.substring(byIndex + 4).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new MandyException("The description and by time cannot be empty.");
        }
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + deadline);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void handleEvent(String input, ArrayList<Task> tasks) throws MandyException {
        String rest = input.substring(5).trim(); // "event" length = 5
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
        Event event = new Event(description, from, to);
        tasks.add(event);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + event);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
    }
}

