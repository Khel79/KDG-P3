package be.kdg.prog3.cycling.persistence;

import be.kdg.prog3.cycling.model.Stage;
import org.springframework.data.repository.CrudRepository;

public interface StageRepository extends CrudRepository<Stage, Long> {
}
