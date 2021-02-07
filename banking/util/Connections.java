package banking.util;

import banking.exception.AppException;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Objects;

public final class Connections {

    private static Connections instance;

    private Connection connection;

    private Connections() {

    }

    public static Connections getInstance() {

        if (Objects.isNull(instance)) {

            instance = new Connections();

        }

        return instance;

    }

    public Connection getConnection(String fileName) throws AppException {

        if (Objects.nonNull(connection)) {

            return connection;

        }

        String url = "jdbc:sqlite:" + fileName;

        SQLiteDataSource dataSource = new SQLiteDataSource();

        dataSource.setUrl(url);

        try {

            connection =  dataSource.getConnection();

            return connection;

        } catch (SQLException e) {

            throw new AppException(e.getMessage());

        }

    }

    public void releaseConnection() throws AppException {

        if (connection != null) {

            try {

                connection.close();

            } catch (SQLException e) {

                throw new AppException(e.getMessage());

            }

        }

    }

}
