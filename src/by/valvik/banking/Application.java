package by.valvik.banking;

import by.valvik.banking.constant.UserCommand;
import by.valvik.banking.constant.ClientCommand;
import by.valvik.banking.controller.Controller;
import by.valvik.banking.context.Holder;
import by.valvik.banking.view.Page;

import static by.valvik.banking.view.Pages.AUTHORIZATION;
import static by.valvik.banking.view.Pages.CLIENT;

public class Application {

    public void run() {

        Controller controller = new Controller();

        Holder holder = new Holder();

        Page page;

        Enum<?>[] commands;

        do {

            if (holder.isAuthorize()) {

                page = CLIENT.getPage();

                commands = ClientCommand.values();

            } else {

                page = AUTHORIZATION.getPage();

                commands = UserCommand.values();

            }

            page.display(holder);

            controller.execute(holder, commands);

        } while (!holder.isExit());

    }

}
