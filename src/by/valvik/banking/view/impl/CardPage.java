package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.view.Page;

public class CardPage implements Page {

    @Override
    public void display(Holder holder) {

        System.out.println("Your card has been created");

        System.out.println("Your card number:");

        System.out.println(holder.get(Param.CARD_NUMBER));

        System.out.println("Your card PIN:");

        System.out.println(holder.get(Param.PIN));

        System.out.println();

    }

}
