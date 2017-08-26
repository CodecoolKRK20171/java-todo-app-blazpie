import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.io.IOException;
import todo.*;

/**
 * Class representing the TodoApp application.
 * It is the main entry point for this program.
 */
public class App {

    public static final String[] taskListOptions = {"Choose task", "Add task", "Archive done tasks", "Exit program"};
    public static final String[] taskListEmptyOptions = {"Add task", "Archive done tasks", "Exit program"};
    public static final String[] taskDoneOptions = {"Mark as undone", "Change description", "Change deadline", "Remove task", "Back"};
    public static final String[] taskUndoneOptions = {"Mark as done", "Change description", "Change deadline", "Remove task", "Back"};

    public static void main(String[] args) {
        TodoList task1;
        try {
            task1 = DAO.readFile("task1.txt");
        } catch (FileNotFoundException e) {
            UI.printMessage(e.getMessage());
            task1 = new TodoList("task1.txt");
        }
        handleTaskListMenu(task1);
        try {
            DAO.saveFile(task1);
        } catch (IOException e) {
            UI.printMessage(e.getMessage());
        }
    }

    public static void handleTaskListMenu(TodoList taskList) {
        Boolean isBrowsingFinished = false;
        Menu taskListMenu;
        while (!isBrowsingFinished) {
            taskList.sortList();
            UI.clearScrean();
            UI.printMessage(taskList.toString());
            if (taskList.isEmpty()) {
                taskListMenu = new Menu (taskListEmptyOptions);
            } else {
                taskListMenu = new Menu (taskListOptions);
            }
            String choosen_option = taskListMenu.handleMenu();
            switch (choosen_option) {
                case "Choose task":
                    TodoItem task = handleTaskChoose(taskList);
                    handleTaskMenu(task);
                    break;
                case "Add task":
                    LocalDate deadline = UI.getDate();
                    String description = UI.getString("Enter description: ");
                    try {
                        taskList.addTask(new TodoItem(description, deadline));
                    } catch (WrongTaskException e) {
                        UI.printMessage(e.getMessage());
                    }
                    break;
                case "Archive done tasks":
                    taskList.archiveDone();
                    break;
                case "Exit program":
                    isBrowsingFinished = true;
                    break;
            }
        }
    }

    public static void handleTaskMenu(TodoItem task) {
        Boolean isBrowsingFinished = false;
        String choosen_option;
        Menu taskMenu;
        while (!isBrowsingFinished) {
            UI.clearScrean();
            UI.printMessage(task.toString());
            if (task.isDone()) {
                taskMenu = new Menu (taskDoneOptions);
            } else {
                taskMenu = new Menu (taskUndoneOptions);
            }
            choosen_option = taskMenu.handleMenu();
            switch (choosen_option) {
                case "Mark as undone":
                    task.setUndone();
                    break;
                case "Mark as done":
                    task.setDone();
                    break;
                case "Change description":
                    String description = UI.getString("Enter description: ");
                    task.setTaskName(description);
                    break;
                case "Change deadline":
                    LocalDate deadline = UI.getDate();
                    try {
                        task.setDeadline(deadline);
                    } catch (WrongTaskException e) {
                        UI.printMessage(e.getMessage());
                    }
                    break;
                case "Remove task":
                    task.getMyList().removeTask(task);
                    isBrowsingFinished = true;
                    break;
                case "Back":
                    isBrowsingFinished = true;
                    break;
            }
        }
    }

    public static TodoItem handleTaskChoose(TodoList taskList) {
        Integer index;
        Boolean isOnList = false;
        do {
            index = UI.getInteger("Choose task index from list above");
            if (index > 0 && index <= taskList.getTaskList().size()) {
                isOnList = true;
            } else {
                UI.printMessage("Wrong choose!");
            }
        } while (!isOnList);
        return taskList.getTaskList().get(index - 1);
    }
}
