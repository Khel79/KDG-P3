package be.kdg.prog3.upvote.test;

import be.kdg.prog3.upvote.Application;
import be.kdg.prog3.upvote.model.Vote;
import be.kdg.prog3.upvote.security.CustomUserDetails;
import be.kdg.prog3.upvote.services.VoteService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { Application.class, SpringSecurityWebTestConfig.class })
@WebAppConfiguration
@EnableWebSecurity
public class RestControllerMvcTests {
    @MockBean
    private VoteService voteService;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserDetailsService userDetailsService;

    private MockMvc mvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithUserDetails("jos")
    public void testGetVoteMvc() throws Exception {
        final long qaId = 123;
        final Vote vote = new Vote(null, null, true);

        final CustomUserDetails josUserDetails = (CustomUserDetails)this.userDetailsService.loadUserByUsername("jos");

        given(this.voteService.getVoteByUser(qaId, josUserDetails)).willReturn(vote);

        this.mvc.perform(get("/vote/" + qaId)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.up", is(vote.isUp())));
    }
}
