package banking.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface IHandler<T> {

    T handle(final ResultSet resultSet) throws SQLException;

}
