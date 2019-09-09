package pl.karnas;

import java.util.Random;

class Range {

    //floor inclusive
    private int floor;
    //ceiling exclusive
    private int ceiling;


    Range() {
        floor = 0;
        ceiling = 10;
    }

    Range(int floor, int ceiling) throws IllegalAccessException {
        if (floor > ceiling) {
            throw new IllegalAccessException("\n\n--- Flor is above the ceiling only in fairy tails! ---\n");
        }
        this.floor = floor;
        this.ceiling = ceiling;
    }

    int drawRandomNumber() {
        if (ceiling == floor) return floor;
        return new Random().nextInt(ceiling - floor) + floor;
    }

    boolean guessedNumberIsInRange(int number) {
        return floor <= number && number <= ceiling;
    }

    @Override
    public String toString() {
        return "<" + floor + ", " + ceiling + '>';
    }
}
