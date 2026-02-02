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
            } else {
                Task newTask = new Task(input);
                tasks.add(newTask);
                System.out.println(" added: " + input);
                System.out.println(separator);
            }
        }

        scanner.close();
    }
}


