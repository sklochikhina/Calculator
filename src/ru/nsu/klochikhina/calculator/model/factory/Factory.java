package ru.nsu.klochikhina.calculator.model.factory;

import java.io.IOException;
import java.lang.Class;
import java.util.*;

public class Factory {
    private final static ArrayList<String> noParameterCommands =
            new ArrayList<>(Arrays.asList("POP", "+", "-", "*", "/", "SQRT", "PRINT"));
    private final static ArrayList<String> aFewParametersCommands =
            new ArrayList<>(Arrays.asList("PUSH", "DEFINE"));
    private static HashMap<String, String> commandPaths = new HashMap<>();

    public Command createCommand(String[] action, Stack<Double> stack, Map<String, Double> defineList) throws Exception{
        if (!checkInput(action))
            throw new IOException("Неверный ввод команды или излишнее/недостаточное количество параметров.");

        try {
            if (action[0].equals("PUSH"))
                return (Command) Class.forName(commandPaths.get(action[0])).
                        getDeclaredConstructor(Stack.class, Map.class).newInstance(stack, defineList);
            else if (action[0].equals("DEFINE"))
                return (Command) Class.forName(commandPaths.get(action[0])).
                        getDeclaredConstructor(Map.class).newInstance(defineList);
            else return (Command) Class.forName(commandPaths.get(action[0])).
                        getDeclaredConstructor(Stack.class).newInstance(stack);
        } catch (ClassNotFoundException e){
            System.err.println("Класс команды не найден.");
        } catch (InstantiationException e){
            System.err.println("Ошибка инициализации.");
        } catch (IllegalAccessException e){
            System.err.println("Ошибка доступа.");
        }
        return null;
    }

    public boolean checkInput(String[] arr) {
        if(noParameterCommands.contains(arr[0]))
            return  arr.length == 1;
        else if (aFewParametersCommands.contains(arr[0]))
            return (!arr[0].equals("PUSH") || arr.length == 2) && (!arr[0].equals("DEFINE") || arr.length == 3);
        return false;
    }

    public void getCommandPaths() {
        String file = "config";
        Scanner scanner = new Scanner(Objects.requireNonNull(Factory.class.getResourceAsStream(file)));
        while (scanner.hasNextLine()){
            String[] strings = scanner.nextLine().split("=");
            commandPaths.put(strings[0], strings[1]);
        }
    }

    public static String getCommandList(){
        return "Здравствуйте, я стековый калькулятор! Мои возможности:\n" +
                "# - начало комментария;\n" +
                "POP - снять число со стека;\n" +
                "PUSH - положить чсило на стек;\n" +
                "+, -, *, /, SQRT - доступные арифметические операции с элементами стека;\n" +
                "PRINT - печать верхнего элемента стека;\n" +
                "DEFINE - задать значение параметра;\n" +
                "STOP - останавливает действие программы.\n";
    }
}
