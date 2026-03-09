package mandy;

/**
 * Represents a command to exit the chatbot.
 * This command displays a goodbye message and signals the chatbot to terminate.
 */
public class ExitCommand extends Command {
    /**
     * Displays the goodbye message via the user interface.
     *
     * @param tasks the task list (unused)
     * @param ui the user interface for displaying the goodbye message
     * @param storage the storage (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Returns true to indicate this is an exit command.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
