package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Console;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.TRANSFER_MONEY;

public class TransferMoneyPage implements Page {

    private static final String ENTER_HOW_MUCH_MONEY_TO_TRANSFER = ">Enter how much money you want to transfer: ";

    private final Console console;

    public TransferMoneyPage() {

        console = Console.getInstance();

    }

    @Override
    public void display(Holder holder) {

        System.out.print(ENTER_HOW_MUCH_MONEY_TO_TRANSFER);

        String transferAmount = console.getScanner().nextLine().trim();

        holder.add(TRANSFER_MONEY, transferAmount);

    }

}
