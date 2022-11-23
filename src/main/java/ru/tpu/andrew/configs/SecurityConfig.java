package ru.tpu.andrew.configs;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig  extends WebSecurityConfigurerAdapter {


    private final AuthProvider authProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
//                .httpBasic()
//                .and()
                .authorizeRequests()
                .antMatchers("/service").hasAnyRole("ADMIN", "USER")
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/static/**").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .defaultSuccessUrl("/auth/success", true);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }
}
