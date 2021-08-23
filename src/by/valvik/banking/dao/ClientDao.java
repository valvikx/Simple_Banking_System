package by.valvik.banking.dao;

import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;

import java.sql.Connection;
import java.util.Optional;

public interface ClientDao {

    Optional<Client> get(Connection connection, int id) throws DaoException;

    Optional<Client> get(Connection connection, String cardNumber) throws DaoException;

    void save(Connection connection, Client client) throws DaoException;

    void delete(Connection connection, int id) throws DaoException;

    void updateBalance(Connection connection, int balance, int id) throws DaoException;

}
