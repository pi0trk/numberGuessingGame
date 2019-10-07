package pl.karnas;

import java.util.Random;

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
        return new Random().nextInt(ceiling - floor+1) + floor;
    }

    boolean guessedNumberIsInRange(int number) {
        return floor <= number && number <= ceiling;
    }

    @Override
    public String toString() {
        return String.format("<%d, %d>", floor, ceiling);
    }
}
