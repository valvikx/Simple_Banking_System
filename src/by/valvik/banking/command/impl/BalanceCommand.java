package by.valvik.banking.command.impl;

import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.AppException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class BalanceCommand extends AbstractCommand {

    @Override
    public Page execute(Holder holder) throws AppException {

        Client client = holder.getClient();

        holder.add(Params.BALANCE, String.valueOf(client.getBalance()));

        return Pages.BALANCE_PAGE;

    }

}
