package banking.command.impl;

import banking.command.ICommand;
import banking.dao.impl.DbDao;
import banking.exception.AppException;
import banking.util.Connections;

import java.sql.Connection;

public abstract class AbstractCommand implements ICommand {

    private final Connections db = Connections.getInstance();

    protected DbDao getDao(String dbName) throws AppException {

        Connection connection = db.getConnection(dbName);

        return new DbDao(connection);

    }

    protected void realiseConnection() throws AppException {

        db.releaseConnection();

    }

}
