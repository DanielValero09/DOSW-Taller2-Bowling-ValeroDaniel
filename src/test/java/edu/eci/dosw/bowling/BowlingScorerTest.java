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

    @Test
    @DisplayName("B4: a strike should add the next two rolls as a bonus")
    void strikeShouldAddNextTwoRollsAsBonus() {
        BowlingGame game = new BowlingGame();

        game.roll(10);

        game.roll(4);
        game.roll(3);

        for (int i = 0; i < 16; i++) {
            game.roll(0);
        }

        assertEquals(24, game.score());
    }

    @Test
    @DisplayName("B5: consecutive strikes should correctly calculate the first strike bonus")
    void consecutiveStrikesShouldCorrectlyCalculateFirstStrikeBonus() {
        BowlingGame game = new BowlingGame();

        game.roll(10);
        game.roll(10);

        game.roll(5);
        game.roll(0);

        for (int i = 0; i < 14; i++) {
            game.roll(0);
        }

        assertEquals(45, game.score());
    }

    @Test
    @DisplayName("B6: ten spares with a final bonus roll of five should score 150")
    void tenSparesWithFinalBonusRollOfFiveShouldScore150() {
        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 10; i++) {
            game.roll(5);
            game.roll(5);
        }

        game.roll(5);

        assertEquals(150, game.score());
    }

    @Test
    @DisplayName("B7: a perfect game should score 300")
    void perfectGameShouldScore300() {
        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 12; i++) {
            game.roll(10);
        }

        assertEquals(300, game.score());
    }
}
