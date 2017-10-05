package be.kdg.prog3.cycling.persistence;

import be.kdg.prog3.cycling.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RiderRepository extends JpaRepository<Rider, Long> {
}
