package todo;

import java.time.LocalDate;
import java.time.DateTimeException;
import java.lang.Comparable;
    /**
 * Class representing a single todo item.
 *
 */
public class TodoItem implements Comparable<TodoItem> {

    private String taskName;
    private LocalDate deadline;
    private Boolean isDone;
    private TodoList myList;

    public TodoItem() {;}

    public TodoItem(String taskName, LocalDate deadline) throws WrongTaskException {
        setTaskName(taskName);
        setDeadline(deadline);
        setUndone();
    }

    public TodoItem(int year, int month, int dayOfMonth) throws DateTimeException, WrongTaskException {
        setDeadline(year, month, dayOfMonth);
        setUndone();
    }

    public TodoItem(LocalDate deadline, String name, Boolean isDone) throws WrongTaskException {
        setDeadline(deadline);
        setTaskName(name);
        this.isDone = isDone;
    }

    public TodoItem(String taskName) {
        setTaskName(taskName);
        setUndone();
    }

    public void setMyList(TodoList list) {
        myList = list;
    }

    public TodoList getMyList () {
        return myList;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline (LocalDate deadline) throws WrongTaskException {
        if (deadline.isBefore(LocalDate.now())) {
            throw new WrongTaskException("Deadline cannot be date from the past!");
        } else {
            this.deadline = deadline;
        }
    }

    public void setDone() {
        isDone = true;
    }

    public void setUndone() {
        isDone = false;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setDeadline (int year, int month, int dayOfMonth) throws DateTimeException, WrongTaskException {
        setDeadline(LocalDate.of(year, month, dayOfMonth));
    }

    public String toString () {
        String doneMark = isDone ? "[x]" : "[ ]";
        return doneMark + " " + deadline.toString() + " : " + getTaskName();
    }

    public String toSave() {
        String doneMark = isDone ? "x" : "o";
        return doneMark + "," + deadline.toString() + "," + getTaskName();
    }

    public int compareTo (TodoItem other) {
        if (getDeadline().isAfter(other.getDeadline())) {
            return 1;
        } else if (getDeadline().isBefore(other.getDeadline())) {
            return -1;
        } else {
            return 0;
        }
    }
}
