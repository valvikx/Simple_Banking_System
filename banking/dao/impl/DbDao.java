package banking.dao.impl;

import banking.dao.IDao;
import banking.dao.handler.ClientHandler;
import banking.domain.Card;
import banking.domain.Client;
import banking.exception.AppException;
import banking.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class DbDao implements IDao {

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS card (" +
                                               "id INTEGER NOT NULL, " +
                                               "number TEXT NOT NULL, " +
                                               "pin TEXT NOT NULL, " +
                                               "balance INTEGER DEFAULT 0);";

    private static final String ADD_NEW_CLIENT = "INSERT INTO card(id, number, pin) VALUES (?, ?, ?)";

    private static final String GET_CLIENT_BY_ID = "SELECT number, pin, balance FROM card WHERE id = ?";

    private static final String GET_CLIENT_BY_CARD_NUMBER = "SELECT number, pin, balance FROM card WHERE number = ?";

    private static final String UPDATE_CLIENT_BY_ID = "UPDATE card SET balance = ? WHERE id = ?";

    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM card WHERE id = ?";

    private final ClientHandler clientHandler = ClientHandler.getInstance();

    private final Executor executor;

    public DbDao(Connection connection) {

        executor = new Executor(connection);

    }

    @Override
    public Optional<Client> getClient(Card card) throws AppException {

        try {

            return executor.executeQuery(GET_CLIENT_BY_ID, clientHandler, card.hashCode());

        } catch (SQLException e) {

            throw new AppException(e.getMessage());

        }

    }

    @Override
    public Optional<Client> getClient(String cardNumber) throws AppException {

        try {

            return executor.executeQuery(GET_CLIENT_BY_CARD_NUMBER, clientHandler, cardNumber);

        } catch (SQLException e) {

            throw new AppException(e.getMessage());

        }

    }

    @Override
    public void saveClient(Client client) throws AppException {

        try {

            executor.executeUpdate(ADD_NEW_CLIENT,
                                   client.getCard().hashCode(),
                                   client.getCard().getNumber(),
                                   client.getCard().getPin());

        } catch (SQLException e) {

            throw new AppException(e.getMessage());

        }

    }

    @Override
    public void updateClient(Client client, int value) throws AppException {

        try {

            client.addToBalance(value);

            executor.executeUpdate(UPDATE_CLIENT_BY_ID,
                                   client.getBalance(),
                                   client.getCard().hashCode());

        } catch (SQLException e) {

            client.isWriteOffBalance(value);

            throw new AppException(e.getMessage());

        }


    }

    @Override
    public void deleteClient(Client client) throws AppException {

        try {

            executor.executeUpdate(DELETE_CLIENT_BY_ID,
                                   client.getCard().hashCode());

        } catch (SQLException e) {

            throw new AppException(e.getMessage());

        }

    }

    @Override
    public void transferToClient(Client client, Client transferClient, int value) throws AppException {

        try {

            if (!client.isWriteOffBalance(value)) {

                throw new AppException("Not enough money!");

            }

            transferClient.addToBalance(value);

            executor.executeTransaction(UPDATE_CLIENT_BY_ID,
                                        new Object[] { client.getBalance(),
                                                       client.getCard().hashCode() },
                                        new Object[] { transferClient.getBalance(),
                                                       transferClient.getCard().hashCode() });

        } catch (SQLException e) {

            transferClient.isWriteOffBalance(value);

            client.addToBalance(value);

            throw new AppException(e.getMessage());

        }

    }

    public void createTable() throws AppException {

        try {

            executor.executeUpdate(CREATE_TABLE);

        } catch (SQLException e) {

            throw new AppException(e.getMessage());

        }

    }

}
