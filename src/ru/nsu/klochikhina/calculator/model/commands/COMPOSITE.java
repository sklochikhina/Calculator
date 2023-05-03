package ru.nsu.klochikhina.calculator.model.commands;

import ru.nsu.klochikhina.calculator.model.factory.Command;
import ru.nsu.klochikhina.calculator.model.factory.Factory;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class COMPOSITE implements Command {
    private final Stack<Double> stack;
    private final Map<String, Double> defineList;

    public COMPOSITE(Stack<Double> stack, Map<String, Double> defineList) {
        this.stack = stack;
        this.defineList = defineList;
    }

    @Override
    public void action(List<String> list) throws Exception {
        Factory factory = new Factory();
        factory.getCommandPaths();

        while (list.size() > 0){
            List<String> input = factory.someCommandArguments(list);
            Command command = factory.createCommand(input, stack, defineList);
            input.remove(0);
            command.action(input);
        }
    }
}
