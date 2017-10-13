package be.kdg.prog3.upvote.controllers;

import be.kdg.prog3.upvote.model.QuestionAnswer;
import be.kdg.prog3.upvote.model.Vote;
import be.kdg.prog3.upvote.persistence.QuestionAnswerRepository;
import be.kdg.prog3.upvote.persistence.VoteRepository;
import be.kdg.prog3.upvote.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private QuestionAnswerRepository questionAnswerRepository;
    private VoteRepository voteRepository;

    @Autowired
    public HomeController(QuestionAnswerRepository questionAnswerRepository, VoteRepository voteRepository) {
        this.questionAnswerRepository = questionAnswerRepository;
        this.voteRepository = voteRepository;
    }

    @GetMapping("/")
    public ModelAndView showDefaultHomepage(@AuthenticationPrincipal CustomUserDetails userDetails) {
        final List<QuestionAnswer> homepageQuestions = questionAnswerRepository.findTop10ByParentIsNullOrderByTimestampDesc();
        final List<Vote> votes;
        if (userDetails != null) {
            votes = voteRepository.findByQuestionAnswerInAndUserId(homepageQuestions, userDetails.getUserId());
        }
        else {
            votes = new ArrayList<>();
        }

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.getModel().put("questions", homepageQuestions);
        modelAndView.getModel().put("votes", votes);
        return modelAndView;
    }
}
