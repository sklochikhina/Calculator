package ru.nsu.klochikhina.calculator.model.factory;

import java.util.List;

public class Flyweight implements Command{
    private Command command;

    @Override
    public void action(List<String> list) throws Exception {
        command.action(list);
    }

    public void setCommand(Command command){
        this.command = command;
    }
}
