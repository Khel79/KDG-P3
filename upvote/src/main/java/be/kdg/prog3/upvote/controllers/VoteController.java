package be.kdg.prog3.upvote.controllers;

import be.kdg.prog3.upvote.security.CustomUserDetails;
import be.kdg.prog3.upvote.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
    private VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/vote/{qaId}/{upOrDown}")
    public void castVote(@PathVariable long qaId, @PathVariable String upOrDown, @AuthenticationPrincipal CustomUserDetails userDetails) {
        this.voteService.castVote(qaId, upOrDown.equals("up"), userDetails.getUserId());
    }

    @DeleteMapping("/vote/{qaId}")
    public void deleteVote(@PathVariable long qaId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        this.voteService.deleteVote(qaId, userDetails.getUserId());
    }
}
