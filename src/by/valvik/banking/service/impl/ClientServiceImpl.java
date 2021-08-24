package by.valvik.banking.service.impl;

import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.connection.DbConnection;
import by.valvik.banking.dao.impl.ClientDaoImpl;
import by.valvik.banking.domain.Card;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.exception.ServiceException;
import by.valvik.banking.service.ClientService;
import by.valvik.banking.service.TransactionService;
import by.valvik.banking.service.proxy.TransactionProxy;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public class ClientServiceImpl implements ClientService {

    private static final ClientService INSTANCE = new ClientServiceImpl();

    private static final String WRONG_CARD_NUMBER_OR_PIN = "Wrong card number or PIN!";

    private static final String SUCH_A_CARD_DOES_NOT_EXIST = "Such a card does not exist!";

    private static final String NOT_ENOUGH_MONEY = "Not enough money!";

    private final DbConnection dbConnection;

    private final ClientDao clientDao;

    private final TransactionProxy transactionProxy;

    private final TransactionService transactionService;

    private ClientServiceImpl() {

        dbConnection = DbConnection.getInstance();

        clientDao = ClientDaoImpl.getInstance();

        transactionProxy = TransactionProxy.getInstance();

        transactionService = TransactionServiceImpl.getInstance();

    }

    @Override
    public Client get(Card card) throws ServiceException {

        try {

            if (Objects.nonNull(card.pin())) {

                return clientDao.get(card.hashCode())
                                .orElseThrow(() -> new ServiceException(WRONG_CARD_NUMBER_OR_PIN));

            }

            return clientDao.get(card.number())
                            .orElseThrow(() -> new ServiceException(SUCH_A_CARD_DOES_NOT_EXIST));


        } catch (DaoException e) {

            throw new ServiceException(e.getMessage());

        }

    }

    @Override
    public void save(Client client) throws ServiceException {

        try {

            clientDao.save(client);

        } catch (DaoException e) {

            throw new ServiceException(e.getMessage());

        }

    }

    @Override
    public void delete(Client client) throws ServiceException {

        try {

            clientDao.delete(client.hashCode());

        } catch (DaoException e) {

            throw new ServiceException(e.getMessage());

        }

    }

    @Override
    public void deposit(Client client, int amount) throws ServiceException {

        try {

            Card card = client.addToBalance(amount);

            clientDao.updateBalance(card.balance(), card.hashCode());

        } catch (DaoException e) {

            throw new ServiceException(e.getMessage());

        }

    }

    @Override
    public void transfer(Client source, Client target, int amount) throws ServiceException {

        Card sourceCard = source.card();

        Card updatedSourceCard = source.writeOffBalance(amount);

        if (updatedSourceCard.balance() == sourceCard.balance()) {

            throw new ServiceException(NOT_ENOUGH_MONEY);

        }

        Card updatedTargetCard = target.addToBalance(amount);

        try (Connection connection = dbConnection.get()) {

            TransactionService proxy = transactionProxy.getProxy(transactionService, connection);

            proxy.updateBalances(connection, updatedSourceCard, updatedTargetCard);

        } catch (SQLException e) {

            throw new ServiceException(e.getMessage());

        }

    }

    public static ClientService getInstance() {

        return INSTANCE;

    }

}
