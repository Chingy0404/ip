package mandy;

/**
 * Represents a command to list all tasks.
 * This command displays all tasks currently stored in the task list.
 */
public class ListCommand extends Command {
    /**
     * Displays the list of tasks via the user interface.
     *
     * @param tasks the task list to display
     * @param ui the user interface for displaying the task list
     * @param storage the storage (unused)
     * @throws MandyException if the task list is empty
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MandyException {
        ui.showTaskList(tasks);
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
