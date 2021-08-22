package by.valvik.banking.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface Mapper<T> {

    T map(final ResultSet resultSet) throws SQLException;

}
