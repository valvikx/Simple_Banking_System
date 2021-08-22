package by.valvik.banking.domain;

public record Client(Card card) {

    public int getBalance() {

        return card.balance();

    }

    public Card addToBalance(int value) {

        int balance = card.balance();

        balance += value;

        return new Card(card.number(), card.pin(), balance);

    }

    public Card writeOffBalance(int value) {

        int balance = card.balance();

        if (card.balance() >= value) {

            balance -= value;

            return new Card(card.number(), card.pin(), balance);

        }

        return card;

    }

}
