package ru.nsu.klochikhina.calculator.model.commands;

import ru.nsu.klochikhina.calculator.model.factory.Command;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

public class Division implements Command {
    private final Stack<Double> stack;

    public Division(Stack<Double> stack) {
        this.stack = stack;
    }

    @Override
    public void action(List<String> list) throws Exception {
        if (stack.isEmpty())
            throw new EmptyStackException();
        else if (stack.size() == 1)
            throw new IOException("Ошибка: стек содержит один элемент - деление невозможно!");

        double peek = stack.pop();
        if (stack.peek() == 0) {
            stack.push(peek);
            throw new ArithmeticException("Ошибка: деление на ноль невозможно!");
        }

        stack.push(peek / stack.pop());
    }
}
