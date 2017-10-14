package be.kdg.prog3.upvote.controllers;

import be.kdg.prog3.upvote.model.QuestionAnswer;
import be.kdg.prog3.upvote.model.Vote;
import be.kdg.prog3.upvote.security.CustomUserDetails;
import be.kdg.prog3.upvote.services.QuestionAnswerService;
import be.kdg.prog3.upvote.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    private final QuestionAnswerService questionAnswerService;
    private final VoteService voteService;

    @Autowired
    public HomeController(QuestionAnswerService questionAnswerService, VoteService voteService) {
        this.questionAnswerService = questionAnswerService;
        this.voteService = voteService;
    }

    @GetMapping("/")
    public ModelAndView showDefaultHomepage(@AuthenticationPrincipal CustomUserDetails userDetails) {
        final List<QuestionAnswer> homepageQuestions = this.questionAnswerService.getTopTenQuestions();
        final List<Vote> votes;
        if (userDetails != null) {
            votes = this.voteService.getVotesByUser(homepageQuestions, userDetails.getUserId());
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
