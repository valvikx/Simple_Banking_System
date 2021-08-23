package by.valvik.banking.factory;

import by.valvik.banking.command.Command;
import by.valvik.banking.command.impl.*;
import by.valvik.banking.constant.ClientCommand;
import by.valvik.banking.constant.UserCommand;

import java.util.Map;

import static java.util.Map.of;

public class CommandFactory {

    private final Map<Enum<?>, Command> map;

    public CommandFactory() {

        map = of(UserCommand.CREATE_ACCOUNT, new CreateAccountCommand(),
                 UserCommand.LOG_IN, new LoginCommand(),
                 UserCommand.EXIT, new ExitCommand(),
                 ClientCommand.BALANCE, new BalanceCommand(),
                 ClientCommand.ADD_INCOME, new IncomeCommand(),
                 ClientCommand.DO_TRANSFER, new TransferCommand(),
                 ClientCommand.CLOSE_ACCOUNT, new CloseAccountCommand(),
                 ClientCommand.LOG_OUT, new LogoutCommand(),
                 ClientCommand.EXIT, new ExitCommand());

    }

    public Command getCommand(Enum<?> command) {

        return map.get(command);

    }

}
