package banking.view.impl;

import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;

public class CardPage implements IPage {

    @Override
    public void display(Model model) {

        System.out.println("Your card has been created");

        System.out.println("Your card number:");

        System.out.println(model.get(Params.CARD_NUMBER));

        System.out.println("Your card PIN:");

        System.out.println(model.get(Params.PIN));

        System.out.println();

    }

}
