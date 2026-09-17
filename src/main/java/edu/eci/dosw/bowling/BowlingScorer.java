package src.main.java.edu.eci.dosw.bowling;

import java.util.List;

public class BowlingScorer {

    public int calculate(List<Frame> frames) {
        int score = 0;
        for (int i = 0; i < frames.size(); i++) {
            Frame frame = frames.get(i);
            for (int pins : frame.getRolls()) {
                score += pins;
            }
            if (frame.getType() == FrameType.SPARE) {
                score += getSpareBonus(frames, i);
            }
            if (frame.getType() == FrameType.STRIKE) {
                score += getStrikeBonus(frames, i);
            }
        }
        return score;
    }

    private int getSpareBonus(List<Frame> frames, int frameIndex) {
        return frames.get(frameIndex + 1).getRolls().get(0);
    }

    private int getStrikeBonus(List<Frame> frames, int frameIndex) {
        List<Integer> nextRolls = frames.get(frameIndex + 1).getRolls();
        return nextRolls.get(0) + nextRolls.get(1);
    }
}
