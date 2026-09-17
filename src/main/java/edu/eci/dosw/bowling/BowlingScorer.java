package src.main.java.edu.eci.dosw.bowling;

import java.util.List;

public class BowlingScorer {

    public int calculate(List<Frame> frames) {
        int score = 0;
        for (Frame frame : frames) {
            for (int pins : frame.getRolls()) {
                score += pins;
            }
        }
        return score;
    }
}
