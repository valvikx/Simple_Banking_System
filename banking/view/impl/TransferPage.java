package banking.view.impl;

import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;

import java.util.Scanner;

public class TransferPage implements IPage {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display(Model model) {

        System.out.println("Transfer");

        System.out.println("Enter card number:");

        String cardNumber = scanner.nextLine().trim();

        model.add(Params.CARD_NUMBER, cardNumber);

    }

}
