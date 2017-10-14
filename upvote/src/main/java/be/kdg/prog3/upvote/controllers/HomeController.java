package be.kdg.prog3.upvote.controllers;

import be.kdg.prog3.upvote.dto.DtoMapper;
import be.kdg.prog3.upvote.dto.QuestionAnswerDto;
import be.kdg.prog3.upvote.model.QuestionAnswer;
import be.kdg.prog3.upvote.model.Vote;
import be.kdg.prog3.upvote.security.CustomUserDetails;
import be.kdg.prog3.upvote.services.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    private final QuestionAnswerService questionAnswerService;

    private final DtoMapper dtoMapper;

    @Autowired
    public HomeController(QuestionAnswerService questionAnswerService, DtoMapper dtoMapper) {
        this.questionAnswerService = questionAnswerService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/")
    public ModelAndView showDefaultHomepage(@AuthenticationPrincipal CustomUserDetails userDetails) {
        final Map<QuestionAnswer, Vote> questionsWithVotes = this.questionAnswerService.getTopTenQuestionsWithUserVotes(userDetails);

        final List<QuestionAnswerDto> homepageQuestions = dtoMapper.toDto(questionsWithVotes);

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.getModel().put("questions", homepageQuestions);
        return modelAndView;
    }
}
