package banking.view.impl;

import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;

import java.util.Scanner;

public class TransferMoneyPage implements IPage {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void display(Model model) {

        System.out.println("Enter how much money you want to transfer:");

        String transfer = scanner.nextLine().trim();

        model.add(Params.TRANSFER_MONEY, transfer);

    }

}
