package by.valvik.banking.service.impl;

import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.dao.connection.DbConnection;
import by.valvik.banking.dao.impl.ClientDaoImpl;
import by.valvik.banking.domain.Card;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.exception.ServiceException;
import by.valvik.banking.service.ClientService;

import java.sql.Connection;
import java.sql.SQLException;

public class ClientServiceImpl implements ClientService {

    private static final ClientService INSTANCE = new ClientServiceImpl();

    private static final String WRONG_CARD_NUMBER_OR_PIN = "Wrong card number or PIN!";

    private final ClientDao clientDao;

    private final DbConnection dbConnection;

    private ClientServiceImpl() {

        clientDao = ClientDaoImpl.getInstance();

        dbConnection = DbConnection.getInstance();

    }

    @Override
    public Client get(Card card) throws ServiceException {

        try (Connection connection = dbConnection.get()) {

            return clientDao.get(connection, card.hashCode())
                            .orElseThrow(() -> new ServiceException(WRONG_CARD_NUMBER_OR_PIN));

        } catch (DaoException | SQLException e) {

            throw new ServiceException(e.getMessage());

        }

    }

    public static ClientService getInstance() {

        return INSTANCE;

    }

}
