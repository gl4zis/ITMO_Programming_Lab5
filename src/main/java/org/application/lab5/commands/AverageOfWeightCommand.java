package org.application.lab5.commands;

import org.application.lab5.collection.DragonCollection;

public class AverageOfWeightCommand extends NonArgsCommand {

    AverageOfWeightCommand() {
        super("average_of_weight",
                "average_of_weight : вывести среднее значение поля weight для всех элементов коллекции");
    }

    @Override
    public void execute() {
        System.out.println(DragonCollection.instance.getAverageWeight());
    }
}