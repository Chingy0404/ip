package mandy;

/**
 * Represents a command to display help information.
 * This command shows a list of available commands and their usage.
 */
public class HelpCommand extends Command {
    /**
     * Displays the help message via the user interface.
     *
     * @param tasks the task list (unused)
     * @param ui the user interface for displaying the help message
     * @param storage the storage (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showHelp();
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
