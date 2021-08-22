package by.valvik.banking.command.impl;

import by.valvik.banking.command.Command;
import by.valvik.banking.dao.impl.ClientDaoImpl;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.dao.connection.DbConnection;

import java.sql.Connection;

public abstract class AbstractCommand implements Command {

    private final DbConnection db = DbConnection.getInstance();

    protected ClientDaoImpl getDao(String dbName) throws DaoException {

        Connection connection = db.get(dbName);

        return new ClientDaoImpl(connection);

    }

    protected void realiseConnection() throws DaoException {

        db.releaseConnection();

    }

}
