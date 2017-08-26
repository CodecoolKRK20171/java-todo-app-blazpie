package todo;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;

/**
 * Class representing a todo list.
 * TodoList object should aggregate TodoItem objects.
 */
public class TodoList {

    private ArrayList<TodoItem> taskList;
    private String name;

    public TodoList() {
        taskList = new ArrayList<TodoItem>();
    }

    public TodoList(String name) {
        taskList = new ArrayList<TodoItem>();
        setName(name);
    }

    public TodoList(ArrayList<TodoItem> taskList) {
        taskList = new ArrayList<TodoItem>();
        setTaskList(taskList);
    }

    public ArrayList<TodoItem> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<TodoItem> taskList) {
        this.taskList = taskList;
    }

    public void addTask(TodoItem task) throws WrongTaskException {
        if ((getByName(task.getTaskName())).isEmpty()) {
            taskList.add(task);
            task.setMyList(this);
        } else if (!(getByDeadline(task.getDeadline())).isEmpty()) {
            throw new WrongTaskException("Duplicated task");
        }
    }

    public void removeTask(TodoItem task) {
        if (taskList.contains(task)) {
            taskList.remove(task);
        }
    }

    public Boolean isEmpty() {
        if (taskList.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<TodoItem> getByName (String taskName) {
        ArrayList<TodoItem> resultList = new ArrayList<TodoItem>();
        for (TodoItem task : taskList) {
            if (task.getTaskName().equals(taskName)) {
                resultList.add(task);
            }
        }
        return resultList;
    }

    public ArrayList<TodoItem> getByDeadline (LocalDate deadline) {
        ArrayList<TodoItem> resultList = new ArrayList<TodoItem>();
        for (TodoItem task : taskList) {
            if (task.getDeadline().equals(deadline)) {
                resultList.add(task);
            }
        }
        return resultList;
    }

    public ArrayList<TodoItem> getDoneTasks() {
        ArrayList<TodoItem> doneTasks = new ArrayList<TodoItem>();
        for (TodoItem task : taskList) {
            if (task.isDone()) {
                doneTasks.add(task);
            }
        }
        return doneTasks;
    }

    public void archiveDone() {
        for (TodoItem task : getDoneTasks()) {
            taskList.remove(task);
        }
    }

    public String toString() {
        String resultString = name + ":\n";
        for (int i = 1; i < taskList.size() + 1; i++) {
            resultString += i + ". " + taskList.get(i - 1).toString() + "\n";
        }
        return resultString;
    }

    public void sortList() {
        Collections.sort(taskList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
