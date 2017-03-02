package be.kdg.prog3.classnotfound.persistence;

import be.kdg.prog3.classnotfound.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface VoteRepository extends CrudRepository<Vote, Long> {
    Vote findByQuestionAnswerIdAndUserId(long qaId, long userId);
}
