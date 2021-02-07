package banking.command.impl;

import banking.util.Cards;
import banking.dao.impl.DbDao;
import banking.domain.Card;
import banking.domain.Client;
import banking.exception.AppException;
import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;
import banking.view.Pages;

public class CardCreateCommand extends AbstractCommand {

    @Override
    public IPage execute(Model model) throws AppException {

        String cardNumber = Cards.generateNumber();

        model.add(Params.CARD_NUMBER, cardNumber);

        String pin = Cards.generatePin();

        model.add(Params.PIN, pin);

        Card card = new Card(cardNumber, pin);

        Client client = new Client(card);

        String dbName = model.get(Params.DB_NAME);

        DbDao dbDao = getDao(dbName);

        dbDao.createTable();

        dbDao.saveClient(client);

        return Pages.CARD_PAGE;

    }



}
