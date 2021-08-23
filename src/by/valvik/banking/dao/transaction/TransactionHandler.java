package by.valvik.banking.dao.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public record TransactionHandler(Connection connection) {

    public void start() throws SQLException {

        if (connection.getAutoCommit()) {

            connection.setAutoCommit(false);

        }

    }

    public void commit() throws SQLException {

        connection.commit();

    }

    public void rollback() throws SQLException {

        connection.rollback();

    }

    public void finish() throws SQLException {

        if (!connection.getAutoCommit()) {

            connection.setAutoCommit(true);

        }

        connection.close();

    }

}
