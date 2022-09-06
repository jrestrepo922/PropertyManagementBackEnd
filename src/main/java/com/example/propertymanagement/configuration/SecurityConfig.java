package com.example.propertymanagement.configuration;

import com.example.propertymanagement.filter.JwtFilter;
import com.example.propertymanagement.service.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    // use for calling customerUserDetailsService witch returns
    // a userDetails user
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // will need to create our own CustomUserDetailService
        // this service will need to be implement UserDetailService and
        // provide the implementation of loadUserByUsername
        auth.userDetailsService(userDetailsService);
    }
    @Bean
    // removes the need for spring to required encoded passwords
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    // used in the logging method in the controller
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    // use to tell spring which endpoints need security
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        // all request are permitted to the "/authenticate" endpoint since this will be use for generating JWT
        // any other end point must be authenticated
        http.csrf().disable().authorizeRequests().antMatchers("/authenticate/**")
                .permitAll().antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll().anyRequest().authenticated()
                // Deals with filter logic
                // filter intercepts request to controllers to check if authorization tokens are provided
                .and().exceptionHandling().and().sessionManagement()
                // enable filter and session is now stateless
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);




        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
    }
}