package banking.command.impl;

import banking.domain.Client;
import banking.exception.AppException;
import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;
import banking.view.Pages;

public class BalanceCommand extends AbstractCommand {

    @Override
    public IPage execute(Model model) throws AppException {

        Client client = model.getClient();

        model.add(Params.BALANCE, String.valueOf(client.getBalance()));

        return Pages.BALANCE_PAGE;

    }

}
