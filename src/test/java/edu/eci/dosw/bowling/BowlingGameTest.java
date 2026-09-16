package src.test.java.edu.eci.dosw.bowling;

import org.junit.jupiter.api.Test;
import src.main.java.edu.eci.dosw.bowling.BowlingGame;
import src.main.java.edu.eci.dosw.bowling.Frame;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
