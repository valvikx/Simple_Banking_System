package banking.dao.handler;

import banking.domain.Card;
import banking.domain.Client;
import banking.executor.IHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ClientHandler implements IHandler<Optional<Client>> {

    private static final ClientHandler INSTANCE = new ClientHandler();

    private ClientHandler() {

    }

    public static ClientHandler getInstance() {

        return INSTANCE;

    }

    @Override
    public Optional<Client> handle(ResultSet resultSet) throws SQLException {

        if (!resultSet.next()) {

            return Optional.empty();

        }

        String number = resultSet.getString("number");

        String pin = resultSet.getString("pin");

        int balance = resultSet.getInt("balance");

        return Optional.of(new Client(new Card(number, pin), balance));

    }

}
