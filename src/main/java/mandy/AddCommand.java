package mandy;

/**
 * Represents a command to add a task.
 * This command adds a new task to the task list and persists it to storage.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructs an AddCommand with the given task.
     *
     * @param task the task to add
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list, displays a confirmation message,
     * and saves the updated task list to storage.
     *
     * @param tasks the task list to add the task to
     * @param ui the user interface for displaying the confirmation
     * @param storage the storage for persisting the updated task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
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
