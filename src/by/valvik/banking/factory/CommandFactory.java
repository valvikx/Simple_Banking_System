package by.valvik.banking.factory;

import by.valvik.banking.command.ICommand;
import by.valvik.banking.command.ICommandType;
import by.valvik.banking.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public final class CommandFactory {

    private static final Map<ICommandType, ICommand> COMMAND_MAP = new HashMap<>() {

        {

            put(AuthCommand.CREATE_ACCOUNT, new CardCreateCommand());

            put(AuthCommand.LOG_IN, new LoginCommand());

            put(AuthCommand.EXIT, new ExitCommand());

            put(ClientCommand.BALANCE, new BalanceCommand());

            put(ClientCommand.ADD_INCOME, new IncomeCommand());

            put(ClientCommand.DO_TRANSFER, new TransferCommand());

            put(ClientCommand.CLOSE_ACCOUNT, new CloseAccountCommand());

            put(ClientCommand.LOG_OUT, new LogoutCommand());

            put(ClientCommand.EXIT, new ExitCommand());

        }

    };

    public static ICommand getCommand(ICommandType command) {

        return COMMAND_MAP.get(command);

    }

    private CommandFactory() {


    }

}
