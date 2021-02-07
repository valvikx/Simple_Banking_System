package banking.executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Executor {

    private final Connection connection;

    public Executor(Connection connection) {

        this.connection = connection;

    }

    public void executeUpdate(String update, Object... args) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(update)) {

            setParams(statement, args);

            statement.executeUpdate();

        }

    }

    public void executeTransaction(String update, Object[]... args) throws SQLException {

        if (connection.getAutoCommit()) {

            connection.setAutoCommit(false);

        }

        try (PreparedStatement statementOne = connection.prepareStatement(update);
             PreparedStatement statementTwo = connection.prepareStatement(update)) {

            setParams(statementOne, args[0]);

            statementOne.executeUpdate();

            setParams(statementTwo, args[1]);

            statementTwo.executeUpdate();

            connection.commit();

        } catch (SQLException e) {

            connection.rollback();

        } finally {

            connection.setAutoCommit(true);

        }

    }

    public <T> T executeQuery(String query, IHandler<T> handler, Object... args) throws SQLException {

        try (final PreparedStatement statement = connection.prepareStatement(query)) {

            setParams(statement, args);

            return handler.handle(statement.executeQuery());

        }

    }

    private void setParams(PreparedStatement statement, Object... args) throws SQLException {

        for (int i = 0; i < args.length; i++) {

            if (args[i].getClass() == Integer.class) {

                statement.setInt(i + 1, (Integer) args[i]);

            } else {

                statement.setString(i + 1, (String) args[i]);

            }

        }

    }

}
