package by.valvik.banking.domain;

public record Client(Card card) {

    public Card addToBalance(int amount) {

        int balance = card.balance();

        balance += amount;

        return new Card(card.number(), card.pin(), balance);

    }

    public Card writeOffBalance(int amount) {

        int balance = card.balance();

        if (card.balance() >= amount) {

            balance -= amount;

            return new Card(card.number(), card.pin(), balance);

        }

        return card;

    }

}
