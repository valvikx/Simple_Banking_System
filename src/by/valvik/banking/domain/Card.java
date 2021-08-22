package by.valvik.banking.domain;

import java.util.Objects;

import static java.lang.Math.abs;

public record Card(String number, String pin, int balance) {

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Card card)) return false;

        return Objects.equals(number, card.number) &&
               Objects.equals(pin, card.pin);
    }

    @Override
    public int hashCode() {

        return abs(Objects.hash(number, pin));

    }

}
