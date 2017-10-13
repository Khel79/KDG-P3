package be.kdg.prog3.upvote.controllers;

import be.kdg.prog3.upvote.model.QuestionAnswer;
import be.kdg.prog3.upvote.model.Vote;
import be.kdg.prog3.upvote.security.CustomUserDetails;
import be.kdg.prog3.upvote.services.QuestionAnswerService;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionAnswerController {
    private QuestionAnswerService questionAnswerService;

    @Autowired
    public QuestionAnswerController(QuestionAnswerService questionAnswerService) {
        this.questionAnswerService = questionAnswerService;
    }

    @GetMapping("/q/{questionId}")
    public ModelAndView showQuestion(@PathVariable long questionId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // TODO: refactor... there's too much business logic here...
        QuestionAnswer question = this.questionAnswerService.getQuestion(questionId);
        if (question != null) {
            final List<QuestionAnswer> answers = this.questionAnswerService.getQuestionAnswers(question);
            final List<Vote> votes;
            if (userDetails != null) {
                answers.add(question);
                votes = this.questionAnswerService.getVotesByUser(answers, userDetails.getUserId());
            }
            else {
                votes = new ArrayList<>();
            }

            final ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("show_question");
            modelAndView.getModel().put("question", question);
            modelAndView.getModel().put("answers", answers);
            modelAndView.getModel().put("votes", votes);
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
        final long questionId = this.questionAnswerService.saveAnswer(userDetails.getUserId(), body, parentId);
        return "redirect:/q/" + questionId;
    }
}
