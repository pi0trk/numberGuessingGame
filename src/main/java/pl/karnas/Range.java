package pl.karnas;

import java.util.Random;

public class Range {

    private int floor;
    private int ceiling;


    public Range() {
        floor = 0;
        ceiling = 10;
    }

    public Range(int floor, int ceiling) throws IllegalAccessException {
        if (floor > ceiling) {
            throw new IllegalAccessException("Flor is above the ceiling only in fairy tails!");
        }
        this.floor = floor;
        this.ceiling = ceiling;
    }

    public int drawRandomNumber() {
        int randomNumber = new Random().nextInt(ceiling + 1) + floor;
        return randomNumber;
    }

    boolean guessedNumberIsInRange(int number) {
        return floor <= number && number <= ceiling;
    }

    @Override
    public String toString() {
        return "<" + floor + ", " + ceiling + '>';
    }
}
