package ru.nsu.klochikhina.calculator.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Parsing {
    private static Scanner scanner;

    public Parsing(InputStream stream) {
        scanner = new Scanner(stream);
    }

    public List<String> parsing() throws Exception {
        try {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty())
                    throw new IOException("Строка пуста!");
                else if (input.equals("STOP"))
                    return null;
                return new ArrayList<>(Arrays.asList(input.split("\\s+")));
            }
            else return null;
        } catch (NoSuchElementException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
