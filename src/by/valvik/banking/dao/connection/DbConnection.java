package by.valvik.banking.dao.connection;

import by.valvik.banking.exception.DaoException;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import static by.valvik.banking.util.Props.getValue;

public class DbConnection {

    private static final DbConnection INSTANCE = new DbConnection();

    private static final String JDBC_URL = "jdbc:sqlite:%s";

    private static final String DB_NAME = "db.name";

    private final SQLiteDataSource dataSource;

    private DbConnection() {

        dataSource = new SQLiteDataSource();

        dataSource.setUrl(JDBC_URL.formatted(getValue(DB_NAME)));

    }

    public Connection get() throws DaoException {

        try {

            return dataSource.getConnection();

        } catch (SQLException e) {

            throw new DaoException(e.getMessage());

        }

    }

    public static DbConnection getInstance() {

        return INSTANCE;

    }

}
