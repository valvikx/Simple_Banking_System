package by.valvik.banking.command.impl;

import by.valvik.banking.exception.DaoException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class ExitCommand extends AbstractCommand {

    @Override
    public Page execute(Holder holder) throws DaoException {

        realiseConnection();

        holder.setExit(true);

        holder.add(Param.MESSAGE, "Bye!");

        return Pages.INFO;

    }

}
