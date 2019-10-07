package pl.karnas;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

public class NumberGuessingGameTest {

    @Rule
    public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    private NumberGuessingGame game;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionIfFlorHigherThanCeiling() {
        game = new NumberGuessingGame(7, 5, 10);
    }

    @Test
    public void afterMaxAttemptsGameShouldEnd() {
        systemInMock.provideLines("5");
        game = new NumberGuessingGame(7, 7, 1);
        game.play();
        assertThat(systemOutRule.getLog()).containsOnlyOnce("Number not in given range!");
    }

    @Test
    public void drawNumberShouldBeBetweenGivenValuesWithFloorInclusiveAndCeilingExclusive() {
        systemInMock.provideLines("6");
        game = new NumberGuessingGame(5, 8, 1);
        game.play();
        assertThat(systemOutRule.getLog()).containsOnlyOnce("Please try guess number from the range <5, 8>");
    }

    @Test
    public void shouldProvideSuccessMessageIfNumberFound() {
        systemInMock.provideLines("6", "7");
        game = new NumberGuessingGame(6, 8, 2);
        game.play();
        assertThat(systemOutRule.getLog()).containsOnlyOnce("Bravo! You've guessed secret number!");
    }

}