package by.valvik.banking.command.impl;

import by.valvik.banking.dao.impl.ClientDaoImpl;
import by.valvik.banking.domain.Card;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.util.Cards;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class CardCreateCommand extends AbstractCommand {

    @Override
    public Page execute(Holder holder) throws DaoException {

        String cardNumber = Cards.generateNumber();

        holder.add(Param.CARD_NUMBER, cardNumber);

        String pin = Cards.generatePin();

        holder.add(Param.PIN, pin);

        Card card = new Card(cardNumber, pin);

        Client client = new Client(card);

        String dbName = holder.get(Param.DB_NAME);

        ClientDaoImpl dbDao = getDao(dbName);

        dbDao.createTable();

        dbDao.saveClient(client);

        return Pages.CARD;

    }



}
