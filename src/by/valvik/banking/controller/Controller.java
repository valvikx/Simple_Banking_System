package by.valvik.banking.controller;

import by.valvik.banking.command.Command;
import by.valvik.banking.constant.Param;
import by.valvik.banking.context.Holder;
import by.valvik.banking.exception.AppConfigurationException;
import by.valvik.banking.exception.ServiceException;
import by.valvik.banking.factory.CommandFactory;
import by.valvik.banking.service.DbService;
import by.valvik.banking.service.impl.DbServiceImpl;
import by.valvik.banking.view.Page;

import static by.valvik.banking.constant.Param.COMMAND_OPTION;
import static by.valvik.banking.view.Pages.INFO;

public record Controller(CommandFactory commandFactory) {

    private static final String INVALID_COMMAND = "Invalid command!";

    public Controller(CommandFactory commandFactory) {

        this.commandFactory = commandFactory;

        createTable();

    }

    public void execute(Holder holder, Enum<?>[] commands) {

        Page page;

        try {

            int commandOption = Integer.parseInt(holder.get(COMMAND_OPTION));

            if (commandOption > commands.length - 1) {

                holder.add(Param.MESSAGE, INVALID_COMMAND);

                page = INFO.getPage();

            } else {

                Enum<?> commandType = commands[commandOption];

                Command command = commandFactory.getCommand(commandType);

                page = command.execute(holder);

            }

        } catch (NumberFormatException e) {

            holder.add(Param.MESSAGE, INVALID_COMMAND);

            page = INFO.getPage();

        }

        page.display(holder);

    }

    private void createTable() {

        DbService dbService = DbServiceImpl.getInstance();

        try {

            dbService.createTable();

        } catch (ServiceException e) {

            throw new AppConfigurationException(e);

        }

    }

}
