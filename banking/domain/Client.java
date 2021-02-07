package banking.domain;

import java.util.Objects;

public class Client {

    private final Card card;

    private int balance;

    public Client(Card card) {

        this.card = card;

    }

    public Client(Card card, int balance) {

        this.card = card;

        this.balance = balance;

    }

    public Card getCard() {

        return card;

    }

    public int getBalance() {

        return balance;

    }

    public void addToBalance(int value) {

        balance += value;

    }

    public boolean isWriteOffBalance(int value) {

        if (balance >= value) {

            balance -= value;

            return true;

        }

        return false;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Client client = (Client) o;

        return balance == client.balance && card.equals(client.card);

    }

    @Override
    public int hashCode() {

        return Objects.hash(card, balance);

    }

}
