package main.commands;

import main.dragons.Dragon;
import main.dragons.DragonCollection;
import main.exceptions.IncorrectInputException;

import java.io.IOException;
import java.io.Reader;

public class AddIfMinCommand extends NonArgsCommand {
    private AddIfMinCommand(String name) {
        super(name);
    }

    private static class CommandHolder {
        public static final Command INSTANCE = new AddIfMinCommand("add_if_min");
    }

    public static Command getInstance() {
        return CommandHolder.INSTANCE;
    }

    @Override
    public void execute() {
        Dragon dragon = readDragon();
        Dragon minDragon = DragonCollection.instance.getMin();
        if (minDragon == null || DragonCollection.instance.getMin().compareTo(dragon) > 0) {
            DragonCollection.instance.add(dragon);
            System.out.println("Новый объект успешно добавлен");
        } else {
            Dragon.decUniqNumber();
            System.out.println("Объект не минимальный");
        }
    }
}
