package ru.nsu.klochikhina.calculator.model.factory;

import java.io.IOException;
import java.util.*;

public class Factory {
    private final static ArrayList<String> noParameterCommands =
            new ArrayList<>(Arrays.asList("POP", "+", "-", "*", "/", "SQRT", "PRINT", "SAVE", "READ"));
    private final static ArrayList<String> aFewParametersCommands =
            new ArrayList<>(Arrays.asList("PUSH", "DEFINE", "COMPOSITE"));
    private final static HashMap<String, String> commandPaths = new HashMap<>();

    public Command createCommand(List<String> action, Stack<Double> stack, Map<String, Double> defineList) throws Exception{
        if (!checkInput(action))
            throw new IOException("Неверный ввод команды или излишнее/недостаточное количество параметров.");

        try {
            if (action.get(0).equals("PUSH") || action.get(0).equals("COMPOSITE"))
                return (Command) Class.forName(commandPaths.get(action.get(0))).
                        getDeclaredConstructor(Stack.class, Map.class).newInstance(stack, defineList);
            else if (action.get(0).equals("DEFINE"))
                return (Command) Class.forName(commandPaths.get(action.get(0))).
                        getDeclaredConstructor(Map.class).newInstance(defineList);
            else if (action.get(0).startsWith("#"))
                return (Command) Class.forName(commandPaths.get("#")).
                        getDeclaredConstructor().newInstance();
            else return (Command) Class.forName(commandPaths.get(action.get(0))).
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

    public boolean checkInput(List<String> list) {
        if(noParameterCommands.contains(list.get(0)))
            return list.size() == 1;
        else if (aFewParametersCommands.contains(list.get(0)))
            return (!list.get(0).equals("PUSH") || list.size() == 2) &&
                    (!list.get(0).equals("DEFINE") || list.size() == 3) &&
                    (!list.get(0).equals("COMPOSITE") || list.size() > 1);
        return list.get(0).startsWith("#");
    }

    public List<String> someCommandArguments(List<String> arguments) throws IOException {
        List<String> newList = new ArrayList<>();
        if (noParameterCommands.contains(arguments.get(0))) {
            newList.add(arguments.get(0));  // сама команда

            arguments.remove(0);

            return newList;
        }
        else if (aFewParametersCommands.contains(arguments.get(0))) {
            if (arguments.size() >= 2) {

                newList.add(arguments.get(0));  // сама команда

                if (arguments.get(0).equals("PUSH")) {
                    newList.add(arguments.get(1));

                    arguments.remove(1);
                    arguments.remove(0);

                    return newList;
                }
                else if (arguments.get(0).equals("DEFINE")) {
                    newList.add(arguments.get(1));
                    newList.add(arguments.get(2));

                    arguments.remove(2);
                    arguments.remove(1);
                    arguments.remove(0);

                    return newList;
                }
                else {
                    arguments.remove(0);
                    return arguments;
                } // для случая, когда есть ещё один COMPOSITE после первого
            }
            else
                throw new IOException("Ошибка: недостаточно аргументов для команды " + arguments.get(0));
        }
        else if (arguments.get(0).startsWith("#"))
            return newList;
        else
            throw new IOException("Ошибка: команда " + arguments.get(0) + " не существует");
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
                "PUSH - положить число на стек;\n" +
                "+, -, *, /, SQRT - доступные арифметические операции с элементами стека;\n" +
                "PRINT - печать верхнего элемента стека;\n" +
                "DEFINE - задать значение параметра;\n" +
                "STOP - останавливает действие программы.\n" +
                "SAVE - сохранение текущего состояния стека в бинарный файл.\n" +
                "COMPOSITE - после этой команды могут идти другие, которые выполнятся по очереди.\n";
    }
}
