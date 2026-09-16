package src.test.java.edu.eci.dosw.bowling;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import src.main.java.edu.eci.dosw.bowling.BowlingGame;
import src.main.java.edu.eci.dosw.bowling.Frame;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingGameTest {

    @Test
    void rollZeroAsFirstThrowShouldRegisterZeroPinsInFrame() {
        // Arrange
        BowlingGame game = new BowlingGame();

        // Act
        assertDoesNotThrow(() -> game.roll(0));

        // Assert
        assertEquals(1, game.getFrames().size());
        Frame frame = game.getFrames().getFirst();
        assertEquals(1, frame.getRolls().size());
        assertEquals(0, frame.getRolls().getFirst());
    }

    @Test
    @DisplayName("A2: roll(-1) should throw IllegalArgumentException")
    void rollNegativeOneShouldThrowIllegalArgumentException() {
        BowlingGame game = new BowlingGame();

        assertThrows(IllegalArgumentException.class, () -> game.roll(-1));
    }

    @Test
    @DisplayName("A3: roll(11) should throw IllegalArgumentException")
    void rollElevenShouldThrowIllegalArgumentException() {
        BowlingGame game = new BowlingGame();

        assertThrows(IllegalArgumentException.class, () -> game.roll(11));
    }

    @Test
    @DisplayName("A4: second roll should throw IllegalArgumentException when frame sum exceeds 10")
    void secondRollShouldThrowIllegalArgumentExceptionWhenFrameSumExceedsTen() {
        BowlingGame game = new BowlingGame();

        game.roll(7);

        assertThrows(IllegalArgumentException.class, () -> game.roll(6));
    }

    @Test
    @DisplayName("A5: roll() should throw IllegalStateException when game is already complete")
    void rollShouldThrowIllegalStateExceptionWhenGameIsAlreadyComplete() {
        BowlingGame game = new BowlingGame();

        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }

        assertThrows(IllegalStateException.class, () -> game.roll(0));
    }
}
