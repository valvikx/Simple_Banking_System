package banking.dao;

import banking.domain.Card;
import banking.domain.Client;
import banking.exception.AppException;

import java.util.Optional;

public interface IDao {

    Optional<Client> getClient(Card card) throws AppException;

    Optional<Client> getClient(String cardNumber) throws AppException;

    void saveClient(Client client) throws AppException;

    void updateClient(Client client, int value) throws AppException;

    void deleteClient(Client client) throws AppException;

    void transferToClient(Client client, Client transferClient, int value) throws AppException;

}
