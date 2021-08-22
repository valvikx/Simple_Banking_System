package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.view.Page;

import java.util.Scanner;

public class AuthorizationPage implements Page {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display(Holder holder) {

        System.out.println("1. Create an account");

        System.out.println("2. Log into account");

        System.out.println("0. Exit");

        String commandItem = scanner.nextLine();

        holder.add(Params.ITEM, commandItem);

        System.out.println();

    }

}
