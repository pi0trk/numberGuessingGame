package pl.karnas;

import java.util.Random;

import static pl.karnas.Hints.*;

class Range {

    private int floor;
    private int ceiling;

    Range() {
        floor = 0;
        ceiling = 10;
    }

    Range(int floor, int ceiling) {
        if (floor > ceiling) {
            throw new IllegalArgumentException("\n\n--- Flor is above the ceiling only in fairy tails! ---\n");
        }
        this.floor = floor;
        this.ceiling = ceiling;
    }

    int drawRandomNumber() {
        return new Random().nextInt(ceiling - floor + 1) + floor;
    }

    void guessCheck(int yourGuess, int randomNumber) {
        if (isGuessed(yourGuess, randomNumber)) {
            System.out.println(PERFECT_GUESS);
        } else if (!numberIsInRange(yourGuess)) {
            System.out.println(NUMBER_NOT_IN_RANGE);
        } else if (yourGuess < randomNumber) {
            System.out.println(NUMBER_TOO_LOW);
        } else {
            System.out.println(NUMBER_TOO_HIGH);
        }
    }

    boolean isGuessed(int yourGuess, int randomNumber) {
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
