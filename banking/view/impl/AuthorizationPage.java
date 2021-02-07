package banking.view.impl;

import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;

import java.util.Scanner;

public class AuthorizationPage implements IPage {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display(Model model) {

        System.out.println("1. Create an account");

        System.out.println("2. Log into account");

        System.out.println("0. Exit");

        String commandItem = scanner.nextLine();

        model.add(Params.ITEM, commandItem);

        System.out.println();

    }

}
