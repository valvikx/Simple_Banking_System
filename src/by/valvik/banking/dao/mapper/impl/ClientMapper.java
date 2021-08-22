package by.valvik.banking.dao.mapper.impl;

import by.valvik.banking.dao.mapper.Mapper;
import by.valvik.banking.domain.Card;
import by.valvik.banking.domain.Client;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class ClientMapper implements Mapper<Optional<Client>> {

    private static final ClientMapper INSTANCE = new ClientMapper();

    private static final String NUMBER = "number";

    private static final String PIN = "pin";

    private static final String BALANCE = "balance";

    private ClientMapper() {

    }

    @Override
    public Optional<Client> map(ResultSet resultSet) throws SQLException {

        if (!resultSet.next()) {

            return empty();

        }

        String number = resultSet.getString(NUMBER);

        String pin = resultSet.getString(PIN);

        int balance = resultSet.getInt(BALANCE);

        return of(new Client(new Card(number, pin, balance)));

    }

    public static ClientMapper getInstance() {

        return INSTANCE;

    }

}
