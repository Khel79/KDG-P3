package be.kdg.prog3.account.model;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class Accounts {
    private final Map<String, Account> accounts;

    public Accounts() {
        final Account marcelAccount = new Account("Marcel");
        marcelAccount.setBalance(37.5);
        final Account rudyAccount = new Account("Rudy");
        rudyAccount.setBalance(1000.0);
        final Account josAccount = new Account("Jos");
        josAccount.setBalance(-10.0);

        this.accounts = new HashMap<>();
        this.accounts.put(marcelAccount.getOwner(), marcelAccount);
        this.accounts.put(rudyAccount.getOwner(), rudyAccount);
        this.accounts.put(josAccount.getOwner(), josAccount);
    }

    public Account get(String accountName) {
        return this.accounts.get(accountName);
    }

    public Collection<Account> getAll() {
        return this.accounts.values();
    }
}
