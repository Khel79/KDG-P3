package be.kdg.prog3.classnotfound.controller;

import be.kdg.prog3.classnotfound.model.QuestionAnswer;
import be.kdg.prog3.classnotfound.model.Vote;
import be.kdg.prog3.classnotfound.persistence.QuestionAnswerRepository;
import be.kdg.prog3.classnotfound.persistence.VoteRepository;
import be.kdg.prog3.classnotfound.security.UserDetails;
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
    private QuestionAnswerRepository questionAnswerRepository;
    private VoteRepository voteRepository;

    @Autowired
    public QuestionAnswerController(QuestionAnswerRepository questionAnswerRepository, VoteRepository voteRepository) {
        this.questionAnswerRepository = questionAnswerRepository;
        this.voteRepository = voteRepository;
    }

    @GetMapping("/q/{questionId}")
    public ModelAndView showQuestion(@PathVariable long questionId, @AuthenticationPrincipal UserDetails userDetails) {
        QuestionAnswer question = questionAnswerRepository.findOne(questionId);
        if (question != null) {
            List<QuestionAnswer> answers = questionAnswerRepository.findAnswersByParentOrderByTimestampAsc(question);
            final List<Vote> votes;
            if (userDetails != null) {
                List<QuestionAnswer> qAndAs = new ArrayList<>(answers);
                qAndAs.add(question);
                votes = voteRepository.findByQuestionAnswerInAndUserId(qAndAs, userDetails.getUser().getId());
            }
            else {
                votes = new ArrayList<>();
            }

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("show_question");
            modelAndView.getModel().put("question", question);
            modelAndView.getModel().put("answers", answers);
            modelAndView.getModel().put("votes", votes);
            return modelAndView;
        }
        else {
            // This would be a good way to handle this:
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "QuestionAnswer with ID '" + questionId + "' not found.");

            // Alternatively, let this exception be picked up by AppWideExceptionHandler:
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
                              @AuthenticationPrincipal UserDetails userDetails) {
        QuestionAnswer questionAnswer = new QuestionAnswer(subject, body, userDetails.getUser());
        questionAnswer = questionAnswerRepository.save(questionAnswer);

        return "redirect:/q/" + questionAnswer.getId();
    }

    @PostMapping("/a")
    public String addAnswer(@RequestParam String body, @RequestParam long parentId,
                              @AuthenticationPrincipal UserDetails userDetails) {
        QuestionAnswer parent = questionAnswerRepository.findOne(parentId);
        QuestionAnswer questionAnswer = new QuestionAnswer(body, userDetails.getUser(), parent);
        questionAnswerRepository.save(questionAnswer);

        return "redirect:/q/" + parent.getId();
    }
}
