package by.valvik.banking.command.impl;

import by.valvik.banking.exception.AppException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class ExitCommand extends AbstractCommand {

    @Override
    public Page execute(Holder holder) throws AppException {

        realiseConnection();

        holder.setExit(true);

        holder.add(Params.MESSAGE, "Bye!");

        return Pages.INFO_PAGE;

    }

}
