package todo;

public class Menu {

    private String title;
    private String [] options;

    public Menu () {
        title = "";
    }

    public Menu (String title) {
        setTitle(title);
    }

    public Menu (String[] options) {
        setTitle("");
        setOptions(options);
    }

    public Menu (String title, String[] options) {
        setTitle(title);
        setOptions(options);
    }

    public void setOptions (String[] options) {
        this.options = options;
    }

    public String[] getOptions () {
        return options;
    }

    public String getTitle () {
        return title;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public String handleMenu() {
        UI.printMessage(getTitle());
        UI.printMenu(getOptions());
        return UI.getChoice(getOptions());
    }
}
