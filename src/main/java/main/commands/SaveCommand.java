package main.commands;

import main.CollectionManager;

import java.io.IOException;

public class SaveCommand extends NonArgsCommand {

    SaveCommand() {
        super("save");
    }

    @Override
    public void execute() {
        try {
            CollectionManager.saveCollection();
            System.out.println("Коллекция успешно сохранена в файл");
        } catch (IOException e) {
            System.out.println("Ошибка доступа к файлу");
        }
    }
}