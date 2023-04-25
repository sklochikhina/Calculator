package ru.nsu.klochikhina.calculator.model;

import ru.nsu.klochikhina.calculator.model.factory.Command;
import ru.nsu.klochikhina.calculator.model.factory.Factory;

import java.io.*;
import java.util.*;

public class Run {
    private final Stack<Double> stack;
    private final Map<String, Double> defineList;
    private final Parsing pars;

    public Run(Stack<Double> stack, Map<String, Double> defineList) {
        this.stack = stack;
        this.defineList = defineList;
        this.pars = new Parsing(chooseStream());
    }

    public boolean run() {
        try {
            String[] arr = pars.parsing();

            if(arr == null) {
                System.out.println("Вы успешно завершили выполнение программы.");
                return true;
            }

            Factory factory = new Factory();
            factory.getCommandPaths();

            try {
                Command command = factory.createCommand(arr, stack, defineList);
                switch (arr.length) {
                    case 1:
                        command.action();
                        break;
                    case 2:
                        command.action(arr[1]);         // PUSH
                        break;
                    case 3:
                        command.action(arr[1], arr[2]); // DEFINE
                        break;
                }
            } catch (ArithmeticException | IOException exc){
                System.err.println(exc.getMessage());
                return false;
            } catch (EmptyStackException exc){
                System.err.println("Ошибка: стек пуст!");
                return false;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream chooseStream(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите режим считывания данных: 1 - с консоли, 2 - с файла: ");

        String input = scanner.nextLine().trim();

        while (!input.matches("[1-2]")) {
            System.err.print("Введите 1 или 2 в зависимости от желаемого режима: ");
            input = scanner.nextLine();
        }

        if(input.matches("^[1]$"))
            return System.in;
        else {
            System.out.print("Введите имя файла: ");
            while (true) {
                String fileName = scanner.nextLine().trim();
                File file = new File(fileName);

                if (file.exists() && file.isFile()) {
                    try {
                        return new FileInputStream(file);
                    } catch (FileNotFoundException e) {
                        // не должно возникать, поскольку мы проверили существование файла
                        throw new RuntimeException(e);
                    }
                }
                System.err.print("Файл с именем " + fileName + " не существует. Попробуйте еще раз: ");
            }
        }
    }
}
