package by.valvik.banking.dao.impl;

import by.valvik.banking.connection.DbConnection;
import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.dao.TransactionClientDao;
import by.valvik.banking.dao.mapper.impl.ClientMapper;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.dao.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class ClientDaoImpl implements ClientDao, TransactionClientDao {

    private static final ClientDaoImpl INSTANCE = new ClientDaoImpl();

    private static final String GET_BY_ID = """
            SELECT number, pin, balance
                FROM cards
                    WHERE id = ?""";

    private static final String GET_BY_CARD_NUMBER = """
            SELECT number, pin, balance
                FROM cards
                    WHERE number = ?""";

    private static final String INSERT = "INSERT INTO cards(id, number, pin) VALUES (?, ?, ?)";

    private static final String UPDATE_BALANCE = """
            UPDATE cards
                SET balance = ?
                    WHERE id = ?""";

    private static final String DELETE = "DELETE FROM cards WHERE id = ?";

    private final DbConnection dbConnection;

    private final ClientMapper clientMapper;

    private final Executor executor;

    private ClientDaoImpl() {

        dbConnection = DbConnection.getInstance();

        clientMapper = ClientMapper.getInstance();

        executor = Executor.getInstance();

    }

    @Override
    public Optional<Client> get(int id) throws DaoException {

        try (Connection connection = dbConnection.get()){

            return executor.execute(connection, GET_BY_ID, clientMapper, id);

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    @Override
    public Optional<Client> get(String cardNumber) throws DaoException {

        try(Connection connection = dbConnection.get()) {

            return executor.execute(connection, GET_BY_CARD_NUMBER, clientMapper, cardNumber);

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    @Override
    public void save(Client client) throws DaoException {

        try(Connection connection = dbConnection.get()) {

            executor.execute(connection, INSERT, client.card().hashCode(),
                                                 client.card().number(),
                                                 client.card().pin());

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    @Override
    public void delete(int id) throws DaoException {

        try(Connection connection = dbConnection.get()) {

            executor.execute(connection, DELETE, id);

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    @Override
    public void updateBalance(int balance, int id) throws DaoException {

        try(Connection connection = dbConnection.get()) {

            updateBalance(connection, balance, id);

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    @Override
    public void updateBalance(Connection connection, int balance, int id) throws DaoException {

        try {

            executor.execute(connection, UPDATE_BALANCE, balance, id);

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    public static ClientDaoImpl getInstance() {

        return INSTANCE;

    }

}
