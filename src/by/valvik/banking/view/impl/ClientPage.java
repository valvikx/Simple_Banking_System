package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Console;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.COMMAND_OPTION;

public class ClientPage implements Page {

    private static final String MENU = """
        ******************** Simple Banking System ********************
        ========================= Client Menu =========================
        1. Balance
        2. Add income
        3. Do transfer
        4. Close account
        5. Log out       
        0. Exit
        >>>\040""";

    private final Console console;

    public ClientPage() {

        console = Console.getInstance();

    }

    @Override
    public void display(Holder holder) {

        System.out.print(MENU);

        String commandItem = console.getScanner().nextLine().trim();

        holder.add(COMMAND_OPTION, commandItem);

        System.out.println();

    }

}
