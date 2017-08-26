package todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.lang.NumberFormatException;
import java.time.DateTimeException;
import java.time.LocalDate;


public class UI {

    public static String getString(String message) {
        Scanner input = new Scanner(System.in);
        printMessage(message);
        String enteredString = input.nextLine();
        return enteredString;
    }

    public static Integer getInteger(String message) {
        Boolean incorrectInput = true;
        Integer enteredInt = null;
        do {
            try {
                String enteredString = getString(message);
                enteredInt = new Integer(enteredString);
                incorrectInput = false;
            } catch (NumberFormatException e) {
                printMessage("Wrong number!");
            }
        } while(incorrectInput);
        return enteredInt;
    }

    public static ArrayList<Integer> getIntegers(String[] labels) {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        for (String label : labels) {
            resultList.add(getInteger("Enter " + label + ": "));
        }
        return resultList;
    }

    public static String getChoice(String [] options) {
        Integer choose;
        do {
            choose = getInteger("Choose option: ");
        } while ((choose <= 0) && (choose > options.length));
        return options[choose - 1];
    }

    public static void printMessage (String message) {
        System.out.println(message);
    }

    public static void printMenu (String [] menuOptions) {
        for (int i = 1; i<menuOptions.length + 1; i++) {
            printMessage(i + ". " + menuOptions[i - 1]);
        }
    }

    public static LocalDate getDate () {
        Boolean incorrectInput = true;
        LocalDate date = null;
        while (incorrectInput) {
            try {
                String[] dateLabels = {"Year", "Month", "Day"};
                ArrayList<Integer> dateData = getIntegers(dateLabels);
                date = LocalDate.of(dateData.get(0), dateData.get(1), dateData.get(2));
                incorrectInput = false;
            } catch (DateTimeException e) {
                printMessage(e.getMessage());
            }
        }
        return date;
    }

    public static void clearScrean () {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
