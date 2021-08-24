package by.valvik.banking.dao;

import by.valvik.banking.exception.DaoException;

import java.sql.Connection;

public interface TransactionClientDao {

    void updateBalance(Connection connection, int balance, int id) throws DaoException;

}
