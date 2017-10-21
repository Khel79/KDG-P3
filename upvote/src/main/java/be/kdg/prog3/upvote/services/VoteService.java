package be.kdg.prog3.upvote.services;

import be.kdg.prog3.upvote.exceptions.DuplicateVoteCastException;
import be.kdg.prog3.upvote.exceptions.QuestionNotFoundException;
import be.kdg.prog3.upvote.exceptions.VoteNotFoundException;
import be.kdg.prog3.upvote.model.QuestionAnswer;
import be.kdg.prog3.upvote.model.User;
import be.kdg.prog3.upvote.model.Vote;
import be.kdg.prog3.upvote.persistence.QuestionAnswerRepository;
import be.kdg.prog3.upvote.persistence.UserRepository;
import be.kdg.prog3.upvote.persistence.VoteRepository;
import be.kdg.prog3.upvote.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VoteService {
    private final VoteRepository voteRepository;
    private final QuestionAnswerRepository questionAnswerRepository;
    private final UserRepository userRepository;

    @Autowired
    public VoteService(VoteRepository voteRepository, QuestionAnswerRepository questionAnswerRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.questionAnswerRepository = questionAnswerRepository;
        this.userRepository = userRepository;
    }

    public void castVote(long qaId, boolean isUp, long userId) {
        final QuestionAnswer qa = this.questionAnswerRepository.findOne(qaId);
        if (qa != null) {
            Vote vote = this.voteRepository.findByQuestionAnswerIdAndUserId(qaId, userId);
            final int modifier;
            if (vote == null) {
                final User user = this.userRepository.findOne(userId);
                vote = new Vote(user, qa, isUp);
                modifier = 1;
            }
            else {
                if (vote.isUp() == isUp) {
                    throw new DuplicateVoteCastException("This vote was already cast.");
                }
                vote.setUp(isUp);
                modifier = 2;
            }

            this.voteRepository.save(vote);

            qa.setScore(qa.getScore() + (vote.isUp() ? 1 : -1) * modifier);
            this.questionAnswerRepository.save(qa);
        }
        else {
            throw new QuestionNotFoundException("Couldn't find this question.");
        }
    }

    public void deleteVote(long qaId, long userId) {
        final QuestionAnswer qa = this.questionAnswerRepository.findOne(qaId);
        final Vote existingVote = this.voteRepository.findByQuestionAnswerIdAndUserId(qaId, userId);

        if (existingVote != null) {
            this.voteRepository.delete(existingVote.getId());

            if (qa != null) {
                qa.setScore(qa.getScore() + (existingVote.isUp() ? -1 : 1));
                this.questionAnswerRepository.save(qa);
            }
            else {
                throw new QuestionNotFoundException("Couldn't find this question.");
            }
        }
        else {
            throw new VoteNotFoundException("Couldn't find this vote.");
        }
    }

    public Vote getVoteByUser(long qaId, CustomUserDetails userDetails) {
        final QuestionAnswer qa = this.questionAnswerRepository.findOne(qaId);
        if (qa != null && userDetails != null) {
            return voteRepository.findByQuestionAnswerAndUserId(qa, userDetails.getUserId());
        } else {
            return null;
        }
    }

    public Vote getVoteByUser(QuestionAnswer qa, CustomUserDetails userDetails) {
        if (userDetails != null) {
            return voteRepository.findByQuestionAnswerAndUserId(qa, userDetails.getUserId());
        } else {
            return null;
        }
    }
}
