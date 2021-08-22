package by.valvik.banking.command.impl;

import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.AppException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class IncomeCommand extends AbstractCommand {

    @Override
    public Page execute(Holder holder) throws AppException {

        Page incomePage = Pages.INCOME_PAGE;

        incomePage.display(holder);

        String money = holder.get(Params.INCOME);

        try {

            Client client = holder.getClient();

            int value = Integer.parseInt(money);

            String dbName = holder.get(Params.DB_NAME);

            ClientDao dbClientDao = getDao(dbName);

            dbClientDao.update(client, value);

            holder.add(Params.MESSAGE, "Income was added!");

            return Pages.INFO_PAGE;

        } catch (NumberFormatException e) {

            throw new AppException("Wrong amount of money!");

        }

    }

}
