package by.valvik.banking.command.impl;

import by.valvik.banking.exception.AppException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class LogoutCommand extends AbstractCommand {

    @Override
    public Page execute(Holder holder) throws AppException {

        holder.setClient(null);

        holder.setAuthorize(false);

        holder.add(Params.MESSAGE, "You have successfully logged out!");

        return Pages.INFO_PAGE;

    }
}
