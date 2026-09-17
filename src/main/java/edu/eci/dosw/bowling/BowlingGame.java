package src.main.java.edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

/**
 * Motor de un juego de Bowling para un jugador.
 * Un juego tiene exactamente 10 frames.
 */
public class BowlingGame {

    private final List<Frame> frames;
    private int currentFrame;

    public BowlingGame() {
        this.frames = new ArrayList<>();
        this.currentFrame = 0;
    }

    /**
     * Registra pinos derribados.
     * Lanza IllegalArgumentException si pins < 0 o > 10.
     * Lanza IllegalStateException si el juego ya termino.
     */
    public void roll(int pins) {
        validatePins(pins);
        validateGameInProgress();
        if (currentFrame == frames.size()) {
            frames.add(new Frame());
        }
        validateFramePins(pins);
        Frame frame = frames.get(currentFrame);
        frame.addRoll(pins);
        if (frame.getRolls().size() == 2) {
            currentFrame++;
        }
    }

    private void validatePins(int pins) {
        if (pins < 0 || pins > 10) {
            throw new IllegalArgumentException();
        }
    }

    private void validateFramePins(int pins) {
        List<Integer> rolls = frames.get(currentFrame).getRolls();
        if (rolls.size() == 1 && rolls.getFirst() != 10 && rolls.getFirst() + pins > 10) {
            throw new IllegalArgumentException();
        }
    }

    private void validateGameInProgress() {
        if (currentFrame == 10) {
            throw new IllegalStateException();
        }
    }

    /**
     * Puntaje total.
     * Lanza IllegalStateException si el juego no esta completo.
     */
    public int score() {
        throw new UnsupportedOperationException();
    }

    /**
     * true cuando los 10 frames han sido completados.
     */
    public boolean isComplete() {
        throw new UnsupportedOperationException();
    }

    public List<Frame> getFrames() {
        return List.copyOf(frames);
    }
}
