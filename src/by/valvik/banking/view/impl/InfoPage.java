package by.valvik.banking.view.impl;

import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.view.Page;

public class InfoPage implements Page {

    @Override
    public void display(Holder holder) {

        System.out.println(holder.get(Params.MESSAGE));

        System.out.println();

    }

}
