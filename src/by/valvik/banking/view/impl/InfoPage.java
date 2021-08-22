package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.MESSAGE;

public class InfoPage implements Page {

    @Override
    public void display(Holder holder) {

        System.out.println(holder.get(MESSAGE));

        System.out.println();

    }

}
