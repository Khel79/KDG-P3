package be.kdg.prog3.upvote.controllers;

import be.kdg.prog3.upvote.dto.DtoMapper;
import be.kdg.prog3.upvote.dto.VoteDto;
import be.kdg.prog3.upvote.model.Vote;
import be.kdg.prog3.upvote.security.CustomUserDetails;
import be.kdg.prog3.upvote.services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteController {
    private final VoteService voteService;
    private final DtoMapper dtoMapper;

    @Autowired
    public VoteController(VoteService voteService, DtoMapper dtoMapper) {
        this.voteService = voteService;
        this.dtoMapper = dtoMapper;
    }

    @PostMapping("/vote/{qaId}/{upOrDown}")
    public void castVote(@PathVariable long qaId, @PathVariable String upOrDown, @AuthenticationPrincipal CustomUserDetails userDetails) {
        this.voteService.castVote(qaId, upOrDown.equals("up"), userDetails.getUserId());
    }

    @DeleteMapping("/vote/{qaId}")
    public void deleteVote(@PathVariable long qaId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        this.voteService.deleteVote(qaId, userDetails.getUserId());
    }

    @GetMapping("/vote/{qaId}")
    public VoteDto getVote(@PathVariable long qaId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        final Vote vote = this.voteService.getVoteByUser(qaId, userDetails);
        if (vote != null) {
            return this.dtoMapper.toDto(vote);
        } else {
            return null;
        }
    }
}
