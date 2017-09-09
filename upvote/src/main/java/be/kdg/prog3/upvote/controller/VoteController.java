package be.kdg.prog3.upvote.controller;

import be.kdg.prog3.upvote.controller.exception.DuplicateVoteCastException;
import be.kdg.prog3.upvote.controller.exception.QuestionNotFoundException;
import be.kdg.prog3.upvote.controller.exception.VoteNotFoundException;
import be.kdg.prog3.upvote.model.QuestionAnswer;
import be.kdg.prog3.upvote.model.Vote;
import be.kdg.prog3.upvote.persistence.QuestionAnswerRepository;
import be.kdg.prog3.upvote.persistence.VoteRepository;
import be.kdg.prog3.upvote.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
    private VoteRepository voteRepository;
    private QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    public VoteController(VoteRepository voteRepository, QuestionAnswerRepository questionAnswerRepository) {
        this.voteRepository = voteRepository;
        this.questionAnswerRepository = questionAnswerRepository;
    }

    @PostMapping("/vote/{qaId}/{upOrDown}")
    public void castVote(@PathVariable long qaId, @PathVariable String upOrDown, @AuthenticationPrincipal UserDetails userDetails) {
        final QuestionAnswer qa = this.questionAnswerRepository.findOne(qaId);
        if (qa != null) {
            Vote vote = this.voteRepository.findByQuestionAnswerIdAndUserId(qaId, userDetails.getUser().getId());
            final int modifier;
            if (vote == null) {
                vote = new Vote(userDetails.getUser(), qa, upOrDown.equals("up"));
                modifier = 1;
            }
            else {
                if (vote.isUp() == upOrDown.equals("up")) {
                    throw new DuplicateVoteCastException("This vote was already cast.");
                }
                vote.setUp(upOrDown.equals("up"));
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

    @DeleteMapping("/vote/{qaId}")
    public void deleteVote(@PathVariable long qaId, @AuthenticationPrincipal UserDetails userDetails) {
        final QuestionAnswer qa = this.questionAnswerRepository.findOne(qaId);
        final Vote existingVote = this.voteRepository.findByQuestionAnswerIdAndUserId(qaId, userDetails.getUser().getId());

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
}
