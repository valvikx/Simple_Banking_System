package by.valvik.banking.service.impl;

import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.dao.connection.DbConnection;
import by.valvik.banking.dao.impl.ClientDaoImpl;
import by.valvik.banking.domain.Card;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.exception.ServiceException;
import by.valvik.banking.service.TransactionService;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionServiceImpl implements TransactionService {

    private static final TransactionService INSTANCE = new TransactionServiceImpl();

    private static final String NOT_ENOUGH_MONEY = "Not enough money!";

    private final ClientDao clientDao;

    private final DbConnection dbConnection;

    private TransactionServiceImpl() {

        clientDao = ClientDaoImpl.getInstance();

        dbConnection = DbConnection.getInstance();

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

            clientDao.updateBalance(connection, updatedSourceCard.balance());

            clientDao.updateBalance(connection, updatedTargetCard.balance());

        } catch (DaoException | SQLException e) {

            throw new ServiceException(e.getMessage());

        }

    }

    public static TransactionService getInstance() {

        return INSTANCE;

    }

}
