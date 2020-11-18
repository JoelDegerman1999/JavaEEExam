package se.joeldegerman.javaeewebshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public AuthenticationConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().mvcMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/api/test").permitAll()
                .mvcMatchers("/cart/**").hasAnyRole("USER", "ADMIN")
                .mvcMatchers("/order/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/**").hasAnyRole("USER", "ADMIN")
                .mvcMatchers("/").permitAll()
                .mvcMatchers("/signup").permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().permitAll().logoutSuccessUrl("/login?logout");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
