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
            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            } else if (input.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println(" No tasks stored.");
                } else {
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                }
                System.out.println(separator);
            } else if (input.startsWith("mark ")) {
                try {
                    int index = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks.get(index));
                    } else {
                        System.out.println(" Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Please provide a valid task number.");
                }
                System.out.println(separator);
            } else if (input.startsWith("unmark ")) {
                try {
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsNotDone();
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(index));
                    } else {
                        System.out.println(" Invalid task number.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println(" Please provide a valid task number.");
                }
                System.out.println(separator);
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                if (description.isEmpty()) {
                    System.out.println(" The description of a todo cannot be empty.");
                } else {
                    Todo todo = new Todo(description);
                    tasks.add(todo);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + todo);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                }
                System.out.println(separator);
            } else if (input.startsWith("deadline ")) {
                String rest = input.substring(9).trim();
                int byIndex = rest.indexOf("/by ");
                if (byIndex == -1) {
                    System.out.println(" Invalid deadline format. Use: deadline <description> /by <time>");
                } else {
                    String description = rest.substring(0, byIndex).trim();
                    String by = rest.substring(byIndex + 4).trim();
                    if (description.isEmpty() || by.isEmpty()) {
                        System.out.println(" The description and by time cannot be empty.");
                    } else {
                        Deadline deadline = new Deadline(description, by);
                        tasks.add(deadline);
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + deadline);
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                System.out.println(separator);
            } else if (input.startsWith("event ")) {
                String rest = input.substring(6).trim();
                int fromIndex = rest.indexOf("/from ");
                int toIndex = rest.indexOf("/to ");
                if (fromIndex == -1 || toIndex == -1 || toIndex <= fromIndex) {
                    System.out.println(" Invalid event format. Use: event <description> /from <time> /to <time>");
                } else {
                    String description = rest.substring(0, fromIndex).trim();
                    String from = rest.substring(fromIndex + 6, toIndex).trim();
                    String to = rest.substring(toIndex + 4).trim();
                    if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                        System.out.println(" The description, from, and to cannot be empty.");
                    } else {
                        Event event = new Event(description, from, to);
                        tasks.add(event);
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + event);
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    }
                }
                System.out.println(separator);
            } else {
                // For backward compatibility, treat as a simple task (Todo)
                Task newTask = new Todo(input);
                tasks.add(newTask);
                System.out.println(" added: " + input);
                System.out.println(separator);
            }
        }

        scanner.close();
    }
}
