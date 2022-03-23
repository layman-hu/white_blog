//package com.white.handler.auth;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.white.domain.Result;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
////角色授权异常处理
//@Component
//public class MyAccessDeniedHandler implements AccessDeniedHandler {
//    @Override
//    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
//
//        ObjectMapper objectMapppr = new ObjectMapper();
//        httpServletResponse.setContentType("application/json;charset=UTF-8");
//        httpServletResponse.getWriter().write(objectMapppr.writeValueAsString(Result.error().codeAndMessage("403",e.getMessage())));
//
//    }
//}
