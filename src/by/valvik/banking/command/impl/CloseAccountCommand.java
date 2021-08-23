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

public class CloseAccountCommand implements Command {

    private static final String ACCOUNT_HAS_BEEN_CLOSED = "The account has been closed!";

    private final ClientService clientService;

    public CloseAccountCommand() {

        clientService = ClientServiceImpl.getInstance();

    }

    @Override
    public Page execute(Holder holder) {

        Client client = holder.getClient();

        try {

            clientService.delete(client);

            holder.setClient(null);

            holder.add(MESSAGE, ACCOUNT_HAS_BEEN_CLOSED);

        } catch (ServiceException e) {

            holder.add(MESSAGE, e.getMessage());

        }

        return INFO.getPage();

    }

}
