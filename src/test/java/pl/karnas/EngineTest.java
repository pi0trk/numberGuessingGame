package pl.karnas;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class EngineTest {

    @Test
    public void drawnNumberShouldBeInRange() {
        int randomNr = new Engine(5, 6).drawRandomNumber();
        assertTrue(randomNr == 5 || randomNr == 6);
    }

    @Test
    public void guessCheckShouldPrintTooLowNumberMessage() {
        String hint = new Engine(2, 5).guessCheck(3, 5).toString();
        System.out.println(hint);
        assertEquals(hint, "Number is too low!");
    }

    @Test
    public void guessCheckShouldPrintTooHighNumberMessage() {
        String hint = new Engine(2, 5).guessCheck(3, 2).toString();
        System.out.println(hint);
        assertEquals(hint, "Number is too high!");
    }

    @Test
    public void guessCheckShouldPrintNumberNotInRangeMessage() {
        String hint = new Engine(2, 5).guessCheck(1, 2).toString();
        System.out.println(hint);
        assertEquals(hint, "Number not in given range!");
    }

    @Test
    public void guessCheckShouldPrintCorrectGuessMessage() {
        String hint = new Engine(2, 5).guessCheck(2, 2).toString();
        System.out.println(hint);
        assertEquals(hint, "Bravo! You've guessed secret number!");
    }

    @Test
    public void testBinarySearch() {
        int binarySearch = new Engine(2, 5).binarySearch(5);
        assertEquals(binarySearch, 3);
    }
}