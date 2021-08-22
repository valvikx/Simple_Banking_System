package by.valvik.banking.command.impl;

import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class CloseAccountCommand extends AbstractCommand {

    @Override
    public Page execute(Holder holder) throws DaoException {

        Client client = holder.getClient();

        String dbName = holder.get(Param.DB_NAME);

        ClientDao dbClientDao = getDao(dbName);

        dbClientDao.delete(client);

        holder.setClient(null);

        holder.setAuthorize(false);

        holder.add(Param.MESSAGE, "The account has been closed!");

        return Pages.INFO;

    }

}
