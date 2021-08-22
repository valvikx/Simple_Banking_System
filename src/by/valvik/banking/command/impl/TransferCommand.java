package by.valvik.banking.command.impl;

import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.AppException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.util.Cards;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class TransferCommand extends AbstractCommand{

    @Override
    public Page execute(Holder holder) throws AppException {

        Client client = holder.getClient();

        Page page = Pages.TRANSFER_PAGE;

        page.display(holder);

        String cardNumber = holder.get(Params.CARD_NUMBER);

        if (!Cards.isValid(cardNumber)) {

            throw new AppException("Probably you made a mistake in the card number. Please try again!");

        }

        String dbName = holder.get(Params.DB_NAME);

        ClientDao dbClientDao = getDao(dbName);

        Client transferClient = dbClientDao.getClient(cardNumber)
                                     .orElseThrow(() -> new AppException("Such a card does not exist."));



        if (transferClient.equals(client)) {

            throw new AppException("You can't transfer money to the same account!");

        }

        page = Pages.TRANSFER_MONEY_PAGE;

        page.display(holder);

        String money = holder.get(Params.TRANSFER_MONEY);

        try {

            int value = Integer.parseInt(money);

            dbClientDao.transferToClient(client, transferClient, value);

            holder.add(Params.MESSAGE, "Success!");

            return Pages.INFO_PAGE;

        } catch (NumberFormatException e) {

            throw new AppException("Wrong amount of money!");

        }

    }

}
