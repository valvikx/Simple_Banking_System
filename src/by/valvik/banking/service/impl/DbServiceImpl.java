package by.valvik.banking.service.impl;

import by.valvik.banking.dao.DbDao;
import by.valvik.banking.connection.DbConnection;
import by.valvik.banking.dao.impl.DbDaoImpl;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.exception.ServiceException;
import by.valvik.banking.service.DbService;

import java.sql.Connection;
import java.sql.SQLException;

public class DbServiceImpl implements DbService {

    private static final DbService INSTANCE = new DbServiceImpl();

    private final DbConnection dbConnection;

    private final DbDao dbDao;

    private DbServiceImpl() {

        dbConnection = DbConnection.getInstance();

        dbDao = DbDaoImpl.getInstance();

    }

    @Override
    public void createTable() throws ServiceException {

        try (Connection connection = dbConnection.get()) {

            dbDao.createTable(connection);

        } catch (DaoException | SQLException e) {

            throw new ServiceException(e.getMessage());

        }

    }

    public static DbService getInstance() {

        return INSTANCE;

    }

}
