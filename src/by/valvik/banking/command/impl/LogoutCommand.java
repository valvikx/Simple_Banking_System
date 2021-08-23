package by.valvik.banking.command.impl;

import by.valvik.banking.command.Command;
import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.MESSAGE;
import static by.valvik.banking.view.Pages.INFO;

public class LogoutCommand implements Command {

    private static final String YOU_HAVE_SUCCESSFULLY_LOGGED_OUT = "You have successfully logged out!";

    @Override
    public Page execute(Holder holder) {

        holder.setClient(null);

        holder.add(MESSAGE, YOU_HAVE_SUCCESSFULLY_LOGGED_OUT);

        return INFO.getPage();

    }

}
