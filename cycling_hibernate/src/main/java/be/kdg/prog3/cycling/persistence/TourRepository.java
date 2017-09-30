package be.kdg.prog3.cycling.persistence;

import be.kdg.prog3.cycling.model.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour, Long> {
}
