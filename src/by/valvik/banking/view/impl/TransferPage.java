package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.view.Page;

import java.util.Scanner;

public class TransferPage implements Page {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display(Holder holder) {

        System.out.println("Transfer");

        System.out.println("Enter card number:");

        String cardNumber = scanner.nextLine().trim();

        holder.add(Param.CARD_NUMBER, cardNumber);

    }

}
