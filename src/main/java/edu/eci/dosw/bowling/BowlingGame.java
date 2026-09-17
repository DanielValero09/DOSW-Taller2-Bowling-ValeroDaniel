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
        if (isStrike(pins)) {
            frame.setType(FrameType.STRIKE);
        } else if (isSpare(frame)) {
            frame.setType(FrameType.SPARE);
        }
        advanceFrameIfComplete();
    }

    private boolean isStrike(int pins) {
        return pins == 10;
    }

    private boolean isSpare(Frame frame) {
        List<Integer> rolls = frame.getRolls();
        return rolls.size() == 2 && rolls.getFirst() + rolls.get(1) == 10;
    }

    private void advanceFrameIfComplete() {
        Frame frame = frames.get(currentFrame);
        if (isTenthFrameWithBonus(frame)) {
            if (frame.getRolls().size() == 3) {
                currentFrame++;
            }
            return;
        }
        if (frame.getType() == FrameType.STRIKE || frame.getRolls().size() == 2) {
            currentFrame++;
        }
    }

    private boolean isTenthFrameWithBonus(Frame frame) {
        return isTenthFrame() && (frame.getRolls().getFirst() == 10 || frame.getType() == FrameType.SPARE);
    }

    private boolean isTenthFrame() {
        return currentFrame == 9;
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
        if (currentFrame != 10) {
            throw new IllegalStateException();
        }
        return new BowlingScorer().calculate(frames);
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
