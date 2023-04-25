package ru.nsu.klochikhina.calculator.ui;

import ru.nsu.klochikhina.calculator.model.Run;
import ru.nsu.klochikhina.calculator.model.factory.Factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    private static final Stack<Double> stack = new Stack<>();
    private static final Map<String, Double> defineList = new HashMap<>();

    public static void main(String[] args) throws Exception {
        System.out.println(Factory.getCommandList());

        Run running = new Run(stack, defineList);
        while (!running.run()) {}
    }
}