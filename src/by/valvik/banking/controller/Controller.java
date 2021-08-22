package by.valvik.banking.controller;

import by.valvik.banking.factory.CommandFactory;
import by.valvik.banking.command.ICommand;
import by.valvik.banking.command.ICommandType;
import by.valvik.banking.exception.AppException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class Controller {

    public void execute(Holder holder, ICommandType[] commandTypes) {

        Page page;

        try {

            int commandItem = Integer.parseInt(holder.get(Params.ITEM));

            if (commandItem > commandTypes.length - 1) {

                throw new AppException("Invalid command!");

            }

            ICommandType commandType = commandTypes[commandItem];

            ICommand command = CommandFactory.getCommand(commandType);

            page = command.execute(holder);

        } catch (NumberFormatException | AppException e) {

            holder.add(Params.MESSAGE, e.getMessage());

            page = Pages.INFO_PAGE;

        }

        page.display(holder);

    }

}
