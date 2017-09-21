package be.kdg.prog3.account.tests;

import be.kdg.prog3.account.controller.AccountController;
import be.kdg.prog3.account.model.Account;
import be.kdg.prog3.account.model.Accounts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerApiTests {
    @MockBean
    private Accounts accounts;

    @Autowired
    private MockMvc mvc;

    @Test
    public void testAccountsViewName() throws Exception {
        final List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("Ronny"));
        accounts.add(new Account("Richard"));
        accounts.add(new Account("Rick"));
        given(this.accounts.getAll()).willReturn(accounts);

        this.mvc.perform(get("/api/accounts").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].owner", is("Ronny")))
                .andExpect(jsonPath("$[1].owner", is("Richard")))
                .andExpect(jsonPath("$[2].owner", is("Rick")));
    }

    @Test
    public void testAccountPage() throws Exception {
        final Account marcelAccount = new Account("Andy");
        marcelAccount.setBalance(123.0);
        given(this.accounts.get("Andy")).willReturn(marcelAccount);

        this.mvc.perform(get("/api/accounts/Andy").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.owner", is("Andy")))
                .andExpect(jsonPath("$.balance", is(123.0)));
    }
}
