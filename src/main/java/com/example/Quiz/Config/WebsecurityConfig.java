package com.example.Quiz.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.beans.Encoder;
@Configuration
@EnableWebSecurity
public class WebsecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
        @Override
        protected void configure(HttpSecurity http) throws Exception{ // tam thoi tat csrf de server respone
            http.cors().and().csrf().disable().antMatcher("/**").authorizeRequests()
            .antMatchers("/").permitAll().anyRequest().authenticated().and().oauth2Login(); // tat csrf
        }

}
