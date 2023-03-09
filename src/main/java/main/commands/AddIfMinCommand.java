package main.commands;

import main.InputConsoleReader;
import main.dragons.Dragon;
import main.dragons.DragonCollection;
import main.exceptions.IncorrectInputException;

import java.io.IOException;
import java.io.Reader;

public class AddIfMinCommand extends NonArgsCommand {

    AddIfMinCommand() {
        super("add_if_min",
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
    }

    @Override
    public void execute() {
        Dragon dragon = InputConsoleReader.readDragon();
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
