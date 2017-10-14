package be.kdg.prog3.upvote.test;

import be.kdg.prog3.upvote.security.CustomUserDetails;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@TestConfiguration
public class SpringSecurityWebTestConfig {
    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        UserDetails jos = new CustomUserDetails("jos", "jos", 1, authorities);

        UserDetailsService mockedUserDetailsService = mock(UserDetailsService.class);
        when(mockedUserDetailsService.loadUserByUsername("jos")).thenReturn(jos);

        return mockedUserDetailsService;
    }
}
