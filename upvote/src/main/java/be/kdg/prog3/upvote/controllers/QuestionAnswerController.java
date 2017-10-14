package be.kdg.prog3.upvote.controllers;

import be.kdg.prog3.upvote.dto.DtoMapper;
import be.kdg.prog3.upvote.dto.QuestionAnswerDto;
import be.kdg.prog3.upvote.model.QuestionAnswer;
import be.kdg.prog3.upvote.model.Vote;
import be.kdg.prog3.upvote.security.CustomUserDetails;
import be.kdg.prog3.upvote.services.QuestionAnswerService;
import be.kdg.prog3.upvote.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class QuestionAnswerController {
    private final QuestionAnswerService questionAnswerService;
    private final VoteService voteService;

    private final DtoMapper dtoMapper;

    @Autowired
    public QuestionAnswerController(QuestionAnswerService questionAnswerService, VoteService voteService, DtoMapper dtoMapper) {
        this.questionAnswerService = questionAnswerService;
        this.voteService = voteService;
        this.dtoMapper = dtoMapper;
    }

    @GetMapping("/q/{questionId}")
    public ModelAndView showQuestion(@PathVariable long questionId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        QuestionAnswer question = this.questionAnswerService.getQuestion(questionId);
        if (question != null) {
            final Vote vote  = this.voteService.getVoteByUser(question, userDetails);
            final Map<QuestionAnswer, Vote> answersWithVotes = this.questionAnswerService.getAnswersWithUserVotes(question, userDetails);

            final QuestionAnswerDto questionDto = dtoMapper.toDto(question, vote);
            final List<QuestionAnswerDto> answerDtos = dtoMapper.toDto(answersWithVotes);

            final ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("show_question");
            modelAndView.getModel().put("question", questionDto);
            modelAndView.getModel().put("answers", answerDtos);
            return modelAndView;
        }
        else {
            // This would be a good way to handle this:
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "QuestionAnswer with ID '" + questionId + "' not found.");

            // Alternatively, let this exceptions be picked up by AppWideExceptionHandler:
            //throw new QuestionNotFoundException("QuestionAnswer with ID '" + questionId + "' not found.");
        }
    }

    @GetMapping("/q/new")
    public String newQuestion() {
        return "new_question";
    }

    @GetMapping("/q/search")
    public String searchQuestions() {
        return "search_questions";
    }

    @PostMapping("/q")
    public String addQuestion(@RequestParam String subject, @RequestParam String body,
                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        long questionId = this.questionAnswerService.saveQuestion(userDetails.getUserId(), subject, body);
        return "redirect:/q/" + questionId;
    }

    @PostMapping("/a")
    public String addAnswer(@RequestParam String body, @RequestParam long parentId,
                              @AuthenticationPrincipal CustomUserDetails userDetails) {
        this.questionAnswerService.saveAnswer(userDetails.getUserId(), body, parentId);
        return "redirect:/q/" + parentId;
    }
}
