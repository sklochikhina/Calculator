package ru.nsu.klochikhina.calculator.model.factory;

import java.util.List;

public interface Command {
    void action(List<String> list) throws Exception;
}
