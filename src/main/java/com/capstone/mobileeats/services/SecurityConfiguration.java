package com.capstone.mobileeats.services;

import com.capstone.mobileeats.models.Vendor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usersLoader) // How to find members by their email
            .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/vendors") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page
            .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value
            .and()
                .authorizeRequests()
                .antMatchers("/", "/vendors") // anyone can see the home and the ads pages
                .permitAll()
            .and()
                .authorizeRequests()
                //TODO: UPDATE THESE AS WE ADD NEW PAGES
                .antMatchers("/profile", "/vendors/*/edit")
                .authenticated()
        ;
    }

}