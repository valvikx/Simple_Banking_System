package banking.view.impl;

import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;

import java.util.Scanner;

public class ClientPage implements IPage {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display(Model model) {

        System.out.println("1. Balance");

        System.out.println("2. Add income");

        System.out.println("3. Do transfer");

        System.out.println("4. Close account");

        System.out.println("5. Log out");

        System.out.println("0. Exit");

        String commandItem = scanner.nextLine();

        model.add(Params.ITEM, commandItem);

        System.out.println();

    }

}
