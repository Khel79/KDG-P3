package be.kdg.prog3.upvote.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private final long userId;

    public CustomUserDetails(String userName, String password, long userId, Collection<? extends GrantedAuthority> authorities) {
        super(userName, password, authorities);
        this.userId = userId;
    }

    public long getUserId() {
        return this.userId;
    }
}
