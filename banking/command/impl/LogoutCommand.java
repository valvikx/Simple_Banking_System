package banking.command.impl;

import banking.exception.AppException;
import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;
import banking.view.Pages;

public class LogoutCommand extends AbstractCommand {

    @Override
    public IPage execute(Model model) throws AppException {

        model.setClient(null);

        model.setAuthorize(false);

        model.add(Params.MESSAGE, "You have successfully logged out!");

        return Pages.INFO_PAGE;

    }
}
