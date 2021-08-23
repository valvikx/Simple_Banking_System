package by.valvik.banking.dao;

import by.valvik.banking.exception.DaoException;

import java.sql.Connection;

public interface DbDao {

    void createTable(Connection connection) throws DaoException;

}
