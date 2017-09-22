package be.kdg.prog3.account.tests;

import be.kdg.prog3.account.Application;
import be.kdg.prog3.account.model.Account;
import be.kdg.prog3.account.model.Accounts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class AccountControllerViewTests {
    @MockBean
    private Accounts accounts;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testAccountsViewName() throws Exception {
        this.mvc.perform(get("/accounts").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("showAllAccounts"));
    }

    @Test
    public void testAccountViewName() throws Exception {
        given(this.accounts.get("Marcel")).willReturn(new Account("Marcel"));

        this.mvc.perform(get("/accounts/Marcel").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("showAccountBalance"));
    }

    @Test
    public void testAccountsPage() throws Exception {
        final List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Ronny"));
        accounts.add(new Account("Richard"));
        accounts.add(new Account("Rick"));
        given(this.accounts.getAll()).willReturn(accounts);

        this.mvc.perform(get("/accounts").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ronny")))
                .andExpect(content().string(containsString("Richard")))
                .andExpect(content().string(containsString("Rick")));
    }

    @Test
    public void testAccountPage() throws Exception {
        final Account andyAccount = new Account("Andy");
        andyAccount.setBalance(123.0);
        given(this.accounts.get("Andy")).willReturn(andyAccount);

        this.mvc.perform(get("/accounts/Andy").accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(andyAccount.getOwner())))
                .andExpect(content().string(containsString(String.valueOf(andyAccount.getBalance()))));
    }
}
