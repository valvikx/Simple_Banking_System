package by.valvik.banking.command;

import by.valvik.banking.exception.AppException;
import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Page;

public interface ICommand {

    Page execute(Holder holder) throws AppException;

}
