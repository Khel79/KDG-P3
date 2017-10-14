package be.kdg.prog3.upvote.persistence;

import be.kdg.prog3.upvote.model.QuestionAnswer;
import be.kdg.prog3.upvote.model.Vote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface VoteRepository extends CrudRepository<Vote, Long> {
    Vote findByQuestionAnswerIdAndUserId(long qaId, long userId);
    List<Vote> findByQuestionAnswerInAndUserId(List<QuestionAnswer> qaIds, long userId);
    Vote findByQuestionAnswerAndUserId(QuestionAnswer qa, long userId);
}
