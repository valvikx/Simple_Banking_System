package banking.view.impl;

import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;

import java.util.Scanner;

public class IncomePage implements IPage {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display(Model model) {

        System.out.println("Enter income:");

        String income = scanner.nextLine().trim();

        model.add(Params.INCOME, income);

    }

}
