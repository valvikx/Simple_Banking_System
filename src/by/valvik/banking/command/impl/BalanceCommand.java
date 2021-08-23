package by.valvik.banking.command.impl;

import by.valvik.banking.command.Command;
import by.valvik.banking.context.Holder;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.ServiceException;
import by.valvik.banking.service.ClientService;
import by.valvik.banking.service.impl.ClientServiceImpl;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.MESSAGE;
import static by.valvik.banking.view.Pages.INFO;
import static java.lang.String.valueOf;

public class BalanceCommand implements Command {

    private static final String YOUR_BALANCE = "Your balance: %s";

    private final ClientService clientService;

    public BalanceCommand() {

        clientService = ClientServiceImpl.getInstance();

    }

    @Override
    public Page execute(Holder holder) {

        try {

            Client client = clientService.get(holder.getClient().card());

            holder.add(MESSAGE, YOUR_BALANCE.formatted(valueOf(client.card().balance())));

        } catch (ServiceException e) {

            holder.add(MESSAGE, e.getMessage());

        }

        return INFO.getPage();

    }

}
