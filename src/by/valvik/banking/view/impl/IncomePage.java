package by.valvik.banking.view.impl;

import by.valvik.banking.constant.Param;
import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Console;
import by.valvik.banking.view.Page;

public class IncomePage implements Page {

    public static final String ENTER_INCOME = ">Enter income: ";

    private final Console console;

    public IncomePage() {

        console = Console.getInstance();

    }

    @Override
    public void display(Holder holder) {

        System.out.print(ENTER_INCOME);

        String income = console.getScanner().nextLine().trim();

        holder.add(Param.INCOME, income);

        System.out.println();

    }

}
