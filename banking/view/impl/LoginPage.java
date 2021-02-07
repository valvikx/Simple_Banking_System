package banking.view.impl;

import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;

import java.util.Scanner;

public class LoginPage implements IPage {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display(Model model) {

        System.out.println("Enter your card number:");

        String cardNumber = scanner.nextLine().trim();

        model.add(Params.CARD_NUMBER, cardNumber);

        System.out.println("Enter your PIN:");

        String pin = scanner.nextLine().trim();

        model.add(Params.PIN, pin);

        System.out.println();

    }

}
