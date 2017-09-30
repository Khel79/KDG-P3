package be.kdg.prog3.cycling.persistence;

import be.kdg.prog3.cycling.model.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeamRepository {
    private final List<Team> teams;

    public TeamRepository() {
        this.teams = new ArrayList<>();
        this.teams.add(new Team(1, "Lotto–Soudal", "LTS", 1985));
        this.teams.add(new Team(2, "Etixx–Quick-Step", "EQS", 2003));
    }

    public List<Team> findAll() {
        return teams;
    }

    public Team findByUciCode(String uciCode) {
        for (Team team : teams) {
            if (team.getUciCode().equals(uciCode)) {
                return team;
            }
        }
        return null;
    }
}
