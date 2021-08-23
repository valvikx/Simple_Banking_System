package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Console;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.CARD_NUMBER;
import static by.valvik.banking.constant.Param.PIN;

public class LoginPage implements Page {

    private static final String ENTER_YOUR_CARD_NUMBER = ">Enter your card number: ";

    private static final String ENTER_YOUR_PIN = ">Enter your PIN: ";

    private final Console console;

    public LoginPage() {

        console = Console.getInstance();

    }

    @Override
    public void display(Holder holder) {

        System.out.print(ENTER_YOUR_CARD_NUMBER);

        String cardNumber = console.getScanner().nextLine().trim();

        holder.add(CARD_NUMBER, cardNumber);

        System.out.print(ENTER_YOUR_PIN);

        String pin = console.getScanner().nextLine().trim();

        holder.add(PIN, pin);

    }

}
