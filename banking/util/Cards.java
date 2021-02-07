package banking.util;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class Cards {

    private static final String IIN = "400000";

    private static final int MAX_RANK = 9;

    private static final Random RANDOM = new Random();

    private Cards() {

    }

    public static String generateNumber() {

        int accountLength = 9;

        String cardSequence = IIN + getSequence(accountLength);

        int sum = getSum(cardSequence);

        int checkSum = (int) Math.ceil(sum / 10.) * 10 - sum;

        return cardSequence + checkSum;

    }

    public static String generatePin() {

        int pinLength = 4;

        return getSequence(pinLength);

    }

    public static boolean isValid(String cardNumber) {

        return getSum(cardNumber) % 10 == 0;

    }

    private static String getSequence(int length) {

        return IntStream.range(0, length)
                        .mapToObj(i -> Integer.toString(RANDOM.nextInt(MAX_RANK + 1)))
                        .collect(Collectors.joining());

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
