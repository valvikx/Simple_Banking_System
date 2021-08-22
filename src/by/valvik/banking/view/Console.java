package by.valvik.banking.view;

import java.util.Scanner;

public class Console {

    private static final Console INSTANCE = new Console();

    private final Scanner scanner;

    private Console() {

         scanner = new Scanner(System.in);

    }

    public static Console getInstance() {

        return INSTANCE;

    }

    public Scanner getScanner() {

        return scanner;

    }

}
