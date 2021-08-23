package by.valvik.banking.command.impl;

import by.valvik.banking.command.Command;
import by.valvik.banking.domain.Card;
import by.valvik.banking.domain.Client;
import by.valvik.banking.context.Holder;
import by.valvik.banking.constant.Param;
import by.valvik.banking.exception.ServiceException;
import by.valvik.banking.service.ClientService;
import by.valvik.banking.service.impl.ClientServiceImpl;
import by.valvik.banking.view.Page;

import java.util.Objects;

import static by.valvik.banking.constant.Param.CARD_NUMBER;
import static by.valvik.banking.constant.Param.MESSAGE;
import static by.valvik.banking.util.Cards.isValid;
import static by.valvik.banking.view.Pages.*;

public class TransferCommand implements Command {

    private static final String MISTAKE_IN_THE_CARD_NUMBER = "Probably you made a mistake in the card number. Please try again!";

    private static final String CAN_NOT_TRANSFER_MONEY_TO_THE_SAME_ACCOUNT = "You can't transfer money to the same account!";

    private static final String SUCCESS = "Success!";

    private static final String WRONG_AMOUNT_OF_MONEY = "Wrong amount of money!";

    private final ClientService clientService;

    public TransferCommand() {

        clientService = ClientServiceImpl.getInstance();

    }

    @Override
    public Page execute(Holder holder) {

        Client sourceClient = holder.getClient();

        Page page = TRANSFER_CARD.getPage();

        page.display(holder);

        String targetCardNumber = holder.get(CARD_NUMBER);

        if (!isValid(targetCardNumber)) {

            holder.add(MESSAGE, MISTAKE_IN_THE_CARD_NUMBER);

            return INFO.getPage();

        }

        if (Objects.equals(sourceClient.card().number(), targetCardNumber)) {

            holder.add(MESSAGE, CAN_NOT_TRANSFER_MONEY_TO_THE_SAME_ACCOUNT);

            return INFO.getPage();

        }

        try {

            Client targetClient = clientService.get(new Card(targetCardNumber));

            page = TRANSFER_MONEY.getPage();

            page.display(holder);

            String transferAmount = holder.get(Param.TRANSFER_MONEY);

            int amount = Integer.parseInt(transferAmount);

            clientService.transfer(sourceClient, targetClient, amount);

            holder.add(MESSAGE, SUCCESS);

        } catch (NumberFormatException e) {

            holder.add(MESSAGE, WRONG_AMOUNT_OF_MONEY);

        } catch (ServiceException e) {

            holder.add(MESSAGE, e.getMessage());

        }

        return INFO.getPage();

    }

}
