package todo;

import java.lang.Exception;


public class WrongTaskException extends Exception {

    public WrongTaskException(String message) {
        super("Invalid Task:\n" + message);
    }
}
