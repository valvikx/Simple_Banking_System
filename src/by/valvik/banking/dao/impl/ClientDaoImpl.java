package by.valvik.banking.dao.impl;

import by.valvik.banking.dao.ClientDao;
import by.valvik.banking.dao.mapper.impl.ClientMapper;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.AppException;
import by.valvik.banking.dao.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class ClientDaoImpl implements ClientDao {

    private static final String CREATE_TABLE = """
       CREATE TABLE IF NOT EXISTS card (id INTEGER NOT NULL,
                                        number TEXT NOT NULL,
                                        pin TEXT NOT NULL,
                                        balance INTEGER DEFAULT 0);""";

    private static final String GET_BY_ID = """
            SELECT number, pin, balance
                FROM card
                    WHERE id = ?""";

    private static final String GET_BY_NUMBER = """
            SELECT number, pin, balance
                FROM card
                    WHERE number = ?""";

    private static final String ADD_NEW_CLIENT = "INSERT INTO card(id, number, pin) VALUES (?, ?, ?)";


    private static final String UPDATE_CLIENT_BY_ID = "UPDATE card SET balance = ? WHERE id = ?";

    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM card WHERE id = ?";

    private final ClientMapper clientMapper;

    private final Executor executor;

    public ClientDaoImpl() {

        clientMapper = ClientMapper.getInstance();

        executor = Executor.getInstance();

    }

    @Override
    public Optional<Client> get(Connection connection, int id) throws AppException {

        try {

            return executor.execute(connection, GET_BY_ID, clientMapper, id);

        } catch (SQLException e) {

            throw new AppException(e.getMessage());

        }

    }

    @Override
    public Optional<Client> getClient(String cardNumber) throws AppException {

        try {

            return executor.execute(GET_BY_NUMBER, clientMapper, cardNumber);

        } catch (SQLException e) {

            throw new AppException(e.getMessage());

        }

    }

    @Override
    public void saveClient(Client client) throws AppException {

        try {

            executor.execute(ADD_NEW_CLIENT,
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

            client.writeOffBalance(value);

            throw new AppException(e.getMessage());

        }


    }

    @Override
    public void deleteClient(Client client) throws AppException {

        try {

            executor.execute(DELETE_CLIENT_BY_ID,
                                   client.getCard().hashCode());

        } catch (SQLException e) {

            throw new AppException(e.getMessage());

        }

    }

    @Override
    public void transferToClient(Client client, Client transferClient, int value) throws AppException {

        try {

            if (!client.writeOffBalance(value)) {

                throw new AppException("Not enough money!");

            }

            transferClient.addToBalance(value);

            executor.executeTransaction(UPDATE_CLIENT_BY_ID,
                                        new Object[] { client.getBalance(),
                                                       client.getCard().hashCode() },
                                        new Object[] { transferClient.getBalance(),
                                                       transferClient.getCard().hashCode() });

        } catch (SQLException e) {

            transferClient.writeOffBalance(value);

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
