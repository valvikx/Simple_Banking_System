package by.valvik.banking.command.impl;

import by.valvik.banking.dao.impl.ClientDaoImpl;
import by.valvik.banking.domain.Card;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.AppException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.util.Cards;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class CardCreateCommand extends AbstractCommand {

    @Override
    public Page execute(Holder holder) throws AppException {

        String cardNumber = Cards.generateNumber();

        holder.add(Params.CARD_NUMBER, cardNumber);

        String pin = Cards.generatePin();

        holder.add(Params.PIN, pin);

        Card card = new Card(cardNumber, pin);

        Client client = new Client(card);

        String dbName = holder.get(Params.DB_NAME);

        ClientDaoImpl dbDao = getDao(dbName);

        dbDao.createTable();

        dbDao.saveClient(client);

        return Pages.CARD_PAGE;

    }



}
