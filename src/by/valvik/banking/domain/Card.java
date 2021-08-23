package by.valvik.banking.domain;

import java.util.Objects;

import static java.lang.Math.abs;
import static java.util.Objects.hash;

public record Card(String number, String pin, int balance) {

    public Card(String number, String pin) {

        this(number, pin, 0);

    }

    public Card(String number) {

        this(number, null, 0);

    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Card card)) return false;

        return Objects.equals(number, card.number) &&
               Objects.equals(pin, card.pin);

    }

    @Override
    public int hashCode() {

        return abs(hash(number, pin));

    }

}
