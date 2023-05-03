package ru.nsu.klochikhina.calculator.model.commands;

import ru.nsu.klochikhina.calculator.model.factory.Command;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class POP implements Command {
    private final Stack<Double> stack;

    public POP(Stack<Double> stack) {
        this.stack = stack;
    }

    @Override
    public void action(List<String> list) throws Exception {
        if (stack.isEmpty())
            throw new EmptyStackException();
        stack.pop();
    }
}
