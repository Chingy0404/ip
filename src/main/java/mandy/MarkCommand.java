package mandy;

/**
 * Represents a command to mark or unmark a task.
 * This command changes the completion status of a task at a specified index.
 */
public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    /**
     * Constructs a MarkCommand with the given task index and desired completion status.
     *
     * @param index the zero-based index of the task to mark/unmark
     * @param isDone true to mark as done, false to mark as not done
     */
    public MarkCommand(int index, boolean isDone) {
        this.index = index;
        this.isDone = isDone;
    }

    /**
     * Updates the completion status of the task at the specified index,
     * displays a confirmation message, and saves the updated task list to storage.
     *
     * @param tasks the task list containing the task to update
     * @param ui the user interface for displaying the confirmation
     * @param storage the storage for persisting the updated task list
     * @throws MandyException if the index is out of bounds
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MandyException {
        if (isDone) {
            tasks.markAsDone(index);
        } else {
            tasks.markAsNotDone(index);
        }
        ui.showTaskMarked(tasks.get(index), isDone);
        storage.saveTasks(tasks.getTasks());
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
