package by.valvik.banking;

import by.valvik.banking.constant.UserCommand;
import by.valvik.banking.constant.ClientCommand;
import by.valvik.banking.controller.Controller;
import by.valvik.banking.context.Holder;
import by.valvik.banking.factory.CommandFactory;
import by.valvik.banking.view.Page;

import java.util.Objects;

import static by.valvik.banking.view.Pages.USER;
import static by.valvik.banking.view.Pages.CLIENT;

public class Application {

    public void run() {

        CommandFactory commandFactory = new CommandFactory();

        Controller controller = new Controller(commandFactory);

        Holder holder = new Holder();

        Page page;

        Enum<?>[] commands;

        do {

            if (Objects.nonNull(holder.getClient())) {

                page = CLIENT.getPage();

                commands = ClientCommand.values();

            } else {

                page = USER.getPage();

                commands = UserCommand.values();

            }

            page.display(holder);

            controller.execute(holder, commands);

        } while (!holder.isExit());

    }

}
