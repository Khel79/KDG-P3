package be.kdg.prog3.cycling.model;

import java.util.ArrayList;
import java.util.List;

public class Tour {
    private long id;
    private int year;
    private List<Stage> stages;

    protected Tour() {
    }

    public Tour(long id, int year) {
        this.id = id;
        this.year = year;
        this.stages = new ArrayList<>();
    }

    public int getYear() {
        return year;
    }

    public List<Stage> getStages() {
        return stages;
    }
}
