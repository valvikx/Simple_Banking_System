package banking.command.impl;

import banking.dao.IDao;
import banking.domain.Client;
import banking.exception.AppException;
import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;
import banking.view.Pages;

public class IncomeCommand extends AbstractCommand {

    @Override
    public IPage execute(Model model) throws AppException {

        IPage incomePage = Pages.INCOME_PAGE;

        incomePage.display(model);

        String money = model.get(Params.INCOME);

        try {

            Client client = model.getClient();

            int value = Integer.parseInt(money);

            String dbName = model.get(Params.DB_NAME);

            IDao dbDao = getDao(dbName);

            dbDao.updateClient(client, value);

            model.add(Params.MESSAGE, "Income was added!");

            return Pages.INFO_PAGE;

        } catch (NumberFormatException e) {

            throw new AppException("Wrong amount of money!");

        }

    }

}
