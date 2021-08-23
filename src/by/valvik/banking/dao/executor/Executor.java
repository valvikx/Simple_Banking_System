package by.valvik.banking.dao.executor;

import by.valvik.banking.dao.mapper.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Executor {

    private static final Executor INSTANCE = new Executor();

    private Executor() {

    }

    public void execute(Connection connection, String sql, Object... args) throws SQLException {

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            setParams(statement, args);

            statement.executeUpdate();

        }

    }

    public <T> T execute(Connection connection, String sql, Mapper<T> mapper, Object... args) throws SQLException {

        try (final PreparedStatement statement = connection.prepareStatement(sql)) {

            setParams(statement, args);

            return mapper.map(statement.executeQuery());

        }

    }

    public static Executor getInstance() {

        return INSTANCE;

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
