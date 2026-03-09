package mandy;

import java.util.ArrayList;

/**
 * Represents a command to find tasks by keyword.
 * This command searches for tasks whose description contains the given keyword (case-insensitive).
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs a FindCommand with the given keyword.
     * The keyword is converted to lowercase for case-insensitive matching.
     *
     * @param keyword the keyword to search for
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Searches for tasks whose description contains the keyword,
     * and displays the matching tasks.
     *
     * @param tasks the task list to search
     * @param ui the user interface for displaying the search results
     * @param storage the storage (unused)
     * @throws MandyException if an error occurs while accessing tasks
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MandyException {
        ArrayList<Task> matching = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matching.add(task);
            }
        }
        if (matching.isEmpty()) {
            ui.showMessage("No tasks found containing '" + keyword + "'.");
        } else {
            ui.showMessage(" Here are the matching tasks in your list:");
            for (int i = 0; i < matching.size(); i++) {
                ui.showMessage(" " + (i + 1) + "." + matching.get(i));
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
