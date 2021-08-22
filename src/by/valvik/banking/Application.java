package by.valvik.banking;

import by.valvik.banking.command.ICommandType;
import by.valvik.banking.command.impl.AuthCommand;
import by.valvik.banking.command.impl.ClientCommand;
import by.valvik.banking.controller.Controller;
import by.valvik.banking.context.Holder;
import by.valvik.banking.context.Params;
import by.valvik.banking.view.Page;
import by.valvik.banking.view.Pages;

public class Application {

    private final Controller controller = new Controller();

    private final Holder holder = new Holder();

    public void run(String[] args) {

        Page page;

        ICommandType[] commandTypes;

        holder.add(Params.DB_NAME, args[1]);

        do {

            if (holder.isAuthorize()) {

                page = Pages.CLIENT_PAGE;

                commandTypes = ClientCommand.values();

            } else {

                page = Pages.AUTHORIZATION_PAGE;

                commandTypes = AuthCommand.values();

            }

            page.display(holder);

            controller.execute(holder, commandTypes);

        } while (!holder.isExit());

    }

}
