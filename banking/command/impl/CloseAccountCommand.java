package banking.command.impl;

import banking.dao.IDao;
import banking.domain.Client;
import banking.exception.AppException;
import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;
import banking.view.Pages;

public class CloseAccountCommand extends AbstractCommand {

    @Override
    public IPage execute(Model model) throws AppException {

        Client client = model.getClient();

        String dbName = model.get(Params.DB_NAME);

        IDao dbDao = getDao(dbName);

        dbDao.deleteClient(client);

        model.setClient(null);

        model.setAuthorize(false);

        model.add(Params.MESSAGE, "The account has been closed!");

        return Pages.INFO_PAGE;

    }

}
