package by.valvik.banking.dao;

import by.valvik.banking.domain.Card;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.AppException;

import java.sql.Connection;
import java.util.Optional;

public interface ClientDao {

    Optional<Client> get(Connection connection, int id) throws AppException;

    Optional<Client> get(Connection connection, String cardNumber) throws AppException;

    void save(Connection connection, Client client) throws AppException;

    void update(Connection connection, Client client, int value) throws AppException;

    void delete(Connection connection, Client client) throws AppException;

    void transferToClient(Client client, Client transferClient, int value) throws AppException;

}
