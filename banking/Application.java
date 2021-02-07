package banking;

import banking.command.ICommandType;
import banking.command.impl.AuthCommand;
import banking.command.impl.ClientCommand;
import banking.controller.Controller;
import banking.model.Model;
import banking.model.Params;
import banking.view.IPage;
import banking.view.Pages;

public class Application {

    private final Controller controller = new Controller();

    private final Model model = new Model();

    public void run(String[] args) {

        IPage page;

        ICommandType[] commandTypes;

        model.add(Params.DB_NAME, args[1]);

        do {

            if (model.isAuthorize()) {

                page = Pages.CLIENT_PAGE;

                commandTypes = ClientCommand.values();

            } else {

                page = Pages.AUTHORIZATION_PAGE;

                commandTypes = AuthCommand.values();

            }

            page.display(model);

            controller.execute(model, commandTypes);

        } while (!model.isExit());

    }

}
