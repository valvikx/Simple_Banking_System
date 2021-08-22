package by.valvik.banking.command.impl;

import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class IncomeCommand extends AbstractCommand {

    @Override
    public Page execute(Holder holder) throws DaoException {

        Page incomePage = Pages.INCOME;

        incomePage.display(holder);

        String money = holder.get(Param.INCOME);

        try {

            Client client = holder.getClient();

            int value = Integer.parseInt(money);

            String dbName = holder.get(Param.DB_NAME);

            ClientDao dbClientDao = getDao(dbName);

            dbClientDao.update(client, value);

            holder.add(Param.MESSAGE, "Income was added!");

            return Pages.INFO;

        } catch (NumberFormatException e) {

            throw new DaoException("Wrong amount of money!");

        }

    }

}
