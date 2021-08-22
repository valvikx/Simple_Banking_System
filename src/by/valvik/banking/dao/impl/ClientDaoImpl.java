package by.valvik.banking.dao.impl;

import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.dao.mapper.impl.ClientMapper;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.dao.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class ClientDaoImpl implements ClientDao {

    private static final ClientDao INSTANCE = new ClientDaoImpl();

    private static final String CREATE_TABLE = """
       CREATE TABLE IF NOT EXISTS cards (id INTEGER NOT NULL,
                                         number TEXT NOT NULL,
                                         pin TEXT NOT NULL,
                                         balance INTEGER DEFAULT 0);""";

    private static final String GET_BY_ID = """
            SELECT number, pin, balance
                FROM cards
                    WHERE id = ?""";

    private static final String GET_BY_NUMBER = """
            SELECT number, pin, balance
                FROM cards
                    WHERE number = ?""";

    private static final String INSERT = "INSERT INTO cards(id, number, pin) VALUES (?, ?, ?)";

    private static final String UPDATE = """
            UPDATE cards
                SET number = ?, pin = ?, balance = ?
                    WHERE id = ?""";

    private static final String DELETE = "DELETE FROM cards WHERE id = ?";

    private final ClientMapper clientMapper;

    private final Executor executor;

    private ClientDaoImpl() {

        clientMapper = ClientMapper.getInstance();

        executor = Executor.getInstance();

    }

    @Override
    public Optional<Client> get(Connection connection, int id) throws DaoException {

        try {

            return executor.execute(connection, GET_BY_ID, clientMapper, id);

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    @Override
    public Optional<Client> get(Connection connection, String cardNumber) throws DaoException {

        try {

            return executor.execute(connection, GET_BY_NUMBER, clientMapper, cardNumber);

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    @Override
    public void save(Connection connection, Client client) throws DaoException {

        try {

            executor.execute(connection, INSERT, client.card().hashCode(),
                                                 client.card().number(),
                                                 client.card().pin());

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    @Override
    public void update(Connection connection, Client client) throws DaoException {

        try {

            executor.execute(connection, UPDATE, client.card().number(),
                                                 client.card().pin(),
                                                 client.card().balance());

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }


    }

    @Override
    public void delete(Connection connection, int id) throws DaoException {

        try {

            executor.execute(connection, DELETE, id);

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    @Override
    public void transferToClient(Client client, Client transferClient, int value) throws DaoException {

        try {

            if (!client.writeOffBalance(value)) {

                throw new DaoException("Not enough money!");

            }

            transferClient.addToBalance(value);

            executor.executeTransaction(UPDATE,
                                        new Object[] { client.getBalance(),
                                                       client.getCard().hashCode() },
                                        new Object[] { transferClient.getBalance(),
                                                       transferClient.getCard().hashCode() });

        } catch (SQLException e) {

            transferClient.writeOffBalance(value);

            client.addToBalance(value);

            throw new DaoException(e.getMessage());

        }

    }

    public void createTable(Connection connection) throws DaoException {

        try {

            executor.execute(connection, CREATE_TABLE);

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    public static ClientDao getInstance() {

        return INSTANCE;

    }

}
