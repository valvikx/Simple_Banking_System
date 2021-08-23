package by.valvik.banking.command.impl;

import by.valvik.banking.command.Command;
import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.MESSAGE;
import static by.valvik.banking.view.Pages.INFO;

public class ExitCommand implements Command {

    private static final String BYE = "Bye!";

    @Override
    public Page execute(Holder holder) {

        holder.setExit(true);

        holder.add(MESSAGE, BYE);

        return INFO.getPage();

    }

}
