package banking.command.impl;

import banking.dao.IDao;
import banking.domain.Card;
import banking.domain.Client;
import banking.exception.AppException;
import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;
import banking.view.Pages;

public class LoginCommand extends AbstractCommand {

    @Override
    public IPage execute(Model model) throws AppException {

        IPage loginPage = Pages.LOGIN_PAGE;

        loginPage.display(model);

        String cardNumber = model.get(Params.CARD_NUMBER);

        String pin = model.get(Params.PIN);

        Card card = new Card(cardNumber, pin);

        String dbName = model.get(Params.DB_NAME);

        IDao dbDao = getDao(dbName);

        Client client = dbDao.getClient(card)
                             .orElseThrow(() -> new AppException("Wrong card number or PIN!"));

        model.setClient(client);

        model.setAuthorize(true);

        model.add(Params.MESSAGE, "You have successfully logged in!");

        return Pages.INFO_PAGE;

    }

}
