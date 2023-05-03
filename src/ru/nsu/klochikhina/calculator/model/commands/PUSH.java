package ru.nsu.klochikhina.calculator.model.commands;

import ru.nsu.klochikhina.calculator.model.factory.Command;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class PUSH implements Command {
    private final Stack<Double> stack;
    private final Map<String, Double> defineList;

    public PUSH(Stack<Double> stack, Map<String, Double> defineList) {
        this.stack = stack;
        this.defineList = defineList;
    }

    @Override
    public void action(List<String> list) throws Exception {
        if (defineList.containsKey(list.get(0)))
            stack.push(defineList.get(list.get(0)));
        else if(!list.get(0).matches("^[0-9]+$"))
            throw new IOException("Ошибка: параметр " + list.get(0) + " не был определён.");
        else
            stack.push(Double.parseDouble(list.get(0)));
    }
}
