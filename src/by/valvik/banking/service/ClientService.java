package by.valvik.banking.service;

import by.valvik.banking.domain.Card;
import by.valvik.banking.domain.Client;
import by.valvik.banking.exception.ServiceException;

public interface ClientService {

    Client get(Card card) throws ServiceException;

    void save(Client client) throws ServiceException;

    void delete(Client client) throws ServiceException;

    void deposit(Client client, int amount) throws ServiceException;

}
