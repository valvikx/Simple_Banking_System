package by.valvik.banking.command.impl;

import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.domain.Card;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.AppException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class LoginCommand extends AbstractCommand {

    @Override
    public Page execute(Holder holder) throws AppException {

        Page loginPage = Pages.LOGIN_PAGE;

        loginPage.display(holder);

        String cardNumber = holder.get(Params.CARD_NUMBER);

        String pin = holder.get(Params.PIN);

        Card card = new Card(cardNumber, pin);

        String dbName = holder.get(Params.DB_NAME);

        ClientDao dbClientDao = getDao(dbName);

        Client client = dbClientDao.getClient(card)
                             .orElseThrow(() -> new AppException("Wrong card number or PIN!"));

        holder.setClient(client);

        holder.setAuthorize(true);

        holder.add(Params.MESSAGE, "You have successfully logged in!");

        return Pages.INFO_PAGE;

    }

}
