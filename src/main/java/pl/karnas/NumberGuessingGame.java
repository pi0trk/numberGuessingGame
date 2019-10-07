package pl.karnas;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Logger;

import static pl.karnas.Engine.attempts;

class NumberGuessingGame {

    private final Engine engine;
    private final int maxGuessTries;
    private final Logger log = Logger.getLogger(NumberGuessingGame.class.getName());

    NumberGuessingGame(int floor, int ceiling, int maxGuessTries) {
        this.engine = new Engine(floor, ceiling);
        this.maxGuessTries = maxGuessTries;
    }

    void play() {
        final int randomNumber = engine.drawRandomNumber();
        int yourGuess;
        boolean limit;

        System.out.println("Welcome to Number Guessing Game! Please try guess number from the engine " + engine.toString()
                + "\nRemember, you have just " + maxGuessTries + " guessing attempts.\n");

        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Type your guess nr " + attempts + ": ");

            try {
                yourGuess = sc.nextInt();
            } catch (InputMismatchException e) {
                log.info("--- Provided input is in wrong format or not an integer! Exit.. ---");
                return;
            }

            System.out.println(engine.guessCheck(yourGuess, randomNumber));
            limit = Engine.checkGuessLimit(maxGuessTries);
        }
        while (!engine.isGuessed(yourGuess, randomNumber) && limit);
    }

}
