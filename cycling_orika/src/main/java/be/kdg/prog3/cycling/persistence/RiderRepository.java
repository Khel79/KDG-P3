package be.kdg.prog3.cycling.persistence;

import be.kdg.prog3.cycling.model.Rider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RiderRepository extends CrudRepository<Rider, Long> {
}
