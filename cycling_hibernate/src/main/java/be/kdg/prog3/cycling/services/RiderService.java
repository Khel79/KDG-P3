package be.kdg.prog3.cycling.services;

import be.kdg.prog3.cycling.exceptions.RiderException;
import be.kdg.prog3.cycling.model.Rider;
import be.kdg.prog3.cycling.persistence.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RiderService {
    private final RiderRepository riderRepository;

    @Autowired
    public RiderService(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    public Rider findById(long riderId) {
        final Rider rider = riderRepository.findOne(riderId);
        if (rider == null) {
            throw new RiderException("Rider not found (ID = " + riderId + ")");
        }
        return rider;
    }

    public List<Rider> findAll() {
        return riderRepository.findAll();
    }
}
