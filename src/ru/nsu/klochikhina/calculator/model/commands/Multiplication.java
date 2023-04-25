package ru.nsu.klochikhina.calculator.model.commands;

import ru.nsu.klochikhina.calculator.model.factory.Command;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

public class Multiplication implements Command {
    private final Stack<Double> stack;

    public Multiplication(Stack<Double> stack) {
        this.stack = stack;
    }

    @Override
    public void action(String... strings) throws Exception {
        if (stack.isEmpty())
            throw new EmptyStackException();
        else if (stack.size() == 1)
            throw new IOException("Ошибка: стек содержит один элемент - умножение невозможно!");

        stack.push(stack.pop() * stack.pop());
    }
}