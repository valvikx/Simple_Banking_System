package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Console;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.CARD_NUMBER;

public class TransferCardPage implements Page {

    public static final String TRANSFER = """
        Transfer
        Enter card number:\040""";

    private final Console console;

    public TransferCardPage() {

        console = Console.getInstance();

    }

    @Override
    public void display(Holder holder) {

        System.out.println(TRANSFER);

        String cardNumber = console.getScanner().nextLine().trim();

        holder.add(CARD_NUMBER, cardNumber);

    }

}
