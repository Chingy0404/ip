package mandy;

/**
 * Represents a task with a description and completion status.
 * This is an abstract base class for specific task types (Todo, Deadline, Event).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with the given description.
     * The task is initially not done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon representing whether the task is done.
     * "X" indicates done, a space indicates not done.
     *
     * @return the status icon as a string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of this task for display.
     * Format: "[status] description"
     *
     * @return the display string
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of this task for storage in a file.
     * Format: "T | isDone | description" for Todo,
     *         "D | isDone | description | by" for Deadline,
     *         "E | isDone | description | from | to" for Event.
     * isDone is 1 if done, 0 otherwise.
     *
     * @return the file storage string
     */
    public abstract String toFileString();
}
