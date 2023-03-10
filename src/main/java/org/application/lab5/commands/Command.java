package org.application.lab5.commands;

import org.application.lab5.Main;
import org.application.lab5.parsers.InputConsoleReader;
import org.application.lab5.parsers.JsonManager;
import org.application.lab5.collection.CollectionManager;
import org.application.lab5.exceptions.IncorrectInputException;

public abstract class Command {

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

    public abstract void execute() throws IncorrectInputException;

    public abstract void execute(String arg) throws IncorrectInputException;
}
