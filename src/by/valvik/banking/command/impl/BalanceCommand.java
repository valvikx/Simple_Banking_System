package by.valvik.banking.command.impl;

import by.valvik.banking.command.Command;
import by.valvik.banking.context.Holder;
import by.valvik.banking.domain.Client;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.BALANCE;
import static by.valvik.banking.view.Pages.INFO;
import static java.lang.String.valueOf;

public class BalanceCommand implements Command {

    private static final String YOUR_BALANCE = "Your balance: %s";

    @Override
    public Page execute(Holder holder) {

        Client client = holder.getClient();

        holder.add(BALANCE, YOUR_BALANCE.formatted(valueOf(client.card().balance())));

        return INFO.getPage();

    }

}
