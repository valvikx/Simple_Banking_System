package by.valvik.banking.util;

import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.Math.ceil;
import static java.util.stream.Collectors.joining;

public final class Cards {

    private static final String IIN = "400000";

    private static final int MAX_RANK = 9;

    private static final int PIN_LENGTH = 4;

    private static final Random RANDOM = new Random();

    private Cards() {

    }

    public static String generateNumber() {

        String cardSequence = IIN + getSequence(MAX_RANK);

        int sum = getSum(cardSequence);

        int checkSum = (int) ceil(sum / 10.) * 10 - sum;

        return cardSequence + checkSum;

    }

    public static String generatePin() {

        return getSequence(PIN_LENGTH);

    }

    public static boolean isValid(String cardNumber) {

        return getSum(cardNumber) % 10 == 0;

    }

    private static String getSequence(int length) {

        return IntStream.range(0, length)
                        .mapToObj(i -> Integer.toString(RANDOM.nextInt(MAX_RANK + 1)))
                        .collect(joining());

    }

    private static int getSum(String sequence) {

        int[] ints = sequence.chars()
                             .map(Character::getNumericValue)
                             .toArray();

        return IntStream.range(0, ints.length)
                        .map(i -> i % 2 == 0 ? 2 * ints[i] : ints[i])
                        .map(i -> i > 9 ? i - 9 : i)
                        .sum();

    }

}
