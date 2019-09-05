package pl.karnas;

import java.util.InputMismatchException;
import java.util.Scanner;

import static pl.karnas.Hints.NUMBER_NOT_IN_RANGE;
import static pl.karnas.Hints.NUMBER_TOO_HIGH;
import static pl.karnas.Hints.NUMBER_TOO_LOW;
import static pl.karnas.Hints.PERFECT_GUESS;

/**
 * Game where user is trying to guess random number from the given range.
 *
 * @author pi0trk
 */
public class NumberGuessingGame {
    public static void main(String[] args) throws IllegalAccessException {

        Range range;
        range = new Range(2,6);
        final int randomNumber = range.drawRandomNumber();
        final int MAX_GUESS_TRIES = 4;
        int attempts = 0;
        int yourGuess = -1;

        Scanner sc = new Scanner(System.in);


        System.out.println("Welcome to Number Guessing Game! Please try guess number from the range " + range.toString()
                + "\nRemember, you have just " + MAX_GUESS_TRIES + " guessing attempts.\n");


        //TODO: delete this below hint!
        System.out.println("Secret number is: " + randomNumber);

        do {
            System.out.print("Type your guess nr " + (attempts += 1) + ": ");

            try {
                yourGuess = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("--- Provided input is in wrong format or not a integer! Exit.. ---");
                return;
            }

            if (yourGuess == randomNumber) {
                System.out.println(PERFECT_GUESS);
            } else if (!range.guessedNumberIsInRange(yourGuess)) {
                System.out.println(NUMBER_NOT_IN_RANGE);
            } else if (yourGuess < randomNumber) {
                System.out.println(NUMBER_TOO_LOW);
            } else if (yourGuess > randomNumber) {
                System.out.println(NUMBER_TOO_HIGH);
            }

        } while (yourGuess != randomNumber && attempts < MAX_GUESS_TRIES);
    }
}
