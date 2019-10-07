package pl.karnas;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import static pl.karnas.Hints.NUMBER_NOT_IN_RANGE;
import static pl.karnas.Hints.NUMBER_TOO_HIGH;
import static pl.karnas.Hints.NUMBER_TOO_LOW;
import static pl.karnas.Hints.PERFECT_GUESS;

class NumberGuessingGame {

    private final Range range;
    private final int maxGuessTries;
    private final Logger log = Logger.getLogger(NumberGuessingGame.class.getName());

    public NumberGuessingGame(int floor, int ceiling, int maxGuessTries) {
        this.range = new Range(floor, ceiling);
        this.maxGuessTries = maxGuessTries;
    }

    void play() {
        final int randomNumber = range.drawRandomNumber();
        int attempts = 1;
        int yourGuess;

        System.out.println("Welcome to Number Guessing Game! Please try guess number from the range " + range.toString()
                + "\nRemember, you have just " + maxGuessTries + " guessing attempts.\n");


        //TODO: delete this below hint!
        System.out.println("Secret number is: " + randomNumber + "\n");

        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Type your guess nr " + attempts + ": ");
            attempts += 1;

            try {
                yourGuess = sc.nextInt();
            } catch (InputMismatchException e) {
                log.info("--- Provided input is in wrong format or not an integer! Exit.. ---");
                return;
            }

            if (yourGuess == randomNumber) {
                System.out.println(PERFECT_GUESS);
            } else if (!range.guessedNumberIsInRange(yourGuess)) {
                System.out.println(NUMBER_NOT_IN_RANGE);
            } else if (yourGuess < randomNumber) {
                System.out.println(NUMBER_TOO_LOW);
            } else {
                System.out.println(NUMBER_TOO_HIGH);
            }
        }
        while (yourGuess != randomNumber && attempts <= maxGuessTries);

    }
}
