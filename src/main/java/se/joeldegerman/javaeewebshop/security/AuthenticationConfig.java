package se.joeldegerman.javaeewebshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import se.joeldegerman.javaeewebshop.security.jwt.JwtTokenVerifier;

import static se.joeldegerman.javaeewebshop.security.UserRole.ADMIN;
import static se.joeldegerman.javaeewebshop.security.UserRole.CUSTOMER;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthenticationConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Configuration
    @Order(1)
    public static class RestApiSecurity extends WebSecurityConfigurerAdapter {

        private final UserDetailsService userDetailsService;
        private final JwtTokenVerifier jwtTokenVerifier;

        public RestApiSecurity(UserDetailsService userDetailsService, JwtTokenVerifier jwtTokenVerifier) {
            this.userDetailsService = userDetailsService;
            this.jwtTokenVerifier = jwtTokenVerifier;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.cors().and().csrf().disable()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                    .addFilterBefore(jwtTokenVerifier, UsernamePasswordAuthenticationFilter.class)
                    .antMatcher("/api/**").authorizeRequests()
                    .antMatchers("/api/auth").permitAll()
                    .antMatchers("/api/admin/**").hasRole(ADMIN.name())
                    .antMatchers("/api/**").hasAnyRole(ADMIN.name(), CUSTOMER.name());
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Configuration
    @Order(2)
    public static class LoginFromSecurity extends WebSecurityConfigurerAdapter {

        private final UserDetailsService userDetailsService;

        public LoginFromSecurity(UserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
                    .mvcMatcher("/**").authorizeRequests()
                    .mvcMatchers("/admin/**").hasRole(ADMIN.name())
                    .mvcMatchers("/cart/**").hasAnyRole(CUSTOMER.name(), ADMIN.name())
                    .mvcMatchers("/order/**").hasAnyRole(CUSTOMER.name(), ADMIN.name())
                    .mvcMatchers("/profile").hasAnyRole(CUSTOMER.name(), ADMIN.name())
                    .mvcMatchers("/signup").permitAll()
                    .mvcMatchers("/").permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
                    .and().logout().logoutSuccessUrl("/login?logout").permitAll()
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                    .and().rememberMe();

//            http.sessionManagement().maximumSessions(1).expiredUrl("/login?expired");
        }

    }

}
