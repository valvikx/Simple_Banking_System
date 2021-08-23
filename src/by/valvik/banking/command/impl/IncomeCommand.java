package by.valvik.banking.command.impl;

import by.valvik.banking.command.Command;
import by.valvik.banking.constant.Param;
import by.valvik.banking.context.Holder;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.ServiceException;
import by.valvik.banking.service.ClientService;
import by.valvik.banking.service.impl.ClientServiceImpl;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

import static by.valvik.banking.constant.Param.MESSAGE;
import static by.valvik.banking.view.Pages.INFO;

public class IncomeCommand implements Command {

    private static final String INCOME_WAS_ADDED = "Income was added!";

    private static final String WRONG_AMOUNT_OF_MONEY = "Wrong amount of money!";

    private final ClientService clientService;

    public IncomeCommand() {

        clientService = ClientServiceImpl.getInstance();

    }

    @Override
    public Page execute(Holder holder) {

        Page incomePage = Pages.INCOME.getPage();

        incomePage.display(holder);

        String income = holder.get(Param.INCOME);

        try {

            Client client = holder.getClient();

            int incomeAmount = Integer.parseInt(income);

            clientService.deposit(client, incomeAmount);

            holder.add(MESSAGE, INCOME_WAS_ADDED);

        } catch (NumberFormatException e) {

            holder.add(MESSAGE, WRONG_AMOUNT_OF_MONEY);

        } catch (ServiceException e) {

            holder.add(MESSAGE, e.getMessage());

        }

        return INFO.getPage();

    }

}
