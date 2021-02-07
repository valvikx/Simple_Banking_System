package banking.command.impl;

import banking.command.ICommandType;

public enum AuthCommand implements ICommandType {

    EXIT,
    CREATE_ACCOUNT,
    LOG_IN

}
