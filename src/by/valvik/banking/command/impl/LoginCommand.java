package by.valvik.banking.command.impl;

import by.valvik.banking.command.Command;
import by.valvik.banking.context.Holder;
import by.valvik.banking.domain.Card;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.ServiceException;
import by.valvik.banking.service.ClientService;
import by.valvik.banking.service.impl.ClientServiceImpl;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.*;
import static by.valvik.banking.view.Pages.INFO;
import static by.valvik.banking.view.Pages.LOGIN;

public class LoginCommand implements Command {

    private static final String YOU_HAVE_SUCCESSFULLY_LOGGED_IN = "You have successfully logged in!";

    private final ClientService clientService;

    public LoginCommand() {

        clientService = ClientServiceImpl.getInstance();

    }

    @Override
    public Page execute(Holder holder) {

        Page loginPage = LOGIN.getPage();

        loginPage.display(holder);

        String cardNumber = holder.get(CARD_NUMBER);

        String pin = holder.get(PIN);

        Card card = new Card(cardNumber, pin);

        try {

            Client client = clientService.get(card);

            holder.setClient(client);

            holder.setAuthorize(true);

            holder.add(MESSAGE, YOU_HAVE_SUCCESSFULLY_LOGGED_IN);

        } catch (ServiceException e) {

            holder.add(MESSAGE, e.getMessage());

        }

        return INFO.getPage();

    }

}
