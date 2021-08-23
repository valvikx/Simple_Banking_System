package by.valvik.banking.dao.impl;

import by.valvik.banking.dao.DbDao;
import by.valvik.banking.dao.executor.Executor;
import by.valvik.banking.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;

public class DbDaoImpl implements DbDao {

    private static final DbDao INSTANCE = new DbDaoImpl();

    private static final String CREATE_TABLE = """
       CREATE TABLE IF NOT EXISTS cards (id INTEGER NOT NULL,
                                         number TEXT NOT NULL,
                                         pin TEXT NOT NULL,
                                         balance INTEGER DEFAULT 0)""";

    private final Executor executor;

    private DbDaoImpl() {

        executor = Executor.getInstance();

    }

    public void createTable(Connection connection) throws DaoException {

        try {

            executor.execute(connection, CREATE_TABLE);

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    public static DbDao getInstance() {

        return INSTANCE;

    }

}
