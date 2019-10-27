package pl.karnas;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pl.karnas.Hints.*;

class Engine {

    private int floor;
    private int ceiling;
    private static int attempts = 1;
    private final Logger log = Logger.getLogger(Engine.class.getName());

    Engine(int floor, int ceiling) {
        if (floor > ceiling) {
            throw new IllegalArgumentException("\n\n--- Flor is above the ceiling only in fairy tails! ---\n");
        }
        this.floor = floor;
        this.ceiling = ceiling;
    }

    private static boolean isInLimit(int maxGuessTries) {
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

    private static String strategyFeedback(boolean solved, int binaryAttempts) {
        if (solved) {
            return String.format("\n" + "Your's strategy needed %s tries, while mine %s!", attempts - 1, binaryAttempts);
        } else {
            return "\nVery bad strategy, you're out of guess attempts!";
        }
    }

    int binarySearch(int randNr) {
        List<Integer> range = IntStream.rangeClosed(floor, ceiling)
                .boxed()
                .collect(Collectors.toList());
        int left = 0;
        int right = range.size() - 1;

        int binaryAttempts = 1;
        while (left <= right) {
            int currentPosition = left + (right - left) / 2;

            final Integer currentNumber = range.get(currentPosition);
            if (currentNumber == randNr) {
                return binaryAttempts;
            }
            if (currentNumber > randNr) {
                right = currentPosition - 1;
            } else if (currentNumber < randNr) {
                left = currentPosition + 1;
            }
            binaryAttempts += 1;
        }
        return binaryAttempts;
    }

    private static boolean isGuessed(int yourGuess, int randomNumber) {
        return (yourGuess == randomNumber);
    }

    private boolean numberIsInRange(int number) {
        return floor <= number && number <= ceiling;
    }

    @Override
    public String toString() {
        return String.format("<%d, %d>", floor, ceiling);
    }

    void getNumbersAndEvaluateGame(int randomNumber, Scanner sc, NumberGuessingGame numberGuessingGame) {
        int yourGuess;
        boolean limit;
        do {
            System.out.print("Type your guess nr " + attempts + ": ");

            try {
                yourGuess = sc.nextInt();
            } catch (InputMismatchException e) {
                log.info("--- Provided input is in wrong format or not an integer! Exit.. ---");
                return;
            }

            System.out.println(guessCheck(yourGuess, randomNumber));
            limit = isInLimit(numberGuessingGame.maxGuessTries);
        }
        while (!isGuessed(yourGuess, randomNumber) && limit);

        int binaryAttempts = binarySearch(randomNumber);
        System.out.println(strategyFeedback(isGuessed(yourGuess, randomNumber), binaryAttempts));
    }

    void gameTypeSelection(NumberGuessingGame numberGuessingGame) {
        String yourInput;
        yourInput = numberGuessingGame.sc.next();

        if (yourInput.equals("p")) {
            numberGuessingGame.play();
        } else if (yourInput.equals("a")) {
            System.out.println("Provide floor: ");
            try {
                floor = numberGuessingGame.sc.nextInt();
                System.out.println("Provide ceiling: ");
                ceiling = numberGuessingGame.sc.nextInt();
            } catch (InputMismatchException e) {
                log.info("--- Provided input is in wrong format or not an Integer! Exit.. ---");
                return;
            }
            new NumberGuessingGame(floor, ceiling, numberGuessingGame.maxGuessTries).play();
        } else log.info("--- Wrong data input or invalid option. Exit.. ---");
    }
}
