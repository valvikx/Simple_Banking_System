package by.valvik.banking.command.impl;

import by.valvik.banking.command.ICommand;
import by.valvik.banking.dao.impl.ClientDaoImpl;
import by.valvik.banking.exception.AppException;
import by.valvik.banking.dao.connection.DbConnection;

import java.sql.Connection;

public abstract class AbstractCommand implements ICommand {

    private final DbConnection db = DbConnection.getInstance();

    protected ClientDaoImpl getDao(String dbName) throws AppException {

        Connection connection = db.getConnection(dbName);

        return new ClientDaoImpl(connection);

    }

    protected void realiseConnection() throws AppException {

        db.releaseConnection();

    }

}
