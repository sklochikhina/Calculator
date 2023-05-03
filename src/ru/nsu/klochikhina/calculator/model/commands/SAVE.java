package ru.nsu.klochikhina.calculator.model.commands;

import ru.nsu.klochikhina.calculator.model.factory.Command;

import java.io.*;
import java.util.List;
import java.util.Stack;

public class SAVE implements Command, Serializable {
    private final File file = new File("F:/Java/Calculator/src/ru/nsu/klochikhina/calculator/model/file.bin");
    private final Stack<Double> stack;

    public SAVE(Stack<Double> stack) {
        this.stack = stack;
    }

    @Override
    public void action(List<String> list) throws Exception {
        try(ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file))){
            obj.writeObject(stack);
            System.out.println("Вы успешно сохранили текущее состояние стека!");
        } catch (FileNotFoundException e){
            System.out.println("Файл не найден.");
        }
    }
}
