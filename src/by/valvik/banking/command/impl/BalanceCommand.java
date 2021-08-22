package by.valvik.banking.command.impl;

import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class BalanceCommand extends AbstractCommand {

    @Override
    public Page execute(Holder holder) throws DaoException {

        Client client = holder.getClient();

        holder.add(Param.BALANCE, String.valueOf(client.getBalance()));

        return Pages.BALANCE;

    }

}
