package ru.nsu.klochikhina.calculator.model.commands;

import ru.nsu.klochikhina.calculator.model.factory.Command;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DEFINE implements Command {
    private final Map<String, Double> defineList;
    public DEFINE(Map<String, Double> defineList){
        this.defineList = defineList;
    }
    @Override
    public void action(List<String> list) throws Exception {
        if (defineList.containsKey(list.get(0)))
            throw new IOException("Ошибка: значение " + list.get(0) + " уже определено как " + defineList.get(list.get(0)) + ".");
        else if (list.get(0).matches("^[0-9]+$"))
            throw new IOException("Ошибка: параметр не должен быть числом. Введено: " + list.get(0));
        else if (!list.get(1).matches("^[0-9]+$"))
            throw new IOException("Ошибка: значение " + list.get(1) + " не является числом.");
        else
            defineList.put(list.get(0), Double.parseDouble(list.get(1)));
    }
}
