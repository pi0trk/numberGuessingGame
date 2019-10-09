package pl.karnas;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pl.karnas.Hints.*;

class Engine {

    private int floor;
    private int ceiling;
    static int attempts = 1;

    Engine(int floor, int ceiling) {
        if (floor > ceiling) {
            throw new IllegalArgumentException("\n\n--- Flor is above the ceiling only in fairy tails! ---\n");
        }
        this.floor = floor;
        this.ceiling = ceiling;
    }

    static boolean isInLimit(int maxGuessTries) {
        attempts += 1;
        return attempts <= maxGuessTries;
    }

    int drawRandomNumber() {
        return new Random().nextInt(ceiling - floor + 1) + floor;
    }

    Enum<Hints> guessCheck(int yourGuess, int randomNumber) {
        if (isGuessed(yourGuess, randomNumber)) {
            return PERFECT_GUESS;
        } else if (!numberIsInRange(yourGuess)) {
            return NUMBER_NOT_IN_RANGE;
        } else if (yourGuess < randomNumber) {
            return NUMBER_TOO_LOW;
        } else {
            return NUMBER_TOO_HIGH;
        }
    }

    static String strategyFeedback(boolean solved, int binaryAttempts) {
        if (solved) {
            return String.format("\n" + "Your's strategy needed %s tries, while mine %s!", attempts - 1, binaryAttempts);
        } else {
            return "\nVery bad strategy, you're out of guess attempts!";
        }
    }

    int binarySearch(int randNr) {
        List<Integer> range = IntStream.rangeClosed(floor, ceiling).boxed()
                .collect(Collectors.toList());
        return Collections.binarySearch(range, randNr);
    }

    static boolean isGuessed(int yourGuess, int randomNumber) {
        return (yourGuess == randomNumber);
    }

    private boolean numberIsInRange(int number) {
        return floor <= number && number <= ceiling;
    }

    @Override
    public String toString() {
        return String.format("<%d, %d>", floor, ceiling);
    }
}
