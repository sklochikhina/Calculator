package ru.nsu.klochikhina.calculator.model.factory;

public interface Command {
    void action(String... strings) throws Exception;
}
