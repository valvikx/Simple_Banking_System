package by.valvik.banking.dao;

import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.DaoException;

import java.sql.Connection;
import java.util.Optional;

public interface ClientDao {

    Optional<Client> get(int id) throws DaoException;

    Optional<Client> get(String cardNumber) throws DaoException;

    void save(Client client) throws DaoException;

    void delete(int id) throws DaoException;

    void updateBalance(int balance, int id) throws DaoException;

}
