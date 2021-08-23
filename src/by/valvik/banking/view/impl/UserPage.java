package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Console;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.COMMAND_OPTION;

public class UserPage implements Page {

    private static final String MENU = """
        ******************** Simple Banking System ********************
        ========================= User Menu ===========================
        1. Create an account
        2. Log into account
        0. Exit
        >>>\040""";

    private final Console console;

    public UserPage() {

        console = Console.getInstance();

    }

    @Override
    public void display(Holder holder) {

        System.out.print(MENU);

        String commandOption = console.getScanner().nextLine().trim();

        holder.add(COMMAND_OPTION, commandOption);

        System.out.println();

    }

}
