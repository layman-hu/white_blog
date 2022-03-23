//package com.white.handler.auth;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.white.domain.Result;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.AuthenticationEntryPoint;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author Administrator
// * 登录鉴权验证
// */
//@Component
//public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        ObjectMapper objectMapppr = new ObjectMapper();
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.getWriter().write(objectMapppr.writeValueAsString(Result.error().codeAndMessage("403",e.getMessage())));
//
//    }
//}
