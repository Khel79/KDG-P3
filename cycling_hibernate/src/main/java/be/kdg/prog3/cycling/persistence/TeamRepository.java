package be.kdg.prog3.cycling.persistence;

import be.kdg.prog3.cycling.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Team findByUciCode(String uciCode);
}
