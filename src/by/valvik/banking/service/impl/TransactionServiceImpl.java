package by.valvik.banking.service.impl;

import by.valvik.banking.dao.TransactionClientDao;
import by.valvik.banking.dao.impl.ClientDaoImpl;
import by.valvik.banking.domain.Card;
import by.valvik.banking.exception.DaoException;
import by.valvik.banking.exception.ServiceException;
import by.valvik.banking.service.TransactionService;

import java.sql.Connection;

public class TransactionServiceImpl implements TransactionService {

    private static final TransactionService INSTANCE = new TransactionServiceImpl();

    private final TransactionClientDao transactionClientDao;

    private TransactionServiceImpl() {

        transactionClientDao = ClientDaoImpl.getInstance();

    }

    @Override
    public void updateBalances(Connection connection, Card source, Card target) throws ServiceException {

        try {

            transactionClientDao.updateBalance(connection, source.balance(), source.hashCode());

            transactionClientDao.updateBalance(connection, target.balance(), target.hashCode());

        } catch (DaoException e) {

            throw new ServiceException(e.getMessage());

        }

    }

    public static TransactionService getInstance() {

        return INSTANCE;

    }

}
