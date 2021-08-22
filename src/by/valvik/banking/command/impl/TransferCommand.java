package by.valvik.banking.command.impl;

import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.util.Cards;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class TransferCommand extends AbstractCommand{

    @Override
    public Page execute(Holder holder) throws DaoException {

        Client client = holder.getClient();

        Page page = Pages.TRANSFER;

        page.display(holder);

        String cardNumber = holder.get(Param.CARD_NUMBER);

        if (!Cards.isValid(cardNumber)) {

            throw new DaoException("Probably you made a mistake in the card number. Please try again!");

        }

        String dbName = holder.get(Param.DB_NAME);

        ClientDao dbClientDao = getDao(dbName);

        Client transferClient = dbClientDao.getClient(cardNumber)
                                     .orElseThrow(() -> new DaoException("Such a card does not exist."));



        if (transferClient.equals(client)) {

            throw new DaoException("You can't transfer money to the same account!");

        }

        page = Pages.TRANSFER_MONEY_PAGE;

        page.display(holder);

        String money = holder.get(Param.TRANSFER_MONEY);

        try {

            int value = Integer.parseInt(money);

            dbClientDao.transferToClient(client, transferClient, value);

            holder.add(Param.MESSAGE, "Success!");

            return Pages.INFO;

        } catch (NumberFormatException e) {

            throw new DaoException("Wrong amount of money!");

        }

    }

}
