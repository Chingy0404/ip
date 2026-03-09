package mandy;

/**
 * Represents an executable command.
 * This is an abstract base class for all command types in the Mandy chatbot.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks the task list to operate on
     * @param ui the user interface for displaying results
     * @param storage the storage for persisting tasks
     * @throws MandyException if an error occurs during execution
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MandyException;

    /**
     * Returns whether this command is an exit command.
     * If true, the chatbot will terminate after executing this command.
     *
     * @return true if this command is an exit command, false otherwise
     */
    public abstract boolean isExit();
}
