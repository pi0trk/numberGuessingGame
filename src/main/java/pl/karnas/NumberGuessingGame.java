package pl.karnas;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

class NumberGuessingGame {

    private final Range range;
    private final int maxGuessTries;
    private final Logger log = Logger.getLogger(NumberGuessingGame.class.getName());

    NumberGuessingGame(int floor, int ceiling, int maxGuessTries) {
        this.range = new Range(floor, ceiling);
        this.maxGuessTries = maxGuessTries;
    }

    void play() {
        final int randomNumber = range.drawRandomNumber();
        int attempts = 1;
        int yourGuess;

        System.out.println("Welcome to Number Guessing Game! Please try guess number from the range " + range.toString()
                + "\nRemember, you have just " + maxGuessTries + " guessing attempts.\n");

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

            range.guessCheck(yourGuess, randomNumber);
        }
        while (!range.isGuessed(yourGuess, randomNumber) && attempts <= maxGuessTries);

    }

}
