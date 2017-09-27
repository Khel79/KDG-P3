package be.kdg.prog3.account.controller;

import be.kdg.prog3.account.model.Account;
import be.kdg.prog3.account.model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
    private final Accounts accounts;

    @Autowired
    public AccountController(Accounts accounts) {
        this.accounts = accounts;
    }

    @GetMapping("/accounts")
    public ModelAndView showAccountBalance() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAllAccounts");
        modelAndView.getModel().put("accounts", this.accounts);
        return modelAndView;
    }

    @GetMapping("/accounts/{accountOwner}")
    public String showAccountBalance(@PathVariable String accountOwner, ModelMap modelMap) {
        final Account account = this.accounts.get(accountOwner);
        modelMap.addAttribute("account", account);
        return "showAccountBalance";
    }

    /*@GetMapping("/accounts/{accountOwner}")
    public ModelAndView showAccountBalance(@PathVariable String accountOwner) {
        final Account account = this.accounts.get(accountOwner);

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("showAccountBalance");
        modelAndView.getModel().put("account", account);
        return modelAndView;
    }*/
}
