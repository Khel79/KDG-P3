package be.kdg.prog3.cycling.services;

import be.kdg.prog3.cycling.exceptions.TeamException;
import be.kdg.prog3.cycling.model.Team;
import be.kdg.prog3.cycling.persistence.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamService {
    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findByUciCode(String uciCode) {
        final Team team = teamRepository.findByUciCode(uciCode);
        if (team == null) {
            throw new TeamException("Team not found (UCI code = " + uciCode + ")");
        }
        return team;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }
}
