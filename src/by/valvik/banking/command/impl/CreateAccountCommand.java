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
import static by.valvik.banking.util.Cards.generateNumber;
import static by.valvik.banking.util.Cards.generatePin;
import static by.valvik.banking.view.Pages.INFO;

public class CreateAccountCommand implements Command {

    public static final String YOUR_CARD_HAS_BEEN_CREATED = """
        Your card has been created.
        1) Card number: %s
        2) Card PIN: %s
        """;

    private final ClientService clientService;

    public CreateAccountCommand() {

        clientService = ClientServiceImpl.getInstance();

    }

    @Override
    public Page execute(Holder holder) {

        String cardNumber = generateNumber();

        String pin = generatePin();

        Client client = new Client(new Card(cardNumber, pin));

        try {

            clientService.save(client);

            holder.add(MESSAGE, YOUR_CARD_HAS_BEEN_CREATED.formatted(cardNumber, pin));

        } catch (ServiceException e) {

            holder.add(MESSAGE, e.getMessage());

        }

        return INFO.getPage();

    }

}