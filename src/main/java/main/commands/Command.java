package main.commands;

import main.CollectionManager;
import main.InputConsoleReader;
import main.JsonManager;
import main.exceptions.IncorrectInputException;

public abstract class Command {

    protected static final JsonManager MANAGER = new JsonManager("ccc");
    protected static final CommandManager INVOKER = new CommandManager();
    private final String name;
    private final String descr;

    protected Command(String name, String descr) {
        this.name = name;
        this.descr = descr;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return descr;
    }

    public static void parse() {
        try {
            INVOKER.seekCommand(InputConsoleReader.readNextLine());
        } catch (IncorrectInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void generateCollection() {
        CollectionManager.transferCollection(MANAGER.readJSON());
    }

    public abstract void execute() throws IncorrectInputException;

    public abstract void execute(String arg) throws IncorrectInputException;
}
