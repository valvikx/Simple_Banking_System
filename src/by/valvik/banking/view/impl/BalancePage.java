package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.view.Page;

public class BalancePage implements Page {

    @Override
    public void display(Holder holder) {

        String balance = holder.get(Param.BALANCE);

        System.out.println("Balance: " + balance);

        System.out.println();

    }

}
