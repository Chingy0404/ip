package mandy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Represents a command to show tasks scheduled on a specific date.
 * This command filters deadlines due on the given date and events that occur on that date.
 */
public class ScheduleCommand extends Command {
    private LocalDate date;

    /**
     * Constructs a ScheduleCommand with the given date.
     *
     * @param date the date to filter tasks by
     */
    public ScheduleCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * Filters tasks that are scheduled on the specified date and displays the results.
     * Includes deadlines due on that date and events that start, end, or span the date.
     *
     * @param tasks the task list to filter
     * @param ui the user interface for displaying the filtered tasks
     * @param storage the storage (unused)
     * @throws MandyException if an error occurs while accessing tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MandyException {
        ArrayList<Task> filtered = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                LocalDateTime by = deadline.getBy();
                if (by.toLocalDate().equals(date)) {
                    filtered.add(task);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                LocalDateTime from = event.getFrom();
                LocalDateTime to = event.getTo();
                if (from.toLocalDate().equals(date) || to.toLocalDate().equals(date) ||
                    (from.toLocalDate().isBefore(date) && to.toLocalDate().isAfter(date))) {
                    filtered.add(task);
                }
            }
        }
        if (filtered.isEmpty()) {
            ui.showMessage("No tasks scheduled on " + date.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy")));
        } else {
            ui.showMessage("Tasks scheduled on " + date.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy")) + ":");
            for (int i = 0; i < filtered.size(); i++) {
                ui.showMessage(" " + (i + 1) + "." + filtered.get(i));
            }
        }
    }

    /**
     * Returns false as this is not an exit command.
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
