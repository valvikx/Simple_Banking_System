package by.valvik.banking.controller;

import by.valvik.banking.command.Command;
import by.valvik.banking.constant.Param;
import by.valvik.banking.context.Holder;
import by.valvik.banking.factory.CommandFactory;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.COMMAND_OPTION;
import static by.valvik.banking.view.Pages.INFO;

public class Controller {

    private static final String INVALID_COMMAND = "Invalid command!";

    public void execute(Holder holder, Enum<?>[] commands) {

        Page page;

        try {

            int commandOption = Integer.parseInt(holder.get(COMMAND_OPTION));

            if (commandOption > commands.length - 1) {

                holder.add(Param.MESSAGE, INVALID_COMMAND);

                page = INFO.getPage();

            } else {

                Enum<?> commandType = commands[commandOption];

                Command command = CommandFactory.getCommand(commandType);

                page = command.execute(holder);

            }

        } catch (NumberFormatException e) {

            holder.add(Param.MESSAGE, INVALID_COMMAND);

            page = INFO.getPage();

        }

        page.display(holder);

    }

}
