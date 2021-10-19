package com.daolayer.people.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin().and()
                .authorizeRequests().antMatchers("/persons/main").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated();
    }
}
