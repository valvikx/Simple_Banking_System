package by.valvik.banking.command.impl;

import by.valvik.banking.command.ICommandType;

public enum ClientCommand implements ICommandType {

    EXIT,
    BALANCE,
    ADD_INCOME,
    DO_TRANSFER,
    CLOSE_ACCOUNT,
    LOG_OUT

}
