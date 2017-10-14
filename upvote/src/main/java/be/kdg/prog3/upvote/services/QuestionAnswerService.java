package be.kdg.prog3.upvote.services;

import be.kdg.prog3.upvote.model.QuestionAnswer;
import be.kdg.prog3.upvote.model.User;
import be.kdg.prog3.upvote.persistence.QuestionAnswerRepository;
import be.kdg.prog3.upvote.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionAnswerService {
    private final QuestionAnswerRepository questionAnswerRepository;
    private final UserRepository userRepository;

    @Autowired
    public QuestionAnswerService(QuestionAnswerRepository questionAnswerRepository, UserRepository userRepository) {
        this.questionAnswerRepository = questionAnswerRepository;
        this.userRepository = userRepository;
    }

    public QuestionAnswer getQuestion(long questionId) {
        return questionAnswerRepository.findOne(questionId);
    }

    public List<QuestionAnswer> getQuestionAnswers(QuestionAnswer question) {
        return questionAnswerRepository.findAnswersByParentOrderByTimestampAsc(question);
    }

    public List<QuestionAnswer> getTopTenQuestions() {
        return questionAnswerRepository.findTop10ByParentIsNullOrderByTimestampDesc();
    }

    public long saveQuestion(long userId, String subject, String body) {
        final User user = this.userRepository.findOne(userId);
        QuestionAnswer questionAnswer = new QuestionAnswer(subject, body, user);
        questionAnswer = questionAnswerRepository.save(questionAnswer);
        return questionAnswer.getId();
    }

    public long saveAnswer(long userId, String body, long parentId) {
        final User user = this.userRepository.findOne(userId);
        final QuestionAnswer parent = questionAnswerRepository.findOne(parentId);
        final QuestionAnswer questionAnswer = new QuestionAnswer(body, user, parent);
        questionAnswerRepository.save(questionAnswer);
        return parent.getId();
    }
}
