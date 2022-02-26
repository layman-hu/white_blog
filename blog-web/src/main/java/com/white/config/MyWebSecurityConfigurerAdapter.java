package com.white.config;

import com.white.auth.MyUserDetailsService;
import com.white.auth.MyUsernamePasswordAuthenticationFilter;
import com.white.handler.auth.MyAuthenticationFailureHandler;
import com.white.handler.auth.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginProcessingUrl("/login");
        http.csrf().disable().exceptionHandling();
        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    //注册MyUsernamePasswordAuthenticationFilter
    @Bean
    public MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new MyUsernamePasswordAuthenticationFilter();
        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());

        return myUsernamePasswordAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(myUserDetailsService);
    }
}
