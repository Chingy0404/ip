package mandy;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

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
     */
    public abstract String toFileString();
}
