package main.commands;

import main.dragons.DragonCollection;

import java.io.Reader;

public class MinByAgeCommand extends NonArgsCommand {

    MinByAgeCommand() {
        super("min_by_age");
    }

    @Override
    public void execute() {
        System.out.println(DragonCollection.instance.minByAge());
    }
}