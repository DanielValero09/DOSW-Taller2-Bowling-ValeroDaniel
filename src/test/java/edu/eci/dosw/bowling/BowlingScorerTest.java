package src.test.java.edu.eci.dosw.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.main.java.edu.eci.dosw.bowling.BowlingGame;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingScorerTest {

    @Test
    @DisplayName("B1: a gutter game should score zero")
    void gutterGameShouldScoreZero() {
        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }

        assertEquals(0, game.score());
    }

    @Test
    @DisplayName("B2: a game without strikes or spares should score the sum of pins")
    void gameWithoutStrikesOrSparesShouldScoreSumOfPins() {
        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }

        assertEquals(20, game.score());
    }

    @Test
    @DisplayName("B3: a spare should add the first roll of the next frame as a bonus")
    void spareShouldAddFirstRollOfNextFrameAsBonus() {
        BowlingGame game = new BowlingGame();

        game.roll(5);
        game.roll(5);

        game.roll(3);
        game.roll(0);

        for (int i = 0; i < 16; i++) {
            game.roll(0);
        }

        assertEquals(16, game.score());
    }
}
