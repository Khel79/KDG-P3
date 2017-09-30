package be.kdg.prog3.cycling.persistence;

import be.kdg.prog3.cycling.model.StageResult;
import org.springframework.data.repository.CrudRepository;

public interface StageResultRepository extends CrudRepository<StageResult, Long> {
}
