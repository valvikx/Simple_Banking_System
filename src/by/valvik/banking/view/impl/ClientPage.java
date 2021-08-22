package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.view.Page;

import java.util.Scanner;

public class ClientPage implements Page {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display(Holder holder) {

        System.out.println("1. Balance");

        System.out.println("2. Add income");

        System.out.println("3. Do transfer");

        System.out.println("4. Close account");

        System.out.println("5. Log out");

        System.out.println("0. Exit");

        String commandItem = scanner.nextLine();

        holder.add(Param.COMMAND_OPTION, commandItem);

        System.out.println();

    }

}
