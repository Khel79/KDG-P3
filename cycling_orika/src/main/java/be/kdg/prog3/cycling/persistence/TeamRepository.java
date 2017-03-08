package be.kdg.prog3.cycling.persistence;

import be.kdg.prog3.cycling.model.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByUciCode(String uciCode);
}
