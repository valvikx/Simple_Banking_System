package banking.command.impl;

import banking.dao.IDao;
import banking.domain.Client;
import banking.exception.AppException;
import banking.model.Model;
import banking.model.Params;
import banking.util.Cards;
import banking.view.IPage;
import banking.view.Pages;

public class TransferCommand extends AbstractCommand{

    @Override
    public IPage execute(Model model) throws AppException {

        Client client = model.getClient();

        IPage page = Pages.TRANSFER_PAGE;

        page.display(model);

        String cardNumber = model.get(Params.CARD_NUMBER);

        if (!Cards.isValid(cardNumber)) {

            throw new AppException("Probably you made a mistake in the card number. Please try again!");

        }

        String dbName = model.get(Params.DB_NAME);

        IDao dbDao = getDao(dbName);

        Client transferClient = dbDao.getClient(cardNumber)
                                     .orElseThrow(() -> new AppException("Such a card does not exist."));



        if (transferClient.equals(client)) {

            throw new AppException("You can't transfer money to the same account!");

        }

        page = Pages.TRANSFER_MONEY_PAGE;

        page.display(model);

        String money = model.get(Params.TRANSFER_MONEY);

        try {

            int value = Integer.parseInt(money);

            dbDao.transferToClient(client, transferClient, value);

            model.add(Params.MESSAGE, "Success!");

            return Pages.INFO_PAGE;

        } catch (NumberFormatException e) {

            throw new AppException("Wrong amount of money!");

        }

    }

}
