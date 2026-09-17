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
}
