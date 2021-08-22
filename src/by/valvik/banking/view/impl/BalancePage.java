package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.view.Page;

public class BalancePage implements Page {

    @Override
    public void display(Holder holder) {

        String balance = holder.get(Params.BALANCE);

        System.out.println("Balance: " + balance);

        System.out.println();

    }

}
