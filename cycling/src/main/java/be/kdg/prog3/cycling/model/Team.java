package be.kdg.prog3.cycling.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private long id;
    private String name;
    private String uciCode;
    private int founded;
    private List<Rider> riders;

    protected Team() {
    }

    public Team(long id, String name, String uciCode, int founded) {
        this.id = id;
        this.name = name;
        this.uciCode = uciCode;
        this.founded = founded;
        this.riders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUciCode() {
        return uciCode;
    }

    public int getFounded() {
        return founded;
    }

    public List<Rider> getRiders() {
        return riders;
    }
}
