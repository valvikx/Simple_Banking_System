package by.valvik.banking.service;

import by.valvik.banking.domain.Card;
import by.valvik.banking.exception.ServiceException;

import java.sql.Connection;

public interface TransactionService {

    void updateBalances(Connection connection, Card source, Card target) throws ServiceException;

}
