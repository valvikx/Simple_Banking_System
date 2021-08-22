package by.valvik.banking.command;

import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Page;

public interface Command {

    Page execute(Holder holder);

}
