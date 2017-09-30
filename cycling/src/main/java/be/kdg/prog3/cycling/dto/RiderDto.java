package be.kdg.prog3.cycling.dto;

import java.time.LocalDate;

public class RiderDto {
    private long id;
    private String name;
    private LocalDate dateOfBirth;
    private String teamUciCode;
    private String teamName;
    private int stageWins;

    public RiderDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getTeamUciCode() {
        return teamUciCode;
    }

    public void setTeamUciCode(String teamUciCode) {
        this.teamUciCode = teamUciCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getStageWins() {
        return stageWins;
    }

    public void setStageWins(int stageWins) {
        this.stageWins = stageWins;
    }
}
