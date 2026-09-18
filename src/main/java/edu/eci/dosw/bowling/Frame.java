package src.main.java.edu.eci.dosw.bowling;

import java.util.ArrayList;
import java.util.List;

public class Frame {

    private final List<Integer> rolls;
    private FrameType type;

    public Frame() {
        this.rolls = new ArrayList<>();
        this.type = FrameType.NORMAL;
    }

    public void addRoll(int pins) {
        rolls.add(pins);
    }

    public List<Integer> getRolls() {
        return List.copyOf(rolls);
    }

    public FrameType getType() {
        return type;
    }

    public void setType(FrameType type) {
        this.type = type;
    }
}
