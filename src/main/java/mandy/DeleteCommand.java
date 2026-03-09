package mandy;

/**
 * Represents a command to delete a task.
 * This command removes a task at a specified index from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a DeleteCommand with the given task index.
     *
     * @param index the zero-based index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task at the specified index, displays a confirmation message,
     * and saves the updated task list to storage.
     *
     * @param tasks the task list containing the task to delete
     * @param ui the user interface for displaying the confirmation
     * @param storage the storage for persisting the updated task list
     * @throws MandyException if the index is out of bounds
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MandyException {
        Task removed = tasks.delete(index);
        ui.showTaskDeleted(removed, tasks.size());
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
