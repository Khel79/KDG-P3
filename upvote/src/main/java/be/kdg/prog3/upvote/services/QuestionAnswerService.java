package be.kdg.prog3.upvote.services;

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

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QuestionAnswerService {
    private final QuestionAnswerRepository questionAnswerRepository;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public QuestionAnswerService(QuestionAnswerRepository questionAnswerRepository, UserRepository userRepository, VoteRepository voteRepository) {
        this.questionAnswerRepository = questionAnswerRepository;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
    }

    public QuestionAnswer getQuestion(long questionId) {
        return questionAnswerRepository.findOne(questionId);
    }

    public long saveQuestion(long userId, String subject, String body) {
        final User user = this.userRepository.findOne(userId);
        QuestionAnswer questionAnswer = new QuestionAnswer(subject, body, user);
        questionAnswer = questionAnswerRepository.save(questionAnswer);
        return questionAnswer.getId();
    }

    public void saveAnswer(long userId, String body, long parentId) {
        final User user = this.userRepository.findOne(userId);
        final QuestionAnswer parent = questionAnswerRepository.findOne(parentId);
        final QuestionAnswer questionAnswer = new QuestionAnswer(body, user, parent);
        questionAnswerRepository.save(questionAnswer);
    }

    public Map<QuestionAnswer, Vote> getAnswersWithUserVotes(QuestionAnswer question, CustomUserDetails userDetails) {
        final List<QuestionAnswer> answers = this.questionAnswerRepository.findAnswersByParentOrderByTimestampAsc(question);
        return enrichQAsWithUserVotes(answers, userDetails);
    }

    public Map<QuestionAnswer, Vote> getTopTenQuestionsWithUserVotes(CustomUserDetails userDetails) {
        final List<QuestionAnswer> topTenQuestions = this.questionAnswerRepository.findTop10ByParentIsNullOrderByTimestampDesc();
        return enrichQAsWithUserVotes(topTenQuestions, userDetails);
    }

    private Map<QuestionAnswer, Vote> enrichQAsWithUserVotes(List<QuestionAnswer> qas, CustomUserDetails userDetails) {
        final Map<QuestionAnswer, Vote> votesByQA = new LinkedHashMap<>();

        for (QuestionAnswer qa : qas) {
            votesByQA.put(qa, null);
        }
        if (userDetails != null) {
            final List<Vote> votes = this.voteRepository.findByQuestionAnswerInAndUserId(qas, userDetails.getUserId());
            for (Vote vote : votes) {
                votesByQA.put(vote.getQuestionAnswer(), vote);
            }
        }

        return votesByQA;
    }
}
