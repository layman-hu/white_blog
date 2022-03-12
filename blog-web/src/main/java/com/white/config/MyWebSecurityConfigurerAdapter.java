package com.white.config;

import com.white.domain.LoggerInfo;
import com.white.handler.auth.MyAccessDeniedHandler;
import com.white.auth.MyUserDetailsService;
import com.white.auth.MyUsernamePasswordAuthenticationFilter;
import com.white.authority.MyAccessDecisionManager;
import com.white.authority.MyFilterInvocationSecurityMetadataSource;
import com.white.handler.auth.MyAuthenticationEntryPoint;
import com.white.handler.auth.MyAuthenticationFailureHandler;
import com.white.handler.auth.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
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
    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;
    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Autowired
    private MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginProcessingUrl("/login");
        http.csrf().disable().exceptionHandling();
        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        //登录验证，上线时设置为true
//        boolean loginVerify = false;
//        if(loginVerify){
            http.authorizeRequests()
                    .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                        @Override
                        public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                            o.setAccessDecisionManager(myAccessDecisionManager);
                            o.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                            return o;
                        }
                    });
//        }
//        http.authorizeRequests()

        LoggerInfo.initialization()
                .loggerName("com.white.config.MyWebSecurityConfigurerAdapter")
                .messages("MyWebSecurityConfigurerAdapter:")
                .messages("登陆拦截，用户权限不足或者用户不存在时，跳转到登陆界面（/login）")
                .messages("MyUserDetailsService",myUserDetailsService)
                .messages("MyAuthenticationSuccessHandler",myAuthenticationSuccessHandler)
                .messages("MyAuthenticationFailureHandler",myAuthenticationFailureHandler)
                .messages("MyAccessDecisionManager",myAccessDecisionManager)
                .messages("MyFilterInvocationSecurityMetadataSource",myFilterInvocationSecurityMetadataSource)
                .messages("MyAccessDeniedHandler",myAccessDeniedHandler)
                .output();
        //其他没有拦截的路径，放行
//        http.authorizeRequests().anyRequest().authenticated();
        http.authorizeRequests().anyRequest().permitAll();

        //异常处理
        http.exceptionHandling()
                .accessDeniedHandler(myAccessDeniedHandler)
                .authenticationEntryPoint(myAuthenticationEntryPoint);
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

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
