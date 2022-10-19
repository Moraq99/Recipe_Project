package com.example.recipe_project.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .permitAll()
                .loginPage("/login")
                .failureUrl("/login-error")

                .and()

                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/home")
                .invalidateHttpSession(true)

                .and()

                .authorizeRequests()
                .antMatchers("/home**", "/**", "/registration", "/css/*.css",  "/photo/**", "/img/**", "/js/**", "/fonts/**")
                .permitAll()

                .antMatchers("/admin**")
                .hasRole("ADMIN")

                .anyRequest().
                authenticated();
    }

    /*@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        final String password = "password";
        System.out.println("Hash of password \"" + password + "\" is " + encoder().encode(password) + ".");

        auth.inMemoryAuthentication()
                .withUser("user")
                .password(encoder().encode(password))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(encoder().encode(password))
                .roles("USER", "ADMIN");
    }*/

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
