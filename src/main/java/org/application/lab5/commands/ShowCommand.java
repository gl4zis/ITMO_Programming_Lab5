package org.application.lab5.commands;

import org.application.lab5.Main;
import org.application.lab5.collection.DragonCollection;
import org.application.lab5.dragons.Dragon;
import org.application.lab5.parsers.InputScriptReader;


public class ShowCommand extends NonArgsCommand {

    ShowCommand() {
        super("show", "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
    }

    @Override
    public void execute(InputScriptReader reader) {
        if (Main.DRAGON_COLLECTION.getItems().size() == 0) {
            System.out.println("Нет элементов в коллекции");
        } else {
            int counter = 0;
            for (Dragon dragon : Main.DRAGON_COLLECTION.getItems()) {
                if (counter >= 1)
                    System.out.println("----------------------------------------------------------------");
                System.out.println(dragon);
                counter++;
            }
        }
    }
}