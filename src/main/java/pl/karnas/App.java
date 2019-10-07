package pl.karnas;

/**
 * Game where user is trying to guess random number from the given range.
 *
 * @author pi0trk
 */
class App {
    public static void main(String[] args) {
        new NumberGuessingGame(2, 6, 3).play();
    }
}
