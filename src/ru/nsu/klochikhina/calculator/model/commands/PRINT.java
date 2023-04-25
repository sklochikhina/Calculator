package ru.nsu.klochikhina.calculator.model.commands;

import ru.nsu.klochikhina.calculator.model.factory.Command;
import java.util.EmptyStackException;
import java.util.Stack;

public class PRINT implements Command {
    private final Stack<Double> stack;

    public PRINT(Stack<Double> stack) {
        this.stack = stack;
    }

    @Override
    public void action(String... strings) throws Exception {
        if (stack.isEmpty())
            throw new EmptyStackException();
        System.out.println("Вершина стека: " + stack.peek() + ".");
    }
}
