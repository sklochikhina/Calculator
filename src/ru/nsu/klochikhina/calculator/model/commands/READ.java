package ru.nsu.klochikhina.calculator.model.commands;

import ru.nsu.klochikhina.calculator.model.factory.Command;

import java.io.*;
import java.util.List;
import java.util.Stack;

public class READ implements Command, Serializable {
    private final File file = new File("F:/Java/Calculator/src/ru/nsu/klochikhina/calculator/model/file.bin");
    private final Stack<Double> stack;

    public READ(Stack<Double> stack) {
        this.stack = stack;
    }

    @Override
    public void action(List<String> list) throws Exception {
        try(ObjectInputStream read_stack = new ObjectInputStream(new FileInputStream(file))){
            stack.clear();
            stack.addAll((Stack<Double>) read_stack.readObject());
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден.");
        }
    }
}
