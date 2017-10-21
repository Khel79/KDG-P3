package be.kdg.prog3.upvote.test;

import be.kdg.prog3.upvote.Application;
import be.kdg.prog3.upvote.controllers.VoteController;
import be.kdg.prog3.upvote.dto.VoteDto;
import be.kdg.prog3.upvote.model.Vote;
import be.kdg.prog3.upvote.security.CustomUserDetails;
import be.kdg.prog3.upvote.services.VoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RestControllerTests {
    @MockBean(name="voteService")
    private VoteService voteService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private VoteController voteController;

    @Test
    public void testGetVote() throws Exception {
        final long qaId = 123;
        final Vote vote = new Vote(null, null, true);

        final CustomUserDetails josUserDetails = (CustomUserDetails)this.userDetailsService.loadUserByUsername("jos");

        given(this.voteService.getVoteByUser(qaId, josUserDetails)).willReturn(vote);

        final VoteDto voteDto = this.voteController.getVote(qaId, josUserDetails);

        assertThat(vote.isUp(), is(voteDto.isUp()));
    }
}
