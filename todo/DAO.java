package todo;

import java.io.FileNotFoundException;
import java.io.File;
import java.nio.file.*;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.IOException;


public class DAO {

    public static TodoList readFile(String fileName) throws FileNotFoundException {
        Path file = Paths.get(fileName);
        Scanner fileReader = new Scanner(file.toFile());
        TodoList fromFile = new TodoList(fileName);

        while(fileReader.hasNextLine()) {
            String[] lineList = fileReader.nextLine().split("[,|_]");
            String[] dateArray = lineList[1].split("-");
            Boolean isDone = (lineList[0] == "x") ? true : false;
            try {
                fromFile.addTask(new TodoItem(LocalDate.of(new Integer(dateArray[0]),
                                                           new Integer(dateArray[1]),
                                                           new Integer(dateArray[2])),
                                                           lineList[2], isDone));
            } catch (WrongTaskException e) {
                UI.printMessage("Unable to load task: " + lineList[1]);
                UI.printMessage(e.getMessage());
            }
        }
        fileReader.close();
        return fromFile;
    }

    public static void saveFile(TodoList todoListObject) throws IOException {
        Path file = Paths.get(todoListObject.getName());
        List<String> recordList = new ArrayList<String>();
        for (TodoItem task : todoListObject.getTaskList()) {
            recordList.add(task.toSave());
        }
        Files.write(file, recordList, Charset.defaultCharset());
    }
}
