package com.white.handler.auth;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.white.Result;
import com.white.ResultInfo;
import com.white.auth.MyUserDetails;
import com.white.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
//        Logger logger = Logger.getLogger("com.white.handler.auth.MyAuthenticationSuccessHandler");
//        logger.info("MyAuthenticationSuccessHandler:");
//        logger.info("登录验证成功");
//        logger.info("");
//
//        ObjectMapper objectMapppr = new ObjectMapper();
//        Object principal = authentication.getPrincipal();
//
//        response.setContentType("application/json;charset=UTF-8");
//
//        if(principal instanceof UserDetails){
//            MyUserDetails userDetails = (MyUserDetails) principal;
//            User user = userDetails.getUser();
//            List<String> roles = new ArrayList<>();
//            for(GrantedAuthority role:userDetails.getAuthorities()){
//                roles.add(role.getAuthority());
//            }
//
//            response.getWriter().write(
//                    objectMapppr.writeValueAsString(
//                            Result.success().codeAndMessage(ResultInfo.LOGIN_SUCCESS)
//                                    .data("user",user)
//                                    .data("role",roles)));
//        }
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Logger logger = Logger.getLogger("com.white.handler.auth.MyAuthenticationSuccessHandler");
        logger.info("MyAuthenticationSuccessHandler:");
        logger.info("登录验证成功了");
        logger.info("");

        ObjectMapper objectMapppr = new ObjectMapper();
        Object principal = authentication.getPrincipal();

        httpServletResponse.setContentType("application/json;charset=UTF-8");

        if(principal instanceof UserDetails){
            MyUserDetails userDetails = (MyUserDetails) principal;
            User user = userDetails.getUser();
            List<String> roles = new ArrayList<>();
            for(GrantedAuthority role:userDetails.getAuthorities()){
                roles.add(role.getAuthority());
            }

            httpServletResponse.getWriter().write(
                    objectMapppr.writeValueAsString(
                            Result.success().codeAndMessage(ResultInfo.LOGIN_SUCCESS)
                                    .data("user",user)
                                    .data("role",roles)));
        }
    }


}
