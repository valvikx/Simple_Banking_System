package banking.domain;

import java.util.Objects;

public class Card {

    private final String number;

    private final String pin;

    public Card(String number, String pin) {

        this.number = number;

        this.pin = pin;

    }

    public String getNumber() {

        return number;

    }

    public String getPin() {

        return pin;

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        return Objects.equals(number, card.number) &&
               Objects.equals(pin, card.pin);
    }

    @Override
    public int hashCode() {

        return Math.abs(Objects.hash(number, pin));

    }

}
