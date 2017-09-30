package be.kdg.prog3.cycling.model;

import java.util.ArrayList;
import java.util.List;

public class Stage {
    private long id;
    private int sequence;
    private Tour tour;
    private List<StageResult> stageResults;

    protected Stage() {
    }

    public Stage(long id, int sequence, Tour tour) {
        this.id = id;
        this.sequence = sequence;
        this.tour = tour;
        this.stageResults = new ArrayList<>();
    }

    public int getSequence() {
        return sequence;
    }

    public Tour getTour() {
        return tour;
    }

    public List<StageResult> getStageResults() {
        return stageResults;
    }
}
