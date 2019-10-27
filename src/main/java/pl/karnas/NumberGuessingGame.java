package pl.karnas;

import java.util.Scanner;

class NumberGuessingGame {

    private final Engine engine;
    final int maxGuessTries;

    NumberGuessingGame(int floor, int ceiling, int maxGuessTries) {
        this.engine = new Engine(floor, ceiling);
        this.maxGuessTries = maxGuessTries;
    }

    final Scanner sc = new Scanner(System.in);

    void selectGame() {
        System.out.println("Type \"p\"(play) to guess number from the range " + engine.toString() + " or \"a\"(again) to start again with different range: ");

        engine.gameTypeSelection(this);
    }

    void play() {
        final int randomNumber = engine.drawRandomNumber();
        System.out.println("\nWelcome to Number Guessing Game!");
        System.out.println("\nRemember, you have just " + maxGuessTries + " guessing attempts.");
        System.out.println("\nPlease try guess number from the range " + engine.toString());

        engine.getNumbersAndEvaluateGame(randomNumber, sc, this);
    }

}
