package be.kdg.prog3.cycling.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Rider {
    private long id;
    private String name;
    private LocalDate dateOfBirth;
    private Team team;
    private List<StageResult> stageResults;

    protected Rider() {
    }

    public Rider(long id, String name, LocalDate dateOfBirth, Team team) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.team = team;
        this.stageResults = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Team getTeam() {
        return team;
    }

    public List<StageResult> getStageResults() {
        return stageResults;
    }
}
