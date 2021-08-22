package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.view.Page;

import java.util.Scanner;

public class LoginPage implements Page {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display(Holder holder) {

        System.out.println("Enter your card number:");

        String cardNumber = scanner.nextLine().trim();

        holder.add(Params.CARD_NUMBER, cardNumber);

        System.out.println("Enter your PIN:");

        String pin = scanner.nextLine().trim();

        holder.add(Params.PIN, pin);

        System.out.println();

    }

}
