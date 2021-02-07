package banking.view.impl;

import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;

public class BalancePage implements IPage {

    @Override
    public void display(Model model) {

        String balance = model.get(Params.BALANCE);

        System.out.println("Balance: " + balance);

        System.out.println();

    }

}
