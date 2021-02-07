package banking.controller;

import banking.command.CommandManager;
import banking.command.ICommand;
import banking.command.ICommandType;
import banking.exception.AppException;
import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;
import banking.view.Pages;

public class Controller {

    public void execute(Model model, ICommandType[] commandTypes) {

        IPage page;

        try {

            int commandItem = Integer.parseInt(model.get(Params.ITEM));

            if (commandItem > commandTypes.length - 1) {

                throw new AppException("Invalid command!");

            }

            ICommandType commandType = commandTypes[commandItem];

            ICommand command = CommandManager.getCommand(commandType);

            page = command.execute(model);

        } catch (NumberFormatException | AppException e) {

            model.add(Params.MESSAGE, e.getMessage());

            page = Pages.INFO_PAGE;

        }

        page.display(model);

    }

}
