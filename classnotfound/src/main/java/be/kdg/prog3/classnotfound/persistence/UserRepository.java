package be.kdg.prog3.classnotfound.persistence;

import be.kdg.prog3.classnotfound.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
