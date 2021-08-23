package by.valvik.banking.service;

import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.ServiceException;

public interface TransactionService {

    void transfer(Client source, Client target, int amount) throws ServiceException;

}
