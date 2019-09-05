package pl.karnas;

enum Hints {

    NUMBER_NOT_IN_RANGE("Number not in given range!"),
    NUMBER_TOO_HIGH("Number is too high!"),
    NUMBER_TOO_LOW("Number is too low!"),
    PERFECT_GUESS("Bravo! You've guessed secret number!");

    private final String description;

    Hints(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return description;
    }

}
