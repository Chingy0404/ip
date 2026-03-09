package mandy;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * Provides operations to add, delete, retrieve, and modify tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the given list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index the zero-based index of the task to delete
     * @return the deleted task
     * @throws MandyException if the index is out of bounds
     */
    public Task delete(int index) throws MandyException {
        if (index < 0 || index >= tasks.size()) {
            throw new MandyException("Invalid task number.");
        }
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified index.
     *
     * @param index the zero-based index of the task to retrieve
     * @return the task at the index
     * @throws MandyException if the index is out of bounds
     */
    public Task get(int index) throws MandyException {
        if (index < 0 || index >= tasks.size()) {
            throw new MandyException("Invalid task number.");
        }
        return tasks.get(index);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index the zero-based index of the task to mark
     * @throws MandyException if the index is out of bounds
     */
    public void markAsDone(int index) throws MandyException {
        Task task = get(index);
        task.markAsDone();
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index the zero-based index of the task to unmark
     * @throws MandyException if the index is out of bounds
     */
    public void markAsNotDone(int index) throws MandyException {
        Task task = get(index);
        task.markAsNotDone();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether the task list is empty.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
