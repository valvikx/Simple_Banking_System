package banking.command.impl;

import banking.exception.AppException;
import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;
import banking.view.Pages;

public class ExitCommand extends AbstractCommand {

    @Override
    public IPage execute(Model model) throws AppException {

        realiseConnection();

        model.setExit(true);

        model.add(Params.MESSAGE, "Bye!");

        return Pages.INFO_PAGE;

    }

}
