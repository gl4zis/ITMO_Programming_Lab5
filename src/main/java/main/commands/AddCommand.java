package main.commands;

import main.dragons.Dragon;
import main.dragons.DragonCollection;

public class AddCommand extends NonArgsCommand {


    AddCommand() {
        super("add");
    }

    @Override
    public void execute() {
        Dragon dragon = CommandManager.readDragon();
        DragonCollection.instance.add(dragon);
        System.out.println("Новый объект успешно добавлен");
    }
}
